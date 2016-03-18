package implementations.entitiesImpl;

import com.becomejavasenior.User;
import com.becomejavasenior.dao.hibernatedao.UserDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao{
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    @Override
    public User getByPK(Integer id) {
        Session session = getSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, id);
        LOGGER.debug(UserDaoImpl.class);
        commitTransaction(session);
        return user;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(User.class).list();
    }
}
