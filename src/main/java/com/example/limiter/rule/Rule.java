package com.example.limiter.rule;

public interface Rule<T> {

    boolean allow(T data);
}
