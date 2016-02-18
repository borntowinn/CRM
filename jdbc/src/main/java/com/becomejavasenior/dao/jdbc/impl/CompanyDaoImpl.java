package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Comment;
import com.becomejavasenior.Company;
import com.becomejavasenior.File;
import com.becomejavasenior.User;
import com.becomejavasenior.dao.CommentDao;
import com.becomejavasenior.dao.CompanyDao;
import com.becomejavasenior.dao.FileDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl extends AbstractJDBCDao<Company> implements CompanyDao<Company> {
    private static final Logger log = Logger.getLogger(CompanyDaoImpl.class);

    private final static String SELECT_QUERY = "SELECT company_id, company_name, responsible, phone_number, email, web_site, createdby, address, isdeleted, creation_time FROM \"company\"";
    private final static String LAST_INSERT_ID_QUERY = "SELECT company_id, company_name, responsible, phone_number, email, web_site, createdby, address, isdeleted, creation_time FROM \"company\" WHERE company_id=?";
    private final static String CREATE_QUERY = "INSERT INTO \"company\" (company_name, responsible, phone_number, email, web_site, createdby, address, isdeleted, creation_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_QUERY = "UPDATE \"company\" SET company_name = ?, responsible = ?, phone_number = ?, email = ?, web_site = ?, createdby = ?, address = ?, isdeleted = ?, creation_time = ? WHERE company_id=?";
    private final static String DELETE_QUERY = "DELETE FROM \"company\" WHERE company_id= ?;";
    private final static String GET_ALL_FILES_FOR_COMPANY_QUERY = "SELECT * " +
            "FROM file INNER JOIN files_to_company " +
            "ON files_to_company.file_id=file.file_id " +
            "WHERE files_to_company.company_id=";
    private final static String GET_ALL_COMMENTS_FOR_COMPANY_QUERY = "SELECT * " +
            "FROM comment INNER JOIN comments_to_company " +
            "ON comments_to_company.comment_id=comment.comment_id " +
            "WHERE comments_to_company.company_id=";
    private final static String INSERT_FILES_TO_COMPANY_QUERY = "INSERT INTO files_to_company (file_id, company_id) VALUES (";
    private final static String INSERT_COMMENTS_TO_COMPANY_QUERY = "INSERT INTO comments_to_company (comment_id, company_id) VALUES (";

    private UserDao<User> userDao = DaoFactory.getUserDAO();
    private FileDao<File> fileDao = DaoFactory.getFileDao();
    private CommentDao<Comment> commentDao = DaoFactory.getCommentDao();

    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getSelectPKQuery() {
        return LAST_INSERT_ID_QUERY;
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
    public Company create(Company company) throws PersistException {
        return persist(company);
    }

    @Override
    protected List<Company> parseResultSet(ResultSet rs) throws PersistException {
        ArrayList<Company> result = new ArrayList<Company>();
        try {
            while (rs.next()) {
                Company company = new Company();
                company.setId(rs.getInt("company_id"));
                company.setCompanyName(rs.getString("company_name"));
                company.setResponsible(userDao.getByPK(rs.getInt("responsible")));
                company.setPhoneNumber(rs.getString("phone_number"));
                company.setEmail(rs.getString("email"));
                company.setWebsite(rs.getString("web_site"));

                if(rs.getInt("createdby") != 0){
                    company.setCreatedBy(userDao.getByPK(rs.getInt("createdby")));
                }else{
                    company.setCreatedBy(userDao.getByPK(1));
                }

                company.setAddress(rs.getString("address"));
                company.setDeleted(rs.getBoolean("isdeleted"));
                company.setCreationTime(rs.getTimestamp("creation_time").toLocalDateTime());
                result.add(company);
            }
        } catch (SQLException e) {
            log.error("error while parse result set: " + e.getMessage());
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Company object) throws PersistException {
        try {
            prepareStatement(statement, object);
            statement.setInt(10, object.getId());
        } catch (SQLException e) {
            log.error("couldn't prepared Statement for Update Company " + e.getMessage());
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Company object) throws PersistException {
        try {
            prepareStatement(statement, object);
        } catch (SQLException e) {
            log.error("couldn't prepared Statement for Insert Company " + e.getMessage());
            throw new PersistException(e);
        }
    }

    private void prepareStatement(PreparedStatement statement, Company object) throws SQLException {
        statement.setString(1, object.getCompanyName());
        statement.setInt(2, object.getResponsible().getId());
        statement.setString(3, object.getPhoneNumber());
        statement.setString(4, object.getEmail());
        statement.setString(5, object.getWebsite());

        if(object.getCreatedBy() == null){
            statement.setNull(6, Types.INTEGER);
        }else{
            statement.setInt(6, object.getCreatedBy().getId());
        }

        statement.setString(7, object.getAddress());
        statement.setBoolean(8, object.getDeleted());
        statement.setTimestamp(9, Timestamp.valueOf(object.getCreationTime()));
    }

    @Override
    public void addFileForCompany(File file, Company company) {
        int id;
        id = fileDao.create(file).getId();
        executeQuery(INSERT_FILES_TO_COMPANY_QUERY + id + ", " + company.getId() + ");");
    }

    @Override
    public void addCommentForCompany(Comment comment, Company company) {
        int id;
        id = commentDao.create(comment).getId();
        executeQuery(INSERT_COMMENTS_TO_COMPANY_QUERY + id + ", " + company.getId() + ");");
    }

    @Override
    public List<File> getAllFilesForCompany(Company company) {
        ResultSet resultSet = executeQuery(GET_ALL_FILES_FOR_COMPANY_QUERY+company.getId());
        File file = new File();
        List<File> fileList= new ArrayList<>();
        try {
            while (resultSet.next()) {
                file.setId(resultSet.getInt("file_id"));
                file.setCreationDate(resultSet.getTimestamp("date_creation").toLocalDateTime());
                file.setFileName(resultSet.getString("file_name"));
                file.setFile(resultSet.getBytes("file"));
                fileList.add(file);
            }
        }
        catch (SQLException e)
        {
            log.error("couldn't get files " + e.getMessage());
            throw new PersistException(e.getMessage());
        }
        return fileList;
    }

    @Override
    public List<Comment> getAllCommentsForCompany(Company company) {

        ResultSet resultSet = executeQuery(GET_ALL_COMMENTS_FOR_COMPANY_QUERY + company.getId());
        Comment comment = new Comment();
        List<Comment> commentList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                comment.setId(resultSet.getInt("comment_id"));
                comment.setCreationDate(resultSet.getTimestamp("data_creation").toLocalDateTime());
                comment.setComment(resultSet.getString("comment"));
                commentList.add(comment);
            }
        } catch (SQLException e) {
            log.error("couldn't get comment for Company " + e.getMessage());
            throw new PersistException(e.getMessage());
        }
        return commentList;
    }
}