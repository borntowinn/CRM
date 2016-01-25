package com.becomejavasenior;

import com.becomejavasenior.dao.DaoFactory;
import com.becomejavasenior.dao.GenericDao;
import com.becomejavasenior.dao.impl.DaoFactoryImpl;
import com.becomejavasenior.dao.impl.PersistException;
import com.becomejavasenior.dao.impl.UserDaoImpl;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

public class UserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCRUD() throws Exception{
        DaoFactory daoFactory = new DaoFactoryImpl();
        Connection connection = daoFactory.getContext();
        connection.setAutoCommit(false);

        GenericDao<User> userDao = new UserDaoImpl(connection);

        try{
            User newUser = new User();
            newUser.setName("Glen");
            newUser.setSurname("Johnson");
            newUser.setPassword("!(*@&!*(@&!*(@!@)(*");
            newUser.setCreationDate(new Date());
            newUser.setEmail("johnson@gmail.com");
            newUser.setMobilePhone("+666 66 66");
            newUser.setWorkPhone("+777 77 77");
            newUser.setUserRoleId(1);

            //1 - create
            User lastInsertedObject =  userDao.persist(newUser);
            assertEquals("Glen", lastInsertedObject.getName());
            assertEquals("!(*@&!*(@&!*(@!@)(*", lastInsertedObject.getPassword());
            assertEquals("+777 77 77", lastInsertedObject.getWorkPhone());
            assertSame(1, lastInsertedObject.getUserRoleId());

            //2 - update
            lastInsertedObject.setName("Maria");
            lastInsertedObject.setCreationDate(new Date());
            lastInsertedObject.setEmail("maria.johnson@yahoo.com");
            lastInsertedObject.setMobilePhone("248-8-248");
            lastInsertedObject.setUserRoleId(2);
            userDao.update(lastInsertedObject);

            //3 - get by PK and check update
            int id = lastInsertedObject.getId();
            User getByPkUser = userDao.getByPK(id);
            assertEquals("248-8-248", getByPkUser.getMobilePhone());
            assertSame(2, getByPkUser.getUserRoleId());

            //4 - get all
            List<User> users = userDao.getAll();
            Assert.assertNotNull(users);
            Assert.assertTrue(users.size() > 0);

            //5 - delete last record and try to retrieve it
            userDao.delete(id);
            thrown.expect(PersistException.class);
            userDao.getByPK(id);

        }catch (Exception e){
            throw new PersistException(e);
        }
    }
}
