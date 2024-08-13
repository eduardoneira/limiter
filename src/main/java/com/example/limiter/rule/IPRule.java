package com.example.limiter.rule;

import com.example.limiter.resource.Resource;
import com.example.limiter.resource.ResourceFactory;

import java.util.HashMap;

public class IPRule implements Rule<Request> {

    private final HashMap<Request, Resource> ipManager;
    private final ResourceFactory resourceFactory;

    public IPRule(ResourceFactory resourceFactory) {
        this.resourceFactory = resourceFactory;
        this.ipManager = new HashMap<>();
    }

    @Override
    public boolean allow(Request request) {
        final Resource resource = this.ipManager.computeIfAbsent(
                request,
                (_) -> resourceFactory.create(getRequestKey(request)));

        return resource.use();
    }

    private String getRequestKey(Request request) {
        return request.getUri() + ":" + request.getIp();
    }
}
