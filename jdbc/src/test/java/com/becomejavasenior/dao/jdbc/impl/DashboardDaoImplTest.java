package com.becomejavasenior.dao.jdbc.impl;


import com.becomejavasenior.dao.DashboardDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import com.becomejavasenior.dao.jdbc.factory.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by ipvinner on 30.01.2016.
 */
public class DashboardDaoImplTest {

    private static DashboardDao dashboardDao = null;

    @BeforeClass
    public static void setUp() {
        try {
            Connection connection =DataSource.getInstance().getConnection();
        } catch (SQLException e)
        {
            throw new PersistException(e);
        }
        dashboardDao = DaoFactory.getDashboardDao();
    }

    @Test
    public void testGetDealsCount() throws Exception {
        int count = dashboardDao.getDealsCount();
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testGetDealsBudgetSum() throws Exception {
        BigDecimal sum = dashboardDao.getDealsBudgetSum();
        Assert.assertTrue(sum.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    public void testGetSuccessDealsCount() throws Exception {
        int count = dashboardDao.getSuccessDealsCount();
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testGetFailedDealsCount() throws Exception {
        int count = dashboardDao.getFailedDealsCount();
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testGetDealsWithoutTasks() throws Exception {
        int count = dashboardDao.getDealsWithoutTasks();
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testGetDealsWithTasks() throws Exception {
        int count = dashboardDao.getDealsWithTasks();
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testGetTasksCount() throws Exception {
        int count = dashboardDao.getTasksCount();
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testGetCompletedTasksCount() throws Exception {

    }

    @Test
    public void testGetOverdueTasksCount() throws Exception {

    }

    @Test
    public void testGetContactsCount() throws Exception {
        int count = dashboardDao.getContactsCount();
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testGetCompaniesCount() throws Exception {
        int count = dashboardDao.getCompaniesCount();
        Assert.assertTrue(count > 0);
    }
}