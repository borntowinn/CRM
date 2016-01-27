package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.UserRole;
import com.becomejavasenior.dao.UserRoleDao;
import com.becomejavasenior.dao.jdbc.exception.PersistException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserRoleDaoImpl extends AbstractJDBCDao<UserRole> implements UserRoleDao {

    private final static String SELECT_QUERY = "SELECT user_role_id, role FROM user_role";
    private final static String LAST_INSERT_QUERY = "SELECT user_role_id, role FROM user_role WHERE user_role_id=";
    private final static String LAST_INSERT_ID_QUERY = "SELECT user_role_id, role FROM user_role WHERE user_role_id= ?";
    private final static String CREATE_QUERY = "INSERT INTO user_role (role) VALUES (?);";
    private final static String UPDATE_QUERY = "UPDATE user_role SET role= ?  WHERE user_role_id= ?;";
    private final static String DELETE_QUERY = "DELETE FROM user_role WHERE user_role_id= ?;";

    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getSelectLastInsertIdQuery() {
        return LAST_INSERT_QUERY;
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
    public UserRole create(UserRole userRole) throws PersistException {
        return persist(userRole);
    }

    @Override
    protected List<UserRole> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<UserRole> result = new LinkedList<UserRole>();
        try {
            while (rs.next()) {
                UserRole userRole = new UserRole();
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