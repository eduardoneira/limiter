local current = tonumber(redis.call('GET', KEYS[1]))
local lowerBound = tonumber(KEYS[2])
local canDecrement = current > lowerBound

if canDecrement then
    redis.call('DECR', KEYS[1])
end

return canDecrement
