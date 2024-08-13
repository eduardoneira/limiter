package com.example.limiter.rule;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Objects;

public class Request {

    private final String uri;
    private final String ip;

    public Request(HttpServletRequest httpServletRequest) {
        this.uri = httpServletRequest.getRequestURI();
        this.ip = httpServletRequest.getRemoteAddr();
    }

    public String getUri() {
        return uri;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(uri, request.uri) && Objects.equals(ip, request.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, ip);
    }
}
