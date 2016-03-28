package implementations.entitiesImpl;

import com.becomejavasenior.User;
import com.becomejavasenior.UserRole;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest {

    UserDaoImpl userDao;
    User user;
    UserRoleDaoImpl userRole;

    @Before
    public void before() {
        userDao = new UserDaoImpl();
        User newUser = new User();
        newUser.setName("name");
        newUser.setPassword("pas");
        newUser.setEmail("emailTestEmail");
        userRole = new UserRoleDaoImpl();
        UserRole byPK = userRole.getByPK(1);
        newUser.setUserRole(byPK);
        user = userDao.create(newUser);
    }

    @After
    public void after () {
        userDao.delete(user);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User result = userDao.getByEmail("emailTestEmail");
        Assert.assertEquals(result.getEmail(), user.getEmail());
    }
}