# Limiter

A basic rate limiter library.

## General Specifications

- Server side rate limiter
- Should allow different throttling rules
- Must handle large number of requests
- Should work in a distributed environment
- Inform to users being throttled

## Implementation specifications

- Filter by IP address of request

## Resource management algorithms

### Token Bucket

Configure *bucket size*, *refill count* and *refill time* in seconds.
