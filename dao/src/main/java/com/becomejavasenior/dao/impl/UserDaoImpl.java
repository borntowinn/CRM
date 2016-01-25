package com.becomejavasenior.dao.impl;

import com.becomejavasenior.User;
import com.becomejavasenior.dao.AbstractJDBCDao;
import com.becomejavasenior.dao.PersistException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends AbstractJDBCDao<User, Integer> {

    private class PersistUser extends User {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM \"user\"";
    }

    @Override
    public String getSelectLastInsertIdQuery() {
        return "SELECT user_id, name, surname, password, date_creation, email, mobile_phone, work_phone, user_role_id FROM \"user\" WHERE user_id=";
    }

    @Override
    public String getSelectPKQuery() {
        return "SELECT user_id, name, surname, password, date_creation, email, mobile_phone, work_phone, user_role_id FROM \"user\" WHERE user_id=?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO \"user\" (name, surname, password, date_creation, email, mobile_phone, work_phone, user_role_id) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE \"user\" \n" +
                "SET name = ?, surname  = ?, password = ?, date_creation = ?, , email = ?, , mobile_phone = ?, , work_phone = ?, user_role_id = ? \n" +
                "WHERE user_id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM \"user\" WHERE user_id= ?;";
    }

    @Override
    public User create() throws PersistException {
        User user = new User();
        return persist(user);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                PersistUser user = new PersistUser();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setPassword(rs.getString("password"));
                user.setCreationDate(rs.getDate("date_creation"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setMobilePhone(rs.getString("mobile_phone"));
                user.setWorkPhone(rs.getString("work_phone"));
                user.setUserRoleId(rs.getInt("user_role_id"));

                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            Date sqlDate = convert(object.getCreationDate());

            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getPassword());
            statement.setDate(4, sqlDate);
            statement.setString(5, object.getEmail());
            statement.setString(6, object.getMobilePhone());
            statement.setString(7, object.getWorkPhone());
            statement.setInt(8, object.getUserRoleId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {
            Date sqlDate = convert(object.getCreationDate());
            int user_role_id = (object.getUserRoleId() == null) ? 1 : object.getUserRoleId();

            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getPassword());
            statement.setDate(4, sqlDate);
            statement.setString(5, object.getEmail());
            statement.setString(6, object.getMobilePhone());
            statement.setString(7, object.getWorkPhone());
            statement.setInt(8, user_role_id);

        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    protected java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

}
