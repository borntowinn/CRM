package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Tag;
import com.becomejavasenior.dao.TagDao;
import com.becomejavasenior.dao.exception.PersistException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDaoImpl extends AbstractJDBCDao<Tag> implements TagDao<Tag> {

    private final static String SELECT_QUERY = "SELECT tag_id, tag FROM tag";
    private final static String SELECT_BY_PK_QUERY = "SELECT tag_id, tag FROM tag WHERE tag_id= ?";
    private final static String CREATE_QUERY = "INSERT INTO tag (tag) VALUES (?);";
    private final static String UPDATE_QUERY = "UPDATE tag SET tag= ? WHERE tag_id= ?;";
    private final static String DELETE_QUERY = "DELETE FROM tag WHERE tag_id= ?;";

    @Override
    protected String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected String getSelectPKQuery() {
        return SELECT_BY_PK_QUERY;
    }

    @Override
    protected List<Tag> parseResultSet(ResultSet rs) throws PersistException {
        ArrayList<Tag> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt("tag_id"));
                tag.setTag(rs.getString("tag"));
                list.add(tag);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Tag tag) throws PersistException {
        try {
            statement.setString(1, tag.getTag());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Tag tag) throws PersistException {
        try {
            statement.setInt(1, tag.getId());
            statement.setString(2, tag.getTag());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    public Tag create(Tag tag) throws PersistException {
        return persist(tag);
    }
}
