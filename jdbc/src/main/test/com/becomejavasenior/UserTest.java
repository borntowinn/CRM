package com.becomejavasenior;

import com.becomejavasenior.dao.DaoFactory;
import com.becomejavasenior.dao.GenericDao;
import com.becomejavasenior.dao.impl.DaoFactoryImpl;
import com.becomejavasenior.dao.impl.PersistException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

public class UserTest {

    private User newUser = null;
    private List<User> users = null;
    private GenericDao userDao =null;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        DaoFactory<Connection> factory = new DaoFactoryImpl();
        Connection connection = factory.getContext();
        newUser = new User();
        userDao = factory.getDao(connection, User.class);
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateAndUpdateAndGetByPK(){
        try {
            UserRole userRole = new UserRole();
            userRole.setId(1);

            newUser.setName("Glen");
            newUser.setPassword("!(*@&!*(@&!*(@!@)(*");
            newUser.setDescription("DESCRIPTION");
            newUser.setCreationDate(new Date());
            newUser.setEmail("johnson@gmail.com");
            newUser.setMobilePhone("+666 66 66");
            newUser.setWorkPhone("+777 77 77");
            newUser.setUserRole(userRole);
            newUser.setLanguage(1);

            //1 - create
            User lastInsertedObject = (User) userDao.persist(newUser);
            assertEquals("Glen", lastInsertedObject.getName());
            assertEquals("!(*@&!*(@&!*(@!@)(*", lastInsertedObject.getPassword());
            assertEquals("+777 77 77", lastInsertedObject.getWorkPhone());
            assertSame(1, lastInsertedObject.getUserRole().getId());

            //2 - update
            lastInsertedObject.setName("Maria");
            lastInsertedObject.setCreationDate(new Date());
            lastInsertedObject.setEmail("maria.johnson@yahoo.com");
            lastInsertedObject.setMobilePhone("248-8-248");
            userDao.update(lastInsertedObject);

            //3 - get by PK and check update
            int id = lastInsertedObject.getId();
            User getByPkUser = (User) userDao.getByPK(id);
            assertEquals("248-8-248", getByPkUser.getMobilePhone());
            assertNotSame(2, getByPkUser.getUserRole().getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Test
    public void testGetAll(){
        users = null;
        try {
            users = userDao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);
    }

    @Test
    public void testDeleteAndException(){
        try {
            //create

            UserRole userRole = new UserRole();
            userRole.setId(1);
            newUser.setName("Oven");
            newUser.setPassword("pass");
            newUser.setDescription("TEST DESC");
            newUser.setCreationDate(new Date());
            newUser.setEmail("oven@gmail.com");
            newUser.setMobilePhone("+6 66 66");
            newUser.setWorkPhone("+7 77 77");
            newUser.setUserRole(userRole);
            newUser.setLanguage(1);
            User lastInsertedObject = (User) userDao.persist(newUser);

            //delete
            userDao.delete(lastInsertedObject.getId());

            //getByPK - PersistException
            userDao.getByPK(lastInsertedObject.getId());
            thrown.expect(PersistException.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
