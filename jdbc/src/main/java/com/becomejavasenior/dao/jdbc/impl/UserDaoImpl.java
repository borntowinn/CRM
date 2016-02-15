package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.User;
import com.becomejavasenior.UserRole;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.UserRoleDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractJDBCDao<User> implements UserDao<User>{
    private final static String SELECT_QUERY = "SELECT * FROM \"user\"";
    private final static String LAST_INSERT_ID_QUERY = "SELECT user_id, name, password, description, date_creation, email, mobile_phone, work_phone, user_role_id, language FROM \"user\" WHERE user_id=?";
    private final static String CREATE_QUERY = "INSERT INTO \"user\" (name, password, description, date_creation, email, mobile_phone, work_phone, user_role_id, language) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_QUERY = "UPDATE \"user\" SET name = ?, password = ?, description  = ?, date_creation = ?, email = ?, mobile_phone = ?, work_phone = ?, user_role_id = ?, language = ? WHERE user_id=?";
    private final static String DELETE_QUERY = "DELETE FROM \"user\" WHERE user_id= ?;";

    private UserRoleDao<UserRole> userRoleDao = DaoFactory.getUserRoleDAO();

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
    public User create(User user) throws PersistException {
        return persist(user);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        ArrayList<User> result = new ArrayList<User>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setDescription(rs.getString("description"));
                if(rs.getTimestamp("date_creation") != null) {
                    user.setCreationDate(rs.getTimestamp("date_creation").toLocalDateTime());
                }
                user.setEmail(rs.getString("email"));
                user.setMobilePhone(rs.getString("mobile_phone"));
                user.setWorkPhone(rs.getString("work_phone"));
                user.setUserRole(userRoleDao.getByPK(rs.getInt("user_role_id")));
                user.setLanguage(rs.getInt("language"));
                result.add(user);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getDescription());

            if(object.getCreationDate() != null){
                statement.setTimestamp(4, Timestamp.valueOf(object.getCreationDate()));
            }else{
                statement.setNull(3, Types.TIMESTAMP);
            }

            statement.setString(5, object.getEmail());
            statement.setString(6, object.getMobilePhone());
            statement.setString(7, object.getWorkPhone());
            statement.setInt(8, object.getUserRole().getId());
            statement.setInt(9, object.getLanguage());
            statement.setInt(10, object.getId());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getDescription());

            if(object.getCreationDate() != null){
                statement.setTimestamp(4, Timestamp.valueOf(object.getCreationDate()));
            }else{
                statement.setNull(3, Types.TIMESTAMP);
            }

            statement.setString(5, object.getEmail());
            statement.setString(6, object.getMobilePhone());
            statement.setString(7, object.getWorkPhone());
            statement.setInt(8, object.getUserRole().getId());
            statement.setInt(9, object.getLanguage());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
