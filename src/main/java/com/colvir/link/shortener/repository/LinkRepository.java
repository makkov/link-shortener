package com.colvir.link.shortener.repository;

import com.colvir.link.shortener.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Integer> {

    Link findByShorted(String shorted);
}
