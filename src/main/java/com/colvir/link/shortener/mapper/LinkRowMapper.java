package com.colvir.link.shortener.mapper;

import com.colvir.link.shortener.model.Link;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LinkRowMapper implements RowMapper<Link> {

    @Override
    public Link mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Link link = new Link();
        link.setId(resultSet.getInt("id"));
        link.setShorted(resultSet.getString("shorted"));
        link.setOriginal(resultSet.getString("original"));
        return link;
    }
}
