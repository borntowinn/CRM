package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Phase;
import com.becomejavasenior.dao.PhaseDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Default71721 on 29.01.16.
 */
public class PhaseDaoImplTest {

    private static Phase phase;
    private List<Phase> phases;
    private static PhaseDao<Phase> phaseDao;

    @BeforeClass
    public static void setupAndConnection()
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
    public void createDbEntry_Phase_PhaseFromDb()
    {
        Assert.assertEquals(phase.getPhase(), phaseDao.create(phase).getPhase());
    }

    @Test
    public void getRecordByPK_Phase_PhaseFromDbByPK()
    {
        //when
        Integer id = phaseDao.create(phase).getId();

        //then
        Assert.assertEquals(phase.getPhase(), phaseDao.getByPK(id).getPhase());
    }

    @Test
    public void getAllRecordsTest()
    {
        //when
        phases = phaseDao.getAll();

        //then
        Assert.assertNotNull(phases);
        Assert.assertTrue(phases.size() > 0);
    }

    @Test
    public void updateRecord_NewPhase_NewPhaseExpectedFromDb()
    {
        //when
        Integer id = phaseDao.create(phase).getId();
        phase.setPhase("newPhase");
        phase.setId(id);
        phaseDao.update(phase);

        //then
        Assert.assertEquals(phase.getPhase(), phaseDao.getByPK(id).getPhase());
    }

    @Test(expected=PersistException.class)
    public void deleteRecord_PersistExceptionIsExpected()
    {
        //when
        Integer id = phaseDao.create(phase).getId();
        phaseDao.delete(id);

        //then -> exception must be thrown == the record was successfully deleted and can't be accessed anymore
        phaseDao.getByPK(id);
    }
}
