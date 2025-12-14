package management;

import management.Admin;
import management.User;
import management.UserRole;

import static management.UserRole.ADMIN;

import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class UserService {

    private ArrayList<User> list_users;
    private final String FILE_NAME = "users.txt";
    private int nextUserId = 1;

    public UserService() {
        list_users = new ArrayList<>();

    }

    public void addUser(User user) {
        list_users.add(user);
    }

    public User getUserById(int id) {
        for (User u : list_users) {
            if (u.getUserId() == id) {
                return u;
            }
        }
        return null;
    }


    public boolean registerUser(User newUser) {


        if (newUser == null ||
                newUser.getUsername() == null ||
                newUser.getPassword() == null) {
            return false;
        }


        for (User u : list_users) {
            if (u.getUsername().equals(newUser.getUsername())) {
                return false;
            }
        }

        list_users.add(newUser);

        return true;
    }


    public User authenticate(String username, String password) {
        if (username == null || password == null)
            return null;

        for (User u : list_users) {
            if (u.getUsername().equals(username)
                    && u.checkPassword(password)) {
                return u;
            }
        }
        return null;
    }


    public ArrayList<User> getAllUsers() {
        return list_users;
    }

    public void loadUsers(List<User> loadedUsers) {
        list_users.clear();
        list_users.addAll(loadedUsers);

        nextUserId = 1;
        for (User u : list_users) {
            if (u.getUserId() >= nextUserId) {
                nextUserId = u.getUserId() + 1;
            }
        }
    }

    public boolean updateUserName(int userId, String newName) {
        User user = getUserById(userId);
        if (user == null || newName == null || newName.isEmpty()) return false;

        user.setName(newName);
        return true;
    }

    public boolean updateUserEmail(int userId, String newEmail) {
        User user = getUserById(userId);
        if (user == null || newEmail == null || newEmail.isEmpty()) return false;

        user.setEmail(newEmail);
        return true;
    }

    public boolean updateUserPassword(int userId, String newPassword) {
        User user = getUserById(userId);
        if (user == null || newPassword == null || newPassword.isEmpty()) return false;

        user.setPassword(newPassword);
        return true;
    }

}