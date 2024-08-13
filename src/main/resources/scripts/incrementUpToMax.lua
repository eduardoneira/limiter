local current = tonumber(redis.call('GET', KEYS[1]))
local delta = tonumber(KEYS[2])
local upperBound = tonumber(KEYS[3])
local canIncrement = current < upperBound

if canIncrement then
    if current + delta > upperBound then
        delta = upperBound - delta
    end

    redis.call('INCRBY', KEYS[1], delta)
end

return canIncrement
