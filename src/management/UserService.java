package management;
import management.Admin;
import management.User;
import management.UserRole;
import static management.UserRole.ADMIN;
import java.util.ArrayList;
import java.io.*;

public class UserService {

    private ArrayList<User> list_users;
    private final String FILE_NAME = "users.txt";

    public UserService() {
        list_users = new ArrayList<>();
        loadUsers();
    }

    public void addUser(User user) {
        list_users.add(user);
        saveUsers();
    }

    public User getUserById(String id) {
        for (User u : list_users) {
            if (u.getUserId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public User authenticate(String username, String password) {
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

    private void loadUsers() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");

                String id = data[0];
                String name = data[1];
                String email = data[2];
                String username = data[3];
                String password = data[4];
                UserRole role = UserRole.valueOf(data[5]);

                switch (role) {
                    case ADMIN:
                        list_users.add(new Admin(id, name, email, username, password));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    private void saveUsers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (User u : list_users) {
                pw.println(
                        u.getUserId() + "|" +
                                u.getName() + "|" +
                                u.getEmail() + "|" +
                                u.getUsername() + "|" +
                                u.getPassword() + "|" +
                                u.getRole()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}