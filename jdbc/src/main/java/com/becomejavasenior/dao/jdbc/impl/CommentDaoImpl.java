package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Comment;
import com.becomejavasenior.dao.CommentDao;
import com.becomejavasenior.dao.exception.PersistException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Default71721 on 10.02.16.
 */
public class CommentDaoImpl extends AbstractJDBCDao<Comment> implements CommentDao<Comment> {
    private final static String SELECT_QUERY = "SELECT comment_id, comment, data_creation FROM comment;";
    private final static String SELECT_BY_PK_QUERY = "SELECT comment_id, comment, data_creation FROM comment WHERE comment_id = ?;";
    private final static String CREATE_QUERY = "INSERT INTO comment (comment, data_creation) VALUES (?, ?);";
    private final static String UPDATE_QUERY = "UPDATE comment SET comment = ?, data_creation = ?  WHERE comment_id = ?;";
    private final static String DELETE_QUERY = "DELETE FROM comment WHERE comment_id= ?;";

    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getSelectPKQuery() {
        return SELECT_BY_PK_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    public Comment create(Comment comment) throws PersistException {
        return persist(comment);
    }

    @Override
    protected List<Comment> parseResultSet(ResultSet rs) throws PersistException {
        ArrayList<Comment> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("comment_id"));
                comment.setComment(rs.getString("comment"));
                comment.setCreationDate(rs.getTimestamp("data_creation").toLocalDateTime());
                result.add(comment);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Comment comment) throws PersistException {
        try {
            statement.setString(1, comment.getComment());
            statement.setTimestamp(2, Timestamp.valueOf(comment.getCreationDate()));
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Comment comment) throws PersistException {
        try {
            statement.setString(1, comment.getComment());
            statement.setTimestamp(2, Timestamp.valueOf(comment.getCreationDate()));
            statement.setInt(3, comment.getId());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}