package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Tag;
import com.becomejavasenior.dao.TagDao;
import com.becomejavasenior.dao.exception.PersistException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDaoImpl extends AbstractJDBCDao<Tag> implements TagDao<Tag> {

    private static final Logger LOGGER = Logger.getLogger(AbstractJDBCDao.class);

    private final static String SELECT_QUERY = "SELECT tag_id, tag FROM tag";
    private final static String SELECT_BY_PK_QUERY = "SELECT tag_id, tag FROM tag WHERE tag_id= ?";
    private final static String CREATE_QUERY = "INSERT INTO tag (tag) VALUES (?);";
    private final static String UPDATE_QUERY = "UPDATE tag SET tag= ? WHERE tag_id= ?;";
    private final static String DELETE_QUERY = "DELETE FROM tag WHERE tag_id= ?;";
    private final static String ADD_TAG_TO_DEAL = "INSERT INTO tags_to_deal (tag_id, deal_id) VALUES (?, ?);";

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
            LOGGER.error("result has not parsed " + e);
            throw new PersistException(e);
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Tag tag) throws PersistException {
        try {
            statement.setString(1, tag.getTag());
        } catch (SQLException e) {
            LOGGER.error("couldn't prepared Statement for Insert " + e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Tag tag) throws PersistException {
        try {
            statement.setInt(1, tag.getId());
            statement.setString(2, tag.getTag());
        } catch (SQLException e) {
            LOGGER.error("couldn't prepared Statement for Update " + e);
            throw new PersistException(e);
        }
    }

    @Override
    public Tag create(Tag tag) throws PersistException {
        return persist(tag);
    }

    public void addTagToDeal(int tagId, int dealId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_TAG_TO_DEAL);
            statement.setInt(1, tagId);
            statement.setInt(2, dealId);
            executeQuery(statement);
        } catch (SQLException e) {
            LOGGER.error("error executing query " + e.getMessage());
            if (e.getErrorCode() != 0) {
                throw new PersistException();
            }
        }
    }
}
