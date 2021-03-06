package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Phase;
import com.becomejavasenior.dao.PhaseDao;
import com.becomejavasenior.dao.exception.PersistException;
import org.apache.log4j.Logger;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Default71721 on 29.01.16.
 */
public class PhaseDaoImpl extends AbstractJDBCDao<Phase> implements PhaseDao<Phase> {

    private static final Logger log = Logger.getLogger(PhaseDaoImpl.class);

    private final static String SELECT_QUERY = "SELECT phase_id, phase FROM phase";
    private final static String SELECT_BY_PK_QUERY = "SELECT phase_id, phase FROM phase WHERE phase_id= ?";
    private final static String CREATE_QUERY = "INSERT INTO phase (phase) VALUES (?);";
    private final static String UPDATE_QUERY = "UPDATE phase SET phase= ?  WHERE phase_id= ?;";
    private final static String DELETE_QUERY = "DELETE FROM phase WHERE phase_id= ?;";

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
    public Phase create(Phase phase) {
        return persist(phase);
    }

    @Override
    protected List<Phase> parseResultSet(ResultSet rs) throws PersistException {
        ArrayList<Phase> resultList = new ArrayList<Phase>();
        try {
            while (rs.next()) {
                Phase phase = new Phase();
                phase.setId(rs.getInt("phase_id"));
                phase.setPhase(rs.getString("phase"));
                resultList.add(phase);
            }
        } catch (SQLException e) {
            log.error("result has not parsed " + e);
            throw new PersistException(e);
        }
        return resultList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Phase object) throws PersistException {
        try {
            statement.setString(1, object.getPhase());
        } catch (SQLException e) {
            log.error("couldn't prepared Statement for Insert Phase " + e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Phase object) throws PersistException {
        try {
            statement.setString(1, object.getPhase());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            log.error("couldn't prepared Statement for Update " + this.getClass().getName() + e);
            throw new PersistException(e);
        }
    }
}
