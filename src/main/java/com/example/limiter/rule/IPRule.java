package com.example.limiter.rule;

import com.example.limiter.resource.Resource;
import com.example.limiter.resource.ResourceFactory;

import java.util.HashMap;

public class IPRule implements Rule<String> {

    private final HashMap<String, Resource> ipManager;
    private final ResourceFactory resourceFactory;

    public IPRule(ResourceFactory resourceFactory) {
        this.resourceFactory = resourceFactory;
        this.ipManager = new HashMap<>();
    }

    @Override
    public boolean allow(String data) {
        final Resource resource = this.ipManager.computeIfAbsent(data, (_) -> resourceFactory.create());
        return resource.use();
    }
}
