package com.colvir.link.shortener.repository;

import com.colvir.link.shortener.model.Link;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class LinkRepository {

    private final Set<Link> links = new HashSet<>();

    public Link save(Link link) {
        links.add(link);
        return link;
    }

    public Link getByShorted(String shortLink) {
        return links.stream()
                .filter(link -> link.getShorted().equals(shortLink))
                .findFirst()
                .orElse(null);
    }
}
