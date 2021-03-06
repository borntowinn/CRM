package implementations.entitiesImpl;

import com.becomejavasenior.SessionHistory;
import com.becomejavasenior.dao.hibernatedao.SessionHistoryDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class SessionHistoryDaoImpl extends AbstractDaoImpl<SessionHistory> implements SessionHistoryDao<SessionHistory> {
    private static final Logger LOGGER = Logger.getLogger(SessionHistoryDaoImpl.class);
    @Override
    public SessionHistory getByPK(Integer id) {
        Session session = getSession();
        SessionHistory sessionHistory = (SessionHistory) session.load(SessionHistory.class, id);
        LOGGER.debug(DealDaoImpl.class + "get session history by PK, getByPK method");
        return sessionHistory;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(SessionHistory.class).list();
    }
}
