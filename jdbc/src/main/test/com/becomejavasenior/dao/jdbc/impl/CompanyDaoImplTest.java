package com.becomejavasenior.dao.jdbc.impl;

        import com.becomejavasenior.Company;
        import com.becomejavasenior.User;
        import com.becomejavasenior.dao.CompanyDao;
        import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
        import com.becomejavasenior.dao.exception.PersistException;
        import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;

        import java.sql.Connection;
        import java.sql.SQLException;
        import java.time.LocalDateTime;
        import java.util.List;

        import static junit.framework.TestCase.assertEquals;
        import static junit.framework.TestCase.assertNotSame;
        import static junit.framework.TestCase.assertSame;

public class CompanyDaoImplTest {
    private CompanyDao<Company> companyDao = null;
    private Company newCompany = null;

    public CompanyDaoImplTest() {
    }

    @Before
    public void setUp() {
        Connection connection = ConnectionFactory.getConnection();
        companyDao = DaoFactory.getCompanyDAO();
        newCompany = new Company();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    @Test
    public void testCreateAndUpdateAndGetByPK() {
        User userCreator = new User();
        userCreator.setId(5);

        newCompany.setCompanyName("Slaves&Bosses");
        newCompany.setPhoneType(1);
        newCompany.setPhoneNumber("+737 37 37");
        newCompany.setEmail("slaves&bosses@google.com");
        newCompany.setWebsite("slaves&bosses.com");
        newCompany.setCreatedBy(userCreator);
        newCompany.setAddress("1 Infinite Loop, Cupertino, CA 95014");
        newCompany.setDeleted(false);
        newCompany.setCreationTime(LocalDateTime.now());

        //1 - create
        Company lastInsertedObject = companyDao.persist(newCompany);
        assertEquals("Slaves&Bosses", lastInsertedObject.getCompanyName());
        assertEquals("+737 37 37", lastInsertedObject.getPhoneNumber());
        assertEquals("slaves&bosses@google.com", lastInsertedObject.getEmail());
        assertSame(5, lastInsertedObject.getCreatedBy().getId());

        //2 - update
        lastInsertedObject.setCompanyName("saveyoursoul.com");
        lastInsertedObject.setCreationTime(LocalDateTime.now());
        lastInsertedObject.setEmail("saveyoursoul@google.com");
        lastInsertedObject.setPhoneNumber("+737 37 37");
        companyDao.update(lastInsertedObject);

        //3 get by PK and check update
        int id = lastInsertedObject.getId().intValue();
        Company getByPkCompany = companyDao.getByPK(Integer.valueOf(id));
        assertEquals("+737 37 37", getByPkCompany.getPhoneNumber());
        assertNotSame(4, getByPkCompany.getCreatedBy().getId());
        companyDao.delete(lastInsertedObject.getId());
    }

    @Test
    public void testGetAll() {
        List<Company> companies = companyDao.getAll();
        Assert.assertNotNull(companies);
        Assert.assertTrue(companies.size() > 0);
    }

    @Test(expected = PersistException.class)
    public void testDeleteAndException() {
        User userCreator = new User();
        userCreator.setId(5);
        newCompany.setCompanyName("Slaves&Bosses");
        newCompany.setPhoneType(1);
        newCompany.setPhoneNumber("+737 37 37");
        newCompany.setEmail("slaves&bosses@google.com");
        newCompany.setWebsite("slaves&bosses.com");
        newCompany.setCreatedBy(userCreator);
        newCompany.setAddress("1 Infinite Loop, Cupertino, CA 95014");
        newCompany.setDeleted(false);
        newCompany.setCreationTime(LocalDateTime.now());
        Company lastInsertedObject = companyDao.persist(newCompany);
        companyDao.delete(lastInsertedObject.getId());
        companyDao.getByPK(lastInsertedObject.getId());
    }
}