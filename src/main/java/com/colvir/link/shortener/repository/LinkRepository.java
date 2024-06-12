package com.colvir.link.shortener.repository;

import com.colvir.link.shortener.model.Link;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class LinkRepository {

    private final SessionFactory sessionFactory;

    public Link save(Link link) {

        Session session = sessionFactory.getCurrentSession();
        session.persist(link);

        return link;
    }

    public List<Link> findAll() {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select l from Link l", Link.class)
                .getResultList();
    }

    public Optional<Link> findById(Integer id) {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select l from Link l where l.id = :id", Link.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();
    }

    public Link update(Link updatedLink) {

        Session session = sessionFactory.getCurrentSession();

        Link linkForUpdate = session.get(Link.class, updatedLink.getId());

        linkForUpdate.setOriginal(updatedLink.getOriginal());
        linkForUpdate.setShorted(updatedLink.getShorted());

        return updatedLink;
    }

    public Link delete(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        Link linkForDelete = session.get(Link.class, id);

        session.remove(linkForDelete);

        return linkForDelete;
    }

    public Link getByShorted(String shortLink) {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select l from Link l where l.shorted = :shorted", Link.class)
                .setParameter("shorted", shortLink)
                .getResultList().stream().findFirst().get();
    }
}
