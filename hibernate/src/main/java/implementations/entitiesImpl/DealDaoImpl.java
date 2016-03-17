package implementations.entitiesImpl;

import com.becomejavasenior.Deal;
import com.becomejavasenior.dao.hibernatedao.DealDao;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DealDaoImpl extends AbstractDaoImpl implements DealDao {

    private static final Logger LOGGER = Logger.getLogger(DealDaoImpl.class);

    public Deal getByPK(Integer id) {
        Session session = getSession();
        session.beginTransaction();
        Deal deal = (Deal) session.load(Deal.class, id);
        LOGGER.debug(DealDaoImpl.class);
        commitTransaction(session);
        return deal;
    }

    public List getAll() {
        return getSession().createCriteria(Deal.class).list();
    }

    @Override
    public List selectDealByContactId(int contactId) {
        Session session = getSession();
        session.beginTransaction();
        Criteria allDeals = getSession().createCriteria(Deal.class);
        List deals = allDeals.add(Restrictions.eq("contact_id", contactId)).list();
        LOGGER.debug(DealDaoImpl.class);
        commitTransaction(session);
        return deals;
    }
}
