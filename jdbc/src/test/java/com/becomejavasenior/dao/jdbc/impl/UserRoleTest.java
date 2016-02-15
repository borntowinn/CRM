package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.UserRole;
import com.becomejavasenior.dao.UserRoleDao;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
import com.becomejavasenior.dao.jdbc.factory.DataSource;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class UserRoleTest {

    private static UserRole userRole = null;
    private static UserRole lastInsertedObject = null;
    private List<UserRole> userRoles = null;
    private static UserRoleDao<UserRole> userRoleDao = null;
    private static DataSource dataSource = DataSource.getInstance();

    @BeforeClass
    public static void setUp() {
        userRoleDao = DaoFactory.getUserRoleDAO();
        userRole = new UserRole();
        lastInsertedObject = new UserRole();

        try(Connection connection = dataSource.getConnection()) {
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
