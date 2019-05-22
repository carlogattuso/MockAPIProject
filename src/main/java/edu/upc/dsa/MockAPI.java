package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.List;

public interface MockAPI {

    public User addUser(String username, String password) throws ExistantUserException;
    public User getUser(String username, String password) throws UserNotFoundException, PasswordNotMatchException;
    public int size();
    public void clear();

}
