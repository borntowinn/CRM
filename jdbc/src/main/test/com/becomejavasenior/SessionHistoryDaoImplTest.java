package com.becomejavasenior;

import com.becomejavasenior.dao.SessionHistoryDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Default71721 on 31.01.16.
 */
public class SessionHistoryDaoImplTest {
    private SessionHistory sessionHistory;
    private User user;
    private List<SessionHistory> sessionHistories;
    private SessionHistoryDao<SessionHistory> sessionHistoryDao;

    @Before
    public void setupAndConnection()
    {
        Connection connection = ConnectionFactory.getConnection();
        sessionHistoryDao = DaoFactory.getSessionHistoryDao();
        user = new User();
        user.setId(1);
        sessionHistory = new SessionHistory();
        sessionHistory.setBrowser("Opera");
        sessionHistory.setCreationDate(LocalDateTime.now());
        sessionHistory.setUserId(user);
        sessionHistory.setIpAddress("192.168.0.1");

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createDbEntry_SessionHistory_LocalSessionHistory()
    {
        //when
        SessionHistory localSessionHistory = sessionHistoryDao.create(sessionHistory);

        //then
        Assert.assertEquals(sessionHistory.getBrowser(), localSessionHistory.getBrowser());
        Assert.assertEquals(sessionHistory.getUserId().getId(), localSessionHistory.getUserId().getId());
        Assert.assertTrue(sessionHistory.getIpAddress().equals(localSessionHistory.getIpAddress()));
    }

    @Test
    public void getRecordByPK_Phase_PhaseFromDbByPK()
    {
        //when
        Integer id = sessionHistoryDao.create(sessionHistory).getId();

        //then
        Assert.assertEquals(sessionHistory.getUserId().getId(), sessionHistoryDao.getByPK(id).getUserId().getId());
        Assert.assertTrue(sessionHistory.getBrowser().equals(sessionHistoryDao.getByPK(id).getBrowser()));
        Assert.assertTrue(sessionHistory.getIpAddress().equals(sessionHistoryDao.getByPK(id).getIpAddress()));
        Assert.assertEquals(sessionHistory.getCreationDate(), sessionHistoryDao.getByPK(id).getCreationDate());
    }

    @Test
    public void getAllRecordsTest()
    {
        //when
        sessionHistories = sessionHistoryDao.getAll();

        //then
        Assert.assertNotNull(sessionHistories);
        Assert.assertTrue(sessionHistories.size() > 0);
    }

    @Test
    public void updateRecord_NewPhase_NewPhaseExpectedFromDb()
    {
        //when
        Integer id = sessionHistoryDao.create(sessionHistory).getId();
        sessionHistory.setBrowser("newBrowser");
        sessionHistory.setId(id);
        sessionHistoryDao.update(sessionHistory);

        //then
        Assert.assertTrue(sessionHistory.getBrowser().equals(sessionHistoryDao.getByPK(id).getBrowser()));
    }

    @Test(expected=PersistException.class)
    public void deleteRecord_PersistExceptionIsExpected()
    {
        //when
        Integer id = sessionHistoryDao.create(sessionHistory).getId();
        sessionHistoryDao.delete(id);

        //then -> exception must be thrown == the record was successfully deleted and can't be accessed anymore
        sessionHistoryDao.getByPK(id);
    }
}