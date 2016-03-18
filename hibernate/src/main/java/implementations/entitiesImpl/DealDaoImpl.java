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
        Deal deal = (Deal) session.load(Deal.class, id);
        LOGGER.debug(DealDaoImpl.class + "get deal by PK, getByPK method");
        return deal;
    }

    public List getAll() {
        return getSession().createCriteria(Deal.class).list();
    }

    @Override
    public List<Deal> selectDealByContactId(int contactId) {
        Criteria allDeals = getSession().createCriteria(Deal.class);
        List<Deal> deals = allDeals.add(Restrictions.eq("contact_id", contactId)).list();
        LOGGER.debug(DealDaoImpl.class + "selectDealByContactId method");
        return deals;
    }
}
