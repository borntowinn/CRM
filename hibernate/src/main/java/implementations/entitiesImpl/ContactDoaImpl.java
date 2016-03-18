package implementations.entitiesImpl;

import com.becomejavasenior.Comment;
import com.becomejavasenior.Contact;
import com.becomejavasenior.File;
import com.becomejavasenior.dao.hibernatedao.ContactDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class ContactDoaImpl extends AbstractDaoImpl implements ContactDao{

    private static final Logger LOGGER = Logger.getLogger(ContactDoaImpl.class);

    @Override
    public Comment getByPK(Integer id) {
        Session session = getSession();
        Comment comment = (Comment) session.load(Comment.class, id);
        LOGGER.debug(ContactDoaImpl.class);
        commitTransaction(session);
        return comment;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(Comment.class).list();
    }

    @Override
    public void addCommentToContact(Comment comment, Contact contact_id) {
        Session session = getSession();
        comment.setContactId(contact_id); //need to avoid
        comment.setCreationDate(LocalDateTime.now());
        session.save(comment);
        commitTransaction(session);
    }

    @Override
    public void addFileToContact(File file, int contact_id) {
        // figure out: why do we need this method here? move it to FileDaoImpl
    }

    @Override
    public List<String> getAllTags() {
        // figure out: why do we need this method here? move it to TagDaoImpl
        return null;
    }
}
