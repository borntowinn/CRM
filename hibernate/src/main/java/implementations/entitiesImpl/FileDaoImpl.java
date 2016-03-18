package implementations.entitiesImpl;

import com.becomejavasenior.File;
import com.becomejavasenior.dao.hibernatedao.FileDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class FileDaoImpl extends AbstractDaoImpl implements FileDao{
    private static final Logger LOGGER = Logger.getLogger(FileDaoImpl.class);
    @Override
    public File getByPK(Integer id) {
        Session session = getSession();
        session.beginTransaction();
        File file = (File) session.load(File.class, id);
        LOGGER.debug(FileDaoImpl.class);
        commitTransaction(session);
        return file;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(File.class).list();
    }
}
