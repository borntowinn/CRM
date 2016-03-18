package implementations.entitiesImpl;

import com.becomejavasenior.User;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.hibernatedao.UserDao;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao{
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    @Override
    public User getByPK(Integer id) {
        Session session = getSession();
        User user = (User) session.load(User.class, id);
        LOGGER.debug(UserDaoImpl.class);
        commitTransaction(session);
        return user;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(User.class).list();
    }

    @Override
    public User getByEmail(String email) {
        Criteria getUsers = getSession().createCriteria(User.class);
        List users = getUsers.add(Restrictions.eq("email", email)).list();
        if (users.size() > 1) {
            LOGGER.warn("Received more than one record.");
            throw new PersistException("Received more than one record.");
        }
        return (User) users.get(0);
    }
}
