package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.User;
import com.becomejavasenior.UserRole;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import com.becomejavasenior.dao.exception.PersistException;
import org.junit.*;

import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

public class UserTest {

    private static User newUser = null;
    private List<User> users = null;
    private static UserDao userDao =null;

    @BeforeClass
    public static void setUp() {
        userDao = DaoFactory.getUserDAO();
        newUser = new User();
    }

    @Test
    public void testCreateAndUpdateAndGetByPK(){
        UserRole userRole = new UserRole();
        userRole.setId(1);

        newUser.setName("Glen");
        newUser.setPassword("!(*@&!*(@&!*(@!@)(*");
        newUser.setDescription("DESCRIPTION");
        newUser.setCreationDate(LocalDateTime.now());
        newUser.setEmail("johnson@gmail.com");
        newUser.setMobilePhone("+666 66 66");
        newUser.setWorkPhone("+777 77 77");
        newUser.setUserRole(userRole);
        newUser.setPasswordSalt("1234");
        newUser.setLanguage(1);

        //1 - create
        User lastInsertedObject = (User) userDao.persist(newUser);
        assertEquals("Glen", lastInsertedObject.getName());
        assertEquals("!(*@&!*(@&!*(@!@)(*", lastInsertedObject.getPassword());
        assertEquals("1234", lastInsertedObject.getPasswordSalt());
        assertEquals("+777 77 77", lastInsertedObject.getWorkPhone());
        assertSame(1, lastInsertedObject.getUserRole().getId());

        //2 - update
        lastInsertedObject.setName("Maria");
        lastInsertedObject.setCreationDate(LocalDateTime.now());
        lastInsertedObject.setEmail("maria.johnson@yahoo.com");
        lastInsertedObject.setMobilePhone("248-8-248");
        lastInsertedObject.setPasswordSalt("1234");
        userDao.update(lastInsertedObject);

        //3 - get by PK and check update
        int id = lastInsertedObject.getId();
        User getByPkUser = (User) userDao.getByPK(id);
        assertEquals("248-8-248", getByPkUser.getMobilePhone());
        assertEquals("1234", getByPkUser.getPasswordSalt());
        assertNotSame(2, getByPkUser.getUserRole().getId());
    }

    @Test
    public void testGetAll(){
        users = userDao.getAll();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);
    }

    @Test(expected = PersistException.class)
    public void testDeleteAndException(){
        //create
        UserRole userRole = new UserRole();
        userRole.setId(1);
        newUser.setName("Oven");
        newUser.setPassword("pass");
        newUser.setDescription("TEST DESC");
        newUser.setCreationDate(LocalDateTime.now());
        newUser.setEmail("oven@gmail.com");
        newUser.setMobilePhone("+6 66 66");
        newUser.setWorkPhone("+7 77 77");
        newUser.setPasswordSalt("1234");
        newUser.setUserRole(userRole);
        newUser.setLanguage(1);
        User lastInsertedObject = (User) userDao.persist(newUser);

        //delete
        userDao.delete(lastInsertedObject.getId());

        //getByPK - PersistException
        userDao.getByPK(lastInsertedObject.getId());
    }
}
