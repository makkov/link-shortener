package com.colvir.link.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class LinkShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkShortenerApplication.class, args);
    }

}
