package implementations.entitiesImpl;

import com.becomejavasenior.Tag;
import com.becomejavasenior.dao.hibernatedao.TagDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class TagDaoImpl extends AbstractDaoImpl<Tag> implements TagDao<Tag> {
    private static final Logger LOGGER = Logger.getLogger(TagDaoImpl.class);

    @Override
    public Tag getByPK(Integer id) {
        Session session = getSession();
        Tag tag = (Tag) session.load(Tag.class, id);
        LOGGER.debug(TagDaoImpl.class);
        return tag;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(Tag.class).list();
    }

//    @Override
//    public List<Tag> selectTagByContact(Contact contactId) { //check it
//        Session session = getSession();
//        return session.createQuery("select t " +
//                "from com.becomejavasenior.Contact c join com.becomejavasenior.Tag t where c = :contactId").list();
//    }
}
