package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Contact;
import com.becomejavasenior.Deal;
import com.becomejavasenior.dao.CompanyDao;
import com.becomejavasenior.dao.DealDao;
import com.becomejavasenior.dao.PhaseDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.jdbc.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Default71721 on 28.01.16.
 */
public class DealDaoImpl extends AbstractJDBCDao<Deal> implements DealDao {
    private static final String SELECT_QUERY = "SELECT deal_id, createdby, budget, phase_id, responsible, date_creation, company_id, contact_id, isdeleted FROM deal";
    private static final String SELECT_BY_PK = "SELECT deal_id, createdby, budget, phase_id, responsible, date_creation, company_id, contact_id, isdeleted FROM deal WHERE deal_id = ?";
    private static final String CREATE_QUERY = "INSERT INTO deal (createdby, budget, phase_id, responsible, date_creation, company_id, contact_id, isdeleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE deal SET createdby = ?, budget = ?, phase_id  = ?, responsible = ?, date_creation = ?, company_id = ?, contact_id = ?, isdeleted = ? WHERE deal_id=?";
    private static final String DELETE_QUERY = "DELETE FROM deal WHERE deal_id= ?;";

    private UserDao userDao = DaoFactory.getUserDAO();
    private PhaseDao phaseDao = DaoFactory.getPhaseDao();
    private CompanyDao companyDao = DaoFactory.getCompanyDAO();

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
        return SELECT_BY_PK;
    }

    @Override
    protected List<Deal> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Deal> resultList = new LinkedList<>();
        Contact contact = new Contact();
        contact.setId(1);
        try {
            while (rs.next()) {
                Deal deal = new Deal();
                deal.setId(rs.getInt("deal_id"));
                deal.setCreatedBy(userDao.getByPK(rs.getInt("createdby")));
                deal.setBudget(rs.getBigDecimal("budget"));
                deal.setPhase(phaseDao.getByPK(rs.getInt("phase_id")));
                deal.setResponsible(userDao.getByPK(rs.getInt("responsible")));
                deal.setCreationDate(rs.getTimestamp("date_creation"));
                deal.setCompany(companyDao.getByPK(rs.getInt("company_id")));
                //deal.setContact has to be changed as soon as ContactDao will be implemented
                deal.setContact(contact);
                deal.setDeleted(rs.getBoolean("isdeleted"));
                resultList.add(deal);
            }
        }
        catch (SQLException e)
        {
            throw new PersistException();
        }
        return resultList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Deal deal) throws PersistException {
        try {
            Timestamp sqlDate = convert(deal.getCreationDate());
           // statement.setInt(1, object.getId());
            statement.setInt(1, deal.getCreatedBy().getId());
            statement.setBigDecimal(2, deal.getBudget());
            statement.setInt(3, deal.getPhase().getId());
            statement.setInt(4, deal.getResponsible().getId());
            statement.setTimestamp(5, sqlDate);
            statement.setInt(6, deal.getCompany().getId());
            //next line should be edited when ContactDao is available
            statement.setInt(7, 3);
            statement.setBoolean(8, deal.getDeleted());
        }
        catch (SQLException e)
        {
            throw new PersistException();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Deal deal) throws PersistException {
        try {
            Timestamp sqlDate = convert(deal.getCreationDate());
            statement.setInt(1, deal.getCreatedBy().getId());
            statement.setBigDecimal(2, deal.getBudget());
            statement.setInt(3, deal.getPhase().getId());
            statement.setInt(4, deal.getResponsible().getId());
            statement.setTimestamp(5, sqlDate);
            statement.setInt(6, deal.getCompany().getId());
            //next line should be edited when ContactDao is available
            statement.setInt(7, 3);
            statement.setBoolean(8, deal.getDeleted());
            statement.setInt(9, deal.getId());
        }
        catch (SQLException e)
        {
            throw new PersistException();
        }
    }

    @Override
    public Deal create(Deal deal) {
        return persist(deal);
    }

    protected java.sql.Timestamp convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Timestamp(date.getTime());
    }
}
