package com.colvir.link.shortener.repository;

import com.colvir.link.shortener.model.Link;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LinkRepository {

    private final Set<Link> links = new HashSet<>();

    public Link save(Link link) {
        links.add(link);
        return link;
    }

    public List<Link> findAll() {
        return new ArrayList<>(links);
    }

    public Optional<Link> findById(Integer id) {
        return links.stream()
                .filter(link -> link.getId().equals(id))
                .findFirst();
    }

    public Link update(Link linkForUpdate) {
        for (Link link : links) {
            if (link.getId().equals(linkForUpdate.getId())) {
                link.setShorted(linkForUpdate.getShorted());
                link.setOriginal(linkForUpdate.getOriginal());
            }
        }
        return linkForUpdate;
    }

    public Link delete(Integer id) {
        Link linkForDelete = links.stream()
                .filter(link -> link.getId().equals(id))
                .findFirst().get();
        links.remove(linkForDelete);
        return linkForDelete;
    }

    public Link getByShorted(String shortLink) {
        return links.stream()
                .filter(link -> link.getShorted().equals(shortLink))
                .findFirst()
                .orElse(null);
    }
}
