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
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class UserRoleTest {

    private UserRole userRole = null;
    private UserRole lastInsertedObject = null;
    private List<UserRole> userRoles = null;
    private DaoFactory<Connection> factory = null;
    private Connection connection = null;
    private GenericDao userRoleDao =null;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        factory = new DaoFactoryImpl();
        connection = factory.getContext();
        userRole = new UserRole();
        userRoleDao = factory.getDao(connection, UserRole.class);
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateAndUpdateAndGetByPK(){
        try {
            //create
            userRole.setUserRole("CTO");
            lastInsertedObject = (UserRole) userRoleDao.persist(userRole);
            String expected = userRole.getUserRole();
            String actual = lastInsertedObject.getUserRole();
            assertEquals(expected, actual);

            //update
            lastInsertedObject.setUserRole("JANITOR");
            userRoleDao.update(lastInsertedObject);

            //getByPK
            UserRole getByPkRole = (UserRole) userRoleDao.getByPK(lastInsertedObject.getId());
            expected = lastInsertedObject.getUserRole();
            actual = getByPkRole.getUserRole();
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Test
    public void testGetAll(){
        try {
            userRoles = userRoleDao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(userRoles);
        Assert.assertTrue(userRoles.size() > 0);
    }

    @Test
    public void testDeleteAndException(){
        try {
            //create
            userRole.setUserRole("MANAGER");
            lastInsertedObject = (UserRole) userRoleDao.persist(userRole);

            //delete
            userRoleDao.delete(lastInsertedObject.getId());

            //getByPK - PersistException
            userRoleDao.getByPK(lastInsertedObject.getId());
            thrown.expect(PersistException.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
