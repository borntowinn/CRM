package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Company;
import com.becomejavasenior.File;
import com.becomejavasenior.User;
import com.becomejavasenior.dao.CompanyDao;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
import com.becomejavasenior.dao.jdbc.factory.DataSource;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

public class CompanyDaoImplTest {
    private static CompanyDao<Company> companyDao;
    private static DataSource dataSource = DataSource.getInstance();

    public CompanyDaoImplTest() {
    }

    @BeforeClass
    public static void setUpConnection() {
        companyDao = DaoFactory.getCompanyDAO();
        try (Connection connection = dataSource.getConnection()){
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getByPKTest() {
        //when
        Company newCompany = new Company();
        testPreparation(newCompany);
        Company lastInsertedObject = companyDao.persist(newCompany);
        Integer id = lastInsertedObject.getId();

        //then
        Assert.assertEquals(id, companyDao.getByPK(id).getId());

        //remove testing data from the database
        companyDao.delete(lastInsertedObject.getId());
    }

    @Test
    public void testCreateCompany() {
        //when
        Company newCompany = new Company();
        testPreparation(newCompany);
        Company lastInsertedObject = companyDao.persist(newCompany);

        //then
        assertEquals("Slaves&Bosses", lastInsertedObject.getCompanyName());
        assertEquals("+737 37 37", lastInsertedObject.getPhoneNumber());
        assertEquals("slaves&bosses@google.com", lastInsertedObject.getEmail());
        assertSame(5, lastInsertedObject.getResponsible().getId());
        assertSame(5, lastInsertedObject.getCreatedBy().getId());

        //remove testing data from the database
        companyDao.delete(lastInsertedObject.getId());
    }

    @Test(expected = PersistException.class)
    public void testDeleteCompany() {
        //when
        Company newCompany = new Company();
        testPreparation(newCompany);
        Company lastInsertedObject = companyDao.persist(newCompany);
        Integer lasInsertedObjectId = lastInsertedObject.getId();
        companyDao.delete(lasInsertedObjectId);

        //then -> exception must be thrown == the record was successfully deleted and can't be accessed anymore
        companyDao.getByPK(lasInsertedObjectId);
    }

    @Test
    public void testUpdateCompany() {
        //before
        Company newCompany = new Company();
        testPreparation(newCompany);
        Company lastInsertedObject = companyDao.persist(newCompany);

        //when (update company data)
        lastInsertedObject.setCompanyName("saveyoursoul.com");
        lastInsertedObject.setEmail("saveyoursoul@google.com");
        lastInsertedObject.setPhoneNumber("+737 77 77");
        companyDao.update(lastInsertedObject);
        int id = lastInsertedObject.getId();
        Company getByPkCompany = companyDao.getByPK(id);

        //then
        assertEquals("saveyoursoul.com", getByPkCompany.getCompanyName());
        assertEquals("saveyoursoul@google.com", getByPkCompany.getEmail());
        assertEquals("+737 77 77", getByPkCompany.getPhoneNumber());

        //remove testing data from the database
        companyDao.delete(id);
    }

    @Test
    public void testGetAllRecords() {
        //when
        List<Company> companies = companyDao.getAll();

        //then
        Assert.assertNotNull(companies);
        Assert.assertTrue(companies.size() > 0);
    }

    private void testPreparation(Company newCompany) {
        //set some testing data to the newCompany object
        User userCreator = new User();
        userCreator.setId(5);
        newCompany.setCompanyName("Slaves&Bosses");
        newCompany.setResponsible(userCreator);
        newCompany.setPhoneNumber("+737 37 37");
        newCompany.setEmail("slaves&bosses@google.com");
        newCompany.setWebsite("slaves&bosses.com");
        newCompany.setCreatedBy(userCreator);
        newCompany.setAddress("1 Infinite Loop, Cupertino, CA 95014");
        newCompany.setDeleted(false);
        newCompany.setCreationTime(LocalDateTime.now());
    }
}