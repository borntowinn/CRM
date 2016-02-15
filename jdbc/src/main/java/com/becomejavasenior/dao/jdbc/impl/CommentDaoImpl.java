package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

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
    private final static String SELECT_QUERY = "SELECT comment_id, comment, data_creation, file_name, company_id, contact_id, deal_id, task_id FROM comment;";
    private final static String SELECT_BY_PK_QUERY = "SELECT comment_id, comment, data_creation, company_id, contact_id, deal_id, task_id FROM comment WHERE comment_id = ?;";
    private final static String CREATE_QUERY = "INSERT INTO comment (comment, data_creation, company_id, contact_id, deal_id, task_id) VALUES (?, ?);";
    private final static String UPDATE_QUERY = "UPDATE comment SET comment = ?, data_creation = ?, company_id = ?, contact_id = ?, deal_id = ?, task_id = ? WHERE comment_id = ?;";
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
        CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();
        ContactDao<Contact> contactDao = DaoFactory.getContactDao();
        DealDao<Deal> dealDao = DaoFactory.getDealDao();
        TaskDao<Task> taskDao = DaoFactory.getTaskDao();
        try {
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("comment_id"));
                comment.setComment(rs.getString("comment"));
                comment.setCreationDate(rs.getTimestamp("data_creation").toLocalDateTime());
                comment.setCompanyId(companyDao.getByPK(rs.getInt("company_id")));
                comment.setContactId(contactDao.getByPK(rs.getInt("contact_id")));
                comment.setDealId(dealDao.getByPK(rs.getInt("deal_id")));
                comment.setTaskId(taskDao.getByPK(rs.getInt("task_id")));
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
            statement.setInt(3, comment.getCompanyId().getId());
            statement.setInt(4, comment.getContactId().getId());
            statement.setInt(5, comment.getDealId().getId());
            statement.setInt(6, comment.getTaskId().getId());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Comment comment) throws PersistException {
        try {
            statement.setString(1, comment.getComment());
            statement.setTimestamp(2, Timestamp.valueOf(comment.getCreationDate()));
            statement.setInt(3, comment.getCompanyId().getId());
            statement.setInt(4, comment.getContactId().getId());
            statement.setInt(5, comment.getDealId().getId());
            statement.setInt(6, comment.getTaskId().getId());
            statement.setInt(7, comment.getId());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}