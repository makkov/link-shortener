package com.colvir.link.shortener.config;

import com.colvir.link.shortener.mapper.LinkMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public LinkMapper getLinkMapper() {
        return Mappers.getMapper(LinkMapper.class);
    }
}
