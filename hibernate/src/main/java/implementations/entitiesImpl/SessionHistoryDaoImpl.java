package implementations.entitiesImpl;

import com.becomejavasenior.SessionHistory;
import com.becomejavasenior.dao.hibernatedao.SessionHistoryDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class SessionHistoryDaoImpl extends AbstractDaoImpl implements SessionHistoryDao {
    private static final Logger LOGGER = Logger.getLogger(SessionHistoryDaoImpl.class);
    @Override
    public SessionHistory getByPK(Integer id) {
        Session session = getSession();
        SessionHistory sessionHistory = (SessionHistory) session.load(SessionHistory.class, id);
        LOGGER.debug(DealDaoImpl.class);
        commitTransaction(session);
        return sessionHistory;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(SessionHistory.class).list();
    }
}
