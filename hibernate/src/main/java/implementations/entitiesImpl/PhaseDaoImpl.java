package implementations.entitiesImpl;

import com.becomejavasenior.Phase;
import com.becomejavasenior.dao.hibernatedao.PhaseDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class PhaseDaoImpl extends AbstractDaoImpl implements PhaseDao{
    private static final Logger LOGGER = Logger.getLogger(PhaseDaoImpl.class);
    @Override
    public Phase getByPK(Integer id) {
        Session session = getSession();
        Phase phase = (Phase) session.load(Phase.class, id);
        LOGGER.debug(PhaseDaoImpl.class + "get phase by PK, getByPK method");
        return phase;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(Phase.class).list();
    }
}
