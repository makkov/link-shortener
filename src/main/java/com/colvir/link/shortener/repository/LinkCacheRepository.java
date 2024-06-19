package com.colvir.link.shortener.repository;

import com.colvir.link.shortener.model.Link;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class LinkCacheRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public Optional<Link> findById(Integer id) {
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

    public Link save(Link link) {
        try {
            redisTemplate.opsForValue().set(String.valueOf(link.getId()), objectMapper.writeValueAsString(link));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return link;
    }

    @Scheduled(fixedDelay = 1L)
    public void clearAll() {
        Set<String> keys = redisTemplate.keys("*");
        if (keys == null) {
            return;
        }

        System.out.printf("Из кэша будет удално %s записей", keys.size());

        redisTemplate.delete(keys);
    }
}
