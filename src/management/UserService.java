package management;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private ArrayList<User> users;
    private int nextUserId = 1;

    public UserService() {
        users = new ArrayList<>();
    }


    //    public boolean registerUser(User newUser) {
//
//        if (newUser == null ||
//                newUser.getUsername() == null ||
//                newUser.getUsername().trim().isEmpty() ||
//                newUser.getPassword() == null ||
//                newUser.getPassword().trim().isEmpty()) {
//            return false;
//        }
//
//        if (usernameExists(newUser.getUsername())) {
//            return false;
//        }
//
//        newUser.setUserId(nextUserId++);
//        users.add(newUser);
//        FileManager.saveUsers(users);
//        return true;
//    }
    public boolean registerUser(User newUser) {

        if (newUser == null ||
                newUser.getUsername() == null ||
                newUser.getUsername().trim().isEmpty() ||
                newUser.getPassword() == null ||
                newUser.getPassword().trim().isEmpty()) {
            return false;
        }

        if (usernameExists(newUser.getUsername())) {
            return false;
        }

        users.add(newUser);   // الـ ID جاي جاهز من constructor
        FileManager.saveUsers(users);
        return true;
    }


    public User authenticate(String username, String password) {

        if (username == null || password == null) {
            return null;
        }

        for (User u : users) {
            if (u.getUsername().equals(username) &&
                    u.checkPassword(password)) {
                return u;
            }
        }
        return null;
    }

    public User getUserById(int id) {
        for (User u : users) {
            if (u.getUserId() == id) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    private boolean usernameExists(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    public boolean updateUserName(int userId, String newName) {
        User user = getUserById(userId);

        if (user == null || newName == null || newName.trim().isEmpty()) {
            return false;
        }

        user.setName(newName);
        FileManager.saveUsers(users);
        return true;
    }

    public boolean updateUserEmail(int userId, String newEmail) {
        User user = getUserById(userId);

        if (user == null || newEmail == null || newEmail.trim().isEmpty()) {
            return false;
        }

        user.setEmail(newEmail);
        FileManager.saveUsers(users);
        return true;
    }

    public boolean updateUserPassword(int userId, String newPassword) {
        User user = getUserById(userId);

        if (user == null || newPassword == null || newPassword.trim().isEmpty()) {
            return false;
        }

        user.setPassword(newPassword);
        FileManager.saveUsers(users);
        return true;
    }


    public void loadUsers(List<User> loadedUsers) {

        users.clear();
        users.addAll(loadedUsers);

        nextUserId = 1;
        for (User u : users) {
            if (u.getUserId() >= nextUserId) {
                nextUserId = u.getUserId() + 1;
            }
        }
    }
}