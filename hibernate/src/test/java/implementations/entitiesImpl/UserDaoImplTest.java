package implementations.entitiesImpl;

import com.becomejavasenior.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest {

    UserDaoImpl userDao;
    User user;

    @Before
    public void before() {
        userDao = new UserDaoImpl();
        User newUser = new User();
        newUser.setEmail("email");
        user = userDao.create(newUser);
    }

    @After
    public void after () {
        userDao.delete(user);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User result = userDao.getByEmail("email");
        Assert.assertEquals(result.getEmail(), user.getEmail());
    }
}