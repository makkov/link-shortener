package com.colvir.link.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableTransactionManagement
public class LinkShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkShortenerApplication.class, args);
    }

}
