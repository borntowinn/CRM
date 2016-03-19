package implementations.entitiesImpl;

import com.becomejavasenior.UserRole;
import com.becomejavasenior.dao.hibernatedao.UserRoleDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class UserRoleDaoImpl extends AbstractDaoImpl<UserRole> implements UserRoleDao<UserRole> {
    private static final Logger LOGGER = Logger.getLogger(UserRoleDaoImpl.class);

    @Override
    public UserRole getByPK(Integer id) {
        Session session = getSession();
        UserRole userRole = (UserRole) session.load(UserRole.class, id);
        LOGGER.debug(UserRoleDaoImpl.class + "get user role by PK, getByPK method");
        return userRole;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(UserRole.class).list();
    }
}
