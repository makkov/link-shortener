package com.colvir.link.shortener.repository;

import com.colvir.link.shortener.model.Link;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class LinkCacheRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Retryable(retryFor = RuntimeException.class, maxAttempts = 10, backoff = @Backoff(delay = 3000L))
    public Optional<Link> findById(Integer id) {
        System.out.printf("request to Redis, time: %s\n", LocalDateTime.now());

        String stringValue = redisTemplate.opsForValue().get(String.valueOf(id));

        if (stringValue == null) {
            return Optional.empty();
        }

        try {
            return Optional.of(objectMapper.readValue(stringValue, Link.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Recover
    Optional<Link> handle(RuntimeException e, Integer id) {
        System.out.printf("Redis is unavailable, Link with id %s will received from DB\n", id);
        return Optional.empty();
    }

    public Link save(Link link) {
        try {
            redisTemplate.opsForValue().set(String.valueOf(link.getId()), objectMapper.writeValueAsString(link));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return link;
    }

    //    @Scheduled(fixedDelay = 1L)
    public void clearAll() {
        Set<String> keys = redisTemplate.keys("*");
        if (keys == null) {
            return;
        }

        System.out.printf("Из кэша будет удално %s записей", keys.size());

        redisTemplate.delete(keys);
    }
}
