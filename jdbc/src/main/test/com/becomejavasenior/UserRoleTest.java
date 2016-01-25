package com.becomejavasenior;

import com.becomejavasenior.dao.DaoFactory;
import com.becomejavasenior.dao.GenericDao;
import com.becomejavasenior.dao.impl.DaoFactoryImpl;
import com.becomejavasenior.dao.impl.PersistException;
import com.becomejavasenior.dao.impl.UserRoleDaoImpl;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Connection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class UserRoleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCRUD() throws Exception{
        DaoFactory daoFactory = new DaoFactoryImpl();
        Connection connection = daoFactory.getContext();
        connection.setAutoCommit(false);

        GenericDao<UserRole> userRoleDao = new UserRoleDaoImpl(connection);

        try{
            UserRole userRole = new UserRole();
            userRole.setUserRole("CEO");

            //1 - create
            UserRole lastInsertedObject =  userRoleDao.persist(userRole);
            assertEquals("CEO", lastInsertedObject.getUserRole());

            //2 - update
            lastInsertedObject.setUserRole("JANITOR");
            userRoleDao.update(lastInsertedObject);

            //3 - get by PK
            int id = lastInsertedObject.getId();
            UserRole getByPkRole = userRoleDao.getByPK(id);
            assertEquals("JANITOR", getByPkRole.getUserRole());

            //4 - get all
            List<UserRole> userRoles = userRoleDao.getAll();
            Assert.assertNotNull(userRoles);
            Assert.assertTrue(userRoles.size() > 0);

            //5 - delete
            userRoleDao.delete(id);
            thrown.expect(PersistException.class);
            userRoleDao.getByPK(id);

        }catch (Exception e){
            throw new PersistException(e);
        }
    }
}
