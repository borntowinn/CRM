package implementations.entitiesImpl;

import com.becomejavasenior.File;
import com.becomejavasenior.dao.hibernatedao.FileDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class FileDaoImpl extends AbstractDaoImpl<File> implements FileDao<File>{
    private static final Logger LOGGER = Logger.getLogger(FileDaoImpl.class);
    @Override
    public File getByPK(Integer id) {
        Session session = getSession();
        File file = (File) session.load(File.class, id);
        LOGGER.debug(FileDaoImpl.class + "get file by PK, getByPK method");
        return file;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(File.class).list();
    }
}
