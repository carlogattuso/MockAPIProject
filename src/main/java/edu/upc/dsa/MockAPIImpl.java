package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class MockAPIImpl implements MockAPI {
    private static MockAPI instance;

    HashMap<String, User> userHashMap;

    final static Logger logger = Logger.getLogger(MockAPIImpl.class);

    private MockAPIImpl() { this.userHashMap = new HashMap<>(); }

    public static MockAPI getInstance() {
        if (instance==null) instance = new MockAPIImpl();
        return instance;
    }

    public int size() {
        int ret = this.userHashMap.size();
        logger.info("size " + ret);
        return ret;
    }

    @Override
    public User addUser(String username, String password) throws ExistantUserException {
        User u = this.userHashMap.get(username);
        if(u!=null) throw new ExistantUserException();
        u = new User(username,password);
        this.userHashMap.put(username,u);
        logger.info("New user: "+u.toString());
        return u;
    }

    @Override
    public User getUser(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
        User u = this.userHashMap.get(username);
        if(u==null) throw new UserNotFoundException();
        if(!password.equals(u.getPassword())) throw new PasswordNotMatchException();
        logger.info("Logged in: "+u.toString());
        return u;
    }

    @Override
    public void clear() {
        this.userHashMap.clear();
    }
}