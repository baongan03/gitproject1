package service;

import model.*;
import utils.FileUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserService  {
    private static final String PATH = "data/user.txt";
    private static UserService userService;

    public UserService() {
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }


    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        List<String> records = FileUtils.readFile(PATH);
        for (String record : records) {
            users.add(User.parseUser(record));
        }
        return users;
    }


    public void add(User newUser) {
        List<User> users = findAll();
        users.add(newUser);
        FileUtils.writeFile(PATH, users);
    }


    public void removeById(long id) {
        List<User> users = findAll();
        users.removeIf(user -> user.getIdUser() == id);
        FileUtils.writeFile(PATH, users);
    }





    public boolean existsById(long id) {
        return findById(id) != null;
    }


    public User findById(long id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getIdUser() == id)
                return user;
        }
        return null;
    }


    public User adminLogin(String userName, String passWord) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassWord().equals(passWord)) {
                return user;
            }
            if (user.getUserName().equals(userName) && user.getPassWord().equals(passWord)
                    && user.getRole().equals(ERole.ADMIN)) {
                return user;
            }
            if (user.getUserName().equals(userName) && user.getPassWord().equals(passWord)
                    && user.getRole().equals(ERole.USER)) {
                return user;
            }
        }
        return null;
    }





    public boolean existByUserName(String username) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUserName().equals(username))
                return true;
        }
        return false;
    }
}