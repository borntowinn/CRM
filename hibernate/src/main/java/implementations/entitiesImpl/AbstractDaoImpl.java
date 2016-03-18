package implementations.entitiesImpl;

import com.becomejavasenior.dao.hibernatedao.GeneralDao;
import implementations.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public abstract class AbstractDaoImpl<T> implements GeneralDao<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDaoImpl.class);

    public abstract T getByPK(Integer id);
    public abstract List getAll();

    @Override
    public T create(T object) {
        Session session = getSession();
        session.save(object);
        commitTransaction(session);
        return object;
    }

    @Override
    public void update(T object) {
        Session session = getSession();
        session.delete(object);
        LOGGER.debug(DealDaoImpl.class);
        commitTransaction(session);
    }

    @Override
    public void delete(T object) {
        Session session = getSession();
        session.update(object);
        LOGGER.debug(DealDaoImpl.class);
        commitTransaction(session);
    }

    protected Session getSession() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }

    protected void commitTransaction(Session session) {
        session.getTransaction().commit();
    }
}
