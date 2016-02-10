package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.SessionHistory;
import com.becomejavasenior.User;
import com.becomejavasenior.UserRole;
import com.becomejavasenior.dao.SessionHistoryDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Default71721 on 31.01.16.
 */
public class SessionHistoryDaoImpl extends AbstractJDBCDao<SessionHistory> implements SessionHistoryDao<SessionHistory> {
    private final static String SELECT_QUERY = "SELECT session_history_id, user_id, ip_address, browser, data_session FROM session_history";
    private final static String SELECT_BY_PK_QUERY = "SELECT session_history_id, user_id, ip_address, browser, data_session FROM session_history WHERE session_history_id= ?";
    private final static String CREATE_QUERY = "INSERT INTO session_history (user_id, ip_address, browser, data_session) VALUES (?, ?, ?, ?);";
    private final static String UPDATE_QUERY = "UPDATE session_history SET user_id= ?, ip_address = ?, browser = ?, data_session = ?  WHERE session_history_id= ?;";
    private final static String DELETE_QUERY = "DELETE FROM session_history WHERE session_history_id= ?;";

    private UserDao<User> userDao= DaoFactory.getUserDAO();

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
    public SessionHistory create(SessionHistory sessionHistory) throws PersistException {
        return persist(sessionHistory);
    }

    @Override
    protected List<SessionHistory> parseResultSet(ResultSet rs) throws PersistException {
        ArrayList<SessionHistory> resultList = new ArrayList<>();
        try {
            while (rs.next()) {
                SessionHistory sessionHistory = new SessionHistory();
                sessionHistory.setId(rs.getInt("session_history_id"));
                sessionHistory.setUserId(userDao.getByPK(rs.getInt("user_id")));
                sessionHistory.setIpAddress(rs.getString("ip_address"));
                sessionHistory.setCreationDate(rs.getTimestamp("data_session").toLocalDateTime());
                sessionHistory.setBrowser(rs.getString("browser"));
                resultList.add(sessionHistory);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return resultList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, SessionHistory sessionHistory) throws PersistException {
        try {
            statement.setInt(1, sessionHistory.getUserId().getId());
            statement.setString(2, sessionHistory.getIpAddress());
            statement.setString(3, sessionHistory.getBrowser());
            statement.setTimestamp(4, Timestamp.valueOf(sessionHistory.getCreationDate()));
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, SessionHistory sessionHistory) throws PersistException {
        try {
            statement.setInt(1, sessionHistory.getUserId().getId());
            statement.setString(2, sessionHistory.getIpAddress());
            statement.setString(3, sessionHistory.getBrowser());
            statement.setTimestamp(4, Timestamp.valueOf(sessionHistory.getCreationDate()));
            statement.setInt(5, sessionHistory.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
