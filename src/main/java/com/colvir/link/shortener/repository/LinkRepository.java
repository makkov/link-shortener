package com.colvir.link.shortener.repository;

import com.colvir.link.shortener.model.Link;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class LinkRepository {

    private final JdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Link> beanPropertyRowMapper = new BeanPropertyRowMapper<>(Link.class);

    private final SessionFactory sessionFactory;

    public Link save(Link link) {
//        try {
//            Statement statement = connection.createStatement();

//            INSERT INTO links VALUES(33, 'new link', 'original link');

//            String statementString = "INSERT INTO links VALUES("
//                    + link.getId() + ", '"
//                    + link.getShorted() + "', '"
//                    + link.getOriginal() + "')";
//
//            System.out.println(statementString);
//
//            statement.executeUpdate(statementString);


//            PreparedStatement preparedStatement = connection.prepareStatement(preparedStatementString);
//            preparedStatement.setInt(1, link.getId());
//            preparedStatement.setString(2, link.getShorted());
//            preparedStatement.setString(3, link.getOriginal());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return link;

//        String preparedStatementString = "INSERT INTO links VALUES(?, ?, ?);";
//        jdbcTemplate.update(preparedStatementString, link.getId(), link.getShorted(), link.getOriginal());

        Session session = sessionFactory.getCurrentSession();
        session.persist(link);

        return link;
    }

    public List<Link> findAll() {

//        try {
//
//            String statementString = "SELECT * FROM links";
//
//            List<Link> links = jdbcTemplate.query(statementString, linkRowMapper);
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery(statementString);
//
//            List<Link> result = new ArrayList<>();
//
//            while (resultSet.next()) {
//                Link link = new Link();
//                link.setId(resultSet.getInt("id"));
//                link.setShorted(resultSet.getString("shorted"));
//                link.setOriginal(resultSet.getString("original"));
//
//                result.add(link);
//            }
//
//            return result;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return new ArrayList<>(links);

//        String statementString = "SELECT * FROM links";
//
//        return jdbcTemplate.query(statementString, beanPropertyRowMapper);

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select l from Link l", Link.class)
                .getResultList();
    }

    public Optional<Link> findById(Integer id) {
//        try {
//
//            String preparedStatementString = "SELECT * FROM links WHERE id = ?";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(preparedStatementString);
//
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                Link link = new Link();
//                link.setId(resultSet.getInt("id"));
//                link.setShorted(resultSet.getString("shorted"));
//                link.setOriginal(resultSet.getString("original"));
//                return Optional.of(link);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return Optional.empty();

//        String statementString = "SELECT * FROM links WHERE id = ?";
//        return jdbcTemplate.query(statementString, beanPropertyRowMapper, new Object[]{id}).stream()
//                .findFirst();

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select l from Link l where l.id = :id", Link.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();
    }

    public Link update(Link updatedLink) {
//        for (Link link : links) {
//            if (link.getId().equals(linkForUpdate.getId())) {
//                link.setShorted(linkForUpdate.getShorted());
//                link.setOriginal(linkForUpdate.getOriginal());
//            }
//        }

//        UPDATE links l
//        SET l.shorted = '',
//                l.original = ''
//        WHERE l.id = ;

//        String statementString = "UPDATE links SET shorted = ?, original = ? WHERE id = ?";
//
//        jdbcTemplate.update(statementString, linkForUpdate.getShorted(), linkForUpdate.getOriginal(), linkForUpdate.getId());
//
//        return linkForUpdate;

        Session session = sessionFactory.getCurrentSession();

        Link linkForUpdate = session.get(Link.class, updatedLink.getId());

        linkForUpdate.setOriginal(updatedLink.getOriginal());
        linkForUpdate.setShorted(updatedLink.getShorted());

        return updatedLink;
    }

    public Link delete(Integer id) {
//        Link linkForDelete = links.stream()
//                .filter(link -> link.getId().equals(id))
//                .findFirst().get();
//        links.remove(linkForDelete);

//        Link linkForDelete = findById(id).get();
//
//        String statementString = "DELETE FROM links WHERE id = ?";
//
//        jdbcTemplate.update(statementString, id);
//
//        return linkForDelete;

        Session session = sessionFactory.getCurrentSession();
        Link linkForDelete = session.get(Link.class, id);

        session.remove(linkForDelete);

        return linkForDelete;
    }

    public Link getByShorted(String shortLink) {
//        return links.stream()
//                .filter(link -> link.getShorted().equals(shortLink))
//                .findFirst()
//                .orElse(null);

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select l from Link l where l.shorted = :shorted", Link.class)
                .setParameter("shorted", shortLink)
                .getResultList().stream().findFirst().get();
    }
}
