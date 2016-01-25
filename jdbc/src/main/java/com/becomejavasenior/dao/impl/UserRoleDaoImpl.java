package com.becomejavasenior.dao.impl;

import com.becomejavasenior.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserRoleDaoImpl extends AbstractJDBCDao<UserRole, Integer> {

    public UserRoleDaoImpl(Connection connection) {
        super(connection);
    }

    private class PersistUserRole extends UserRole {
        public void setId(int id) { super.setId(id); }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT user_role_id, role FROM user_role";
    }

    @Override
    public String getSelectLastInsertIdQuery() {
        return "SELECT user_role_id, role FROM user_role WHERE user_role_id=";
    }

    @Override
    public String getSelectPKQuery() {
        return "SELECT user_role_id, role FROM user_role WHERE user_role_id= ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO user_role (role) VALUES (?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE user_role SET role= ?  WHERE user_role_id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM user_role WHERE user_role_id= ?;";
    }

    @Override
    public UserRole create() throws PersistException {
        UserRole userRole = new UserRole();

        return persist(userRole);
    }

    @Override
    protected List<UserRole> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<UserRole> result = new LinkedList<UserRole>();
        try {
            while (rs.next()) {
                PersistUserRole userRole = new PersistUserRole();
                userRole.setId(rs.getInt("user_role_id"));
                userRole.setUserRole(rs.getString("role"));
                result.add(userRole);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UserRole object) throws PersistException {
        try {
            statement.setString(1,object.getUserRole());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserRole object) throws PersistException {
        try {
            statement.setString(1, object.getUserRole());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
