package implementations.entitiesImpl;

import com.becomejavasenior.Company;
import com.becomejavasenior.Contact;
import com.becomejavasenior.Deal;
import com.becomejavasenior.Tag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ContactDaoImplTest {

    ContactDaoImpl dao;
    Contact contact;
    CompanyDaoImpl companyDao;
    Company companyId;
    DealDaoImpl dealDao;
    Deal deal;
    TagDaoImpl tagDao;
    Tag tag;

    @Before
    public void before() {
        dao = new ContactDaoImpl();
        companyDao = new CompanyDaoImpl();
        Contact testContact = new Contact();
        testContact.setEmail("email");
        Company company = new Company();
        company.setWebsite("testontact");
        company.setEmail("testContact");
        companyId = companyDao.create(company);
        testContact.setCompanyId(companyId);
//        tagDao = new TagDaoImpl();
//        Tag newTag = new Tag();
//        newTag.setTag("tag");
//        tag = tagDao.create(newTag);
//        List<Tag> tags = new LinkedList<>();
//        tags.add(tag);
//        contact.setTags(tags);
        contact = dao.create(testContact);
        dealDao = new DealDaoImpl();
        Deal newDeal = new Deal();
        newDeal.setContact(contact);
        newDeal.setDealName("name");
        newDeal.setCompany(companyId);
        deal =  dealDao.create(newDeal);
    }

    @After
    public void after() {
        dealDao.delete(deal);
//        tagDao.delete(tag);
        dao.delete(contact);
        companyDao.delete(companyId);
    }

    @Test
    public void testSelectCompanyByContactId() throws Exception {
        Company newCompany = dao.selectCompanyByContactId(contact.getId());
        assertEquals(newCompany.getId(), companyDao.getByPK(companyId.getId()).getId());
    }

    @Test
    public void testSelectDealsByContactId() throws Exception {
        List<Deal> deals = dao.selectDealByContactId(contact.getId());
        assertTrue(deals.size() > 0);
    }

//    @Test
//    public void testSelectTagsByContact() throws Exception {
//        List<Tag> tags = dao.selectTagByContact(contact);
//        Tag checkTag = tags.get(0);
//        assertSame("tag", checkTag.getTag());
//    }
}