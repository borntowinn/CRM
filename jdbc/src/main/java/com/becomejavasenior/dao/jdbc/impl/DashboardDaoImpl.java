package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Comment;
import com.becomejavasenior.dao.DashboardDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
import com.becomejavasenior.dao.jdbc.factory.DataSource;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Created by ipvinner on 30.01.2016.
 */
public class DashboardDaoImpl implements DashboardDao {

    private final String SELECT_DEALS = "SELECT * FROM deal";
    private final String SELECT_DEALS_Budget_SUM = "SELECT SUM(budget) from deal";
    private final String SELECT_SUCCESS_DEALS = "SELECT * FROM deal WHERE phase_id IN (SELECT phase_id FROM phase WHERE phase = 'First')";
    private final String SELECT_FAILED_DEALS = "SELECT * FROM deal WHERE phase_id IN (SELECT phase_id FROM phase WHERE phase = 'зnewPhase')";
    private final String SELECT_DEALS_WITHOUT_TASK = "SELECT * FROM deal WHERE deal_id NOT IN (SELECT deal_id from task WHERE deal_id = deal_id)";
    private final String SELECT_DEALS_WITH_TASK = "SELECT * FROM deal WHERE deal_id IN (SELECT deal_id from task WHERE deal_id = deal_id)";
    private final String SELECT_TASKS = "SELECT * FROM task";
    private final String SELECT_COMPLETED_TASKS = "SELECT * FROM task WHERE isDone";
    private final String SELECT_OVERDUE_TASKS = "SELECT * FROM task WHERE NOT isDone;";
    private final String SELECT_CONTACTS = "SELECT * FROM contact";
    private final String SELECT_COMPANIES = "SELECT * FROM company";

    private Connection connection;

    {
        try {
             connection = DataSource.getInstance().getConnection();
        } catch (SQLException e)
        {
            throw new PersistException(e);
        }
    }


    private int getCount(String sql) {
        Statement s = null;
        int count = 0;
        try {
            s = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet r = s.executeQuery(sql);
            r.last();
            count = r.getRow();
            r.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public int getDealsCount() {
        return getCount(SELECT_DEALS);
    }

    @Override
    public BigDecimal getDealsBudgetSum() {
        BigDecimal value = new BigDecimal(String.valueOf(BigDecimal.ZERO));

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_DEALS_Budget_SUM);
            ResultSet result = statement.executeQuery();
            result.next();
            value = result.getBigDecimal(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public int getSuccessDealsCount() {
        return getCount(SELECT_SUCCESS_DEALS);
    }

    @Override
    public int getFailedDealsCount() {
        return getCount(SELECT_FAILED_DEALS);
    }

    @Override
    public int getDealsWithoutTasks() {
        return getCount(SELECT_DEALS_WITHOUT_TASK);
    }

    @Override
    public int getDealsWithTasks() {
        return getCount(SELECT_DEALS_WITH_TASK);
    }

    @Override
    public int getTasksCount() {
        return getCount(SELECT_TASKS);
    }

    @Override
    public int getCompletedTasksCount() {
        return getCount(SELECT_COMPLETED_TASKS);
    }

    @Override
    public int getOverdueTasksCount() {
        return getCount(SELECT_OVERDUE_TASKS);
    }

    @Override
    public int getContactsCount() {
        return getCount(SELECT_CONTACTS);
    }

    @Override
    public int getCompaniesCount() {
        return getCount(SELECT_COMPANIES);
    }
}
