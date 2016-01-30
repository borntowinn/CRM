package com.becomejavasenior;

import com.becomejavasenior.dao.PhaseDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Default71721 on 29.01.16.
 */
public class PhaseDaoImplTest {

    private Phase phase;
    private List<Phase> phases;
    private PhaseDao<Phase> phaseDao;

    @Before
    public void setupAndConnection()
    {
        Connection connection = ConnectionFactory.getConnection();
        phaseDao = DaoFactory.getPhaseDao();
        phase = new Phase();
        phase.setPhase("First");

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateDatabaseEntry()
    {
        Assert.assertEquals(phase.getPhase(), phaseDao.create(phase).getPhase());
    }

    @Test
    public void testGetByPK()
    {
        Integer id = phaseDao.create(phase).getId();
        Assert.assertEquals(phase.getPhase(), phaseDao.getByPK(id).getPhase());
    }

    @Test
    public void testGetAllEntries()
    {
        phases = phaseDao.getAll();
        Assert.assertNotNull(phases);
        Assert.assertTrue(phases.size() > 0);
    }

    @Test
    public void testUpdate()
    {
        Integer id = phaseDao.create(phase).getId();
        phase.setPhase("newPhase");
        phase.setId(id);
        phaseDao.update(phase);
        Assert.assertEquals(phase.getPhase(), phaseDao.getByPK(id).getPhase());
    }

    @Test(expected=PersistException.class)
    public void testDelete()
    {
        Integer id = phaseDao.create(phase).getId();
        phaseDao.delete(id);
        phaseDao.getByPK(id);
    }
}
