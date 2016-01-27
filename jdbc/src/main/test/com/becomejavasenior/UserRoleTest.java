package com.becomejavasenior;

import com.becomejavasenior.dao.UserRoleDao;
import com.becomejavasenior.dao.impl.DaoFactoryImpl;
import com.becomejavasenior.dao.impl.PersistException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class UserRoleTest {

    private UserRole userRole = null;
    private UserRole lastInsertedObject = null;
    private List<UserRole> userRoles = null;
    private UserRoleDao userRoleDao = null;

    @Before
    public void setUp() {
        Connection connection = DaoFactoryImpl.getConnection();
        userRoleDao = DaoFactoryImpl.getUserRoleDAO();
        userRole = new UserRole();
        lastInsertedObject = new UserRole();

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateAndUpdateAndGetByPK(){
        //create
        userRole.setUserRole("CTO");
        lastInsertedObject = userRoleDao.persist(userRole);
        String expected = userRole.getUserRole();
        String actual = lastInsertedObject.getUserRole();
        assertEquals(expected, actual);

        //update
        lastInsertedObject.setUserRole("JANITOR");
        userRoleDao.update(lastInsertedObject);

        //getByPK
        UserRole getByPkRole = userRoleDao.getByPK(lastInsertedObject.getId());
        expected = lastInsertedObject.getUserRole();
        actual = getByPkRole.getUserRole();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll(){
        userRoles = userRoleDao.getAll();
        Assert.assertNotNull(userRoles);
        Assert.assertTrue(userRoles.size() > 0);
    }

    @Test(expected = PersistException.class)
    public void testDeleteAndException(){
        //create
        userRole.setUserRole("MANAGER");
        lastInsertedObject = userRoleDao.persist(userRole);

        //delete
        userRoleDao.delete(lastInsertedObject.getId());

        //getByPK - PersistException
        userRoleDao.getByPK(lastInsertedObject.getId());
    }
}
