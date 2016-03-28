package implementations.entitiesImpl;

import com.becomejavasenior.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
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
    UserDaoImpl userDao;
    User createdBy;
    UserRoleDaoImpl userRoleDao;

    @Before
    public void before() {
        dao = new ContactDaoImpl();
        companyDao = new CompanyDaoImpl();
        Contact testContact = new Contact();
        testContact.setEmail("email");
        Company company = new Company();
        company.setCompanyName("name");
        company.setWebsite("testontact");
        company.setEmail("testContact");
        companyId = companyDao.create(company);
        testContact.setCompanyId(companyId);
        tagDao = new TagDaoImpl();
        Tag newTag = new Tag();
        newTag.setTag("tag");
        newTag.getTagsToContacts().add(contact);
        tag = tagDao.create(newTag);
        testContact.getTags().add(tag);
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
        UserRole userRole = userRoleDao.getByPK(1);
        User object = new User("name", "pass", userRole, "maiiil");
        createdBy = userDao.create(object);
        testContact.setCreatedBy(createdBy);
        contact = dao.create(testContact);
        dealDao = new DealDaoImpl();
        Deal newDeal = new Deal();
        newDeal.setContact(contact);
        newDeal.setDealName("name");
        newDeal.setCompany(companyId);
        newDeal.setCreatedBy(object);
        newDeal.setBudget(new BigDecimal(1231231.12));
        newDeal.setResponsible(object);
        deal =  dealDao.create(newDeal);
    }

    @After
    public void after() {
        dealDao.delete(deal);
        dao.delete(contact);
        companyDao.delete(companyId);
        userDao.delete(createdBy);
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

    @Test
    public void testSelectTagsByContact() throws Exception {
        List<Tag> tags = dao.selectTagByContact(contact);
        Tag checkTag = tags.get(0);
        assertSame("tag", checkTag.getTag());
    }
}