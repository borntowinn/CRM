package implementations.entitiesImpl;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.hibernatedao.ContactDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class ContactDaoImpl extends AbstractDaoImpl<Contact> implements ContactDao<Contact>{

    private static final Logger LOGGER = Logger.getLogger(ContactDaoImpl.class);

    @Override
    public Contact getByPK(Integer id) {
        Session session = getSession();
        Contact contact = (Contact) session.load(Contact.class, id);
        LOGGER.debug(ContactDaoImpl.class + "get comment by PK, getByPK method");
        return contact;
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(Comment.class).list();
    }

    public Company selectCompanyByContactId(int contactId) {
        Contact contact = getByPK(contactId);
        return contact.getCompanyId();
    }

    public List<Deal> selectDealByContactId(int contactId) {
        Contact contact = getByPK(contactId);
        return contact.getDeals();
    }

    public List<Tag> selectTagByContact(Contact contact) {
        return contact.getTags();
    }

    @Override
    public void addFileToContact(File file, int contactId) {
        // @TODO figure out: why do we need this method here? move it to FileDaoImpl
    }

    @Override
    public List<String> getAllTags() {
        // @TODO  figure out: why do we need this method here? move it to TagDaoImpl
        return null;
    }
}
