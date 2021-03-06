package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Company;
import com.becomejavasenior.Contact;
import com.becomejavasenior.User;
import com.becomejavasenior.dao.ContactDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by valkos on 02.02.16.
 */
public class ContactDaoImplTest {
    private static ContactDao<Contact> contactDao;

    @BeforeClass
    public static void beforeScenario()
    {
        contactDao = DaoFactory.getContactDAO();

    }



    @Test
    public void getByPKTest()
    {
        Contact newContact = new Contact();
        testInformation(newContact);
        Contact testContact = contactDao.create(newContact);

        Integer id = testContact.getId();
        assertEquals(id, contactDao.getByPK(id).getId());

        contactDao.delete(testContact.getId());

    }

    @Test
    public void createTest()
    {
        Contact newContact = new Contact();
        testInformation(newContact);
        Contact testContact = contactDao.persist(newContact);

        assertEquals("some name", testContact.getNameSurname());
        assertEquals("phone", testContact.getPhoneNumber());
        assertEquals("skype1", testContact.getSkype());
        assertSame(1, testContact.getCreatedBy().getId());
        assertSame(1, testContact.getResponsible().getId());

        contactDao.delete(testContact.getId());
    }

    @Test(expected = PersistException.class)
    public void testDeleteContact() {
        Contact newContact = new Contact();
        testInformation(newContact);
        Contact testContact = contactDao.persist(newContact);
        Integer testContactId = testContact.getId();
        contactDao.delete(testContactId);

        contactDao.getByPK(testContactId);
    }

    @Test
    public void updateContact() {
        Contact newContact = new Contact();
        testInformation(newContact);
        Integer id = contactDao.create(newContact).getId();
        newContact.setId(id);
        newContact.setPhoneType(3);
        contactDao.update(newContact);

        assertSame(3, contactDao.getByPK(id).getPhoneType());
        assertSame(1, contactDao.getByPK(id).getResponsible().getId());
    }

    @Test
    public void testGetAllRecords() {
        List<Contact> contacts = contactDao.getAll();

        assertNotNull(contacts);
        assertTrue(contacts.size() > 0);
    }

    private void testInformation(Contact contact) {
        Company company = new Company();
        User user = new User();
        company.setId(1);
        user.setId(1);
        contact.setNameSurname("some name");
        contact.setPhoneType(1);
        contact.setPhoneNumber("phone");
        contact.setEmail("email");
        contact.setSkype("skype1");
        contact.setPosition("admin");
        contact.setDeleted(false);
        contact.setCreationTime(LocalDateTime.now());
        contact.setCompanyId(company);
        contact.setCreatedBy(user);
        contact.setResponsible(user);
    }

}