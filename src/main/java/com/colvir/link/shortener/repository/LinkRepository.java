package com.colvir.link.shortener.repository;

import com.colvir.link.shortener.mapper.LinkRowMapper;
import com.colvir.link.shortener.model.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class LinkRepository {

    private final Set<Link> links = new HashSet<>();

    private final JdbcTemplate jdbcTemplate;
    private final LinkRowMapper linkRowMapper;

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

        String preparedStatementString = "INSERT INTO links VALUES(?, ?, ?);";
        jdbcTemplate.update(preparedStatementString, link.getId(), link.getShorted(), link.getOriginal());

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

        String statementString = "SELECT * FROM links";

        return jdbcTemplate.query(statementString, linkRowMapper);
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

        String statementString = "SELECT * FROM links WHERE id = ?";
        return jdbcTemplate.query(statementString, linkRowMapper, new Object[]{id}).stream()
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
