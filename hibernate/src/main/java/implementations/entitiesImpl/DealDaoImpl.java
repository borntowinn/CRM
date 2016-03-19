package implementations.entitiesImpl;

import com.becomejavasenior.Deal;
import com.becomejavasenior.dao.hibernatedao.DealDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class DealDaoImpl extends AbstractDaoImpl<Deal> implements DealDao<Deal> {

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
}
