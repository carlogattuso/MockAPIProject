package edu.upc.dsa;

import edu.upc.dsa.models.ExistantUserException;
import edu.upc.dsa.models.PasswordNotMatchException;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MockAPITest {
    private MockAPI ma;

    @Before
    public void setUp() throws Exception {
        this.ma = MockAPIImpl.getInstance();

        this.ma.addUser("admin", "admin");
        this.ma.addUser("carlo", "carlo");
    }

    @After
    public void tearDown() {
        this.ma.clear();
    }

    @Test
    public void testAddUser() throws ExistantUserException {
        this.ma.addUser("mario", "mario");
        Assert.assertEquals(3, this.ma.size());
    }

    @Test
    public void testGetUser() throws UserNotFoundException, PasswordNotMatchException {
        User u = this.ma.getUser("carlo", "carlo");
        Assert.assertEquals("carlo", u.getUsername());
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUserNotFound() throws Exception {
        this.ma.getUser("null", "null");
    }

    @Test(expected = ExistantUserException.class)
    public void testAddExistingUser() throws Exception {
        this.ma.addUser("carlo", "carlo");
    }

    @Test(expected = PasswordNotMatchException.class)
    public void testGetUserPasswordNotMatch() throws Exception {
        this.ma.getUser("carlo", "null");
    }

}

