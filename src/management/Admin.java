package management;

import java.util.Scanner;

public class Admin extends User {

    private Scanner scanner = new Scanner(System.in);
    private UserService userService;

    public Admin(int userId, String name, String email,
                 String username, String password) {
        super(userId, name, email, username, password, UserRole.ADMIN);
    }

    public void setServices(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add User");
            System.out.println("2. List Users");
            System.out.println("3. Search User");
            System.out.println("4. Update User Name");
            System.out.println("5. Update User Email");
            System.out.println("6. Update User Password");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> addUser();
                case 2 -> listUsers();
                case 3 -> searchUser();
                case 4 -> updateUserName();
                case 5 -> updateUserEmail();
                case 6 -> updateUserPassword();
                case 0 -> System.out.println("Logged out successfully.");
                default -> System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

    private void addUser() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        User user = new TeamMember(0, name, email, username, password);
        System.out.println(userService.registerUser(user) ? "User added ✔️" : "Failed ❌");
    }

    private void listUsers() {
        System.out.println("=== All Users ===");
        for (User u : userService.getAllUsers()) {
            System.out.println(u);
        }
    }

    private void searchUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        for (User u : userService.getAllUsers()) {
            if (u.getUsername().equals(username)) {
                System.out.println(u);
                return;
            }
        }
        System.out.println("User not found ❌");
    }

    private void updateUserName() {
        System.out.print("User ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("New Name: ");
        String newName = scanner.nextLine();
        System.out.println(userService.updateUserName(id, newName) ? "Name updated ✔️" : "Failed ❌");
    }

    private void updateUserEmail() {
        System.out.print("User ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("New Email: ");
        String newEmail = scanner.nextLine();
        System.out.println(userService.updateUserEmail(id, newEmail) ? "Email updated ✔️" : "Failed ❌");
    }

    private void updateUserPassword() {
        System.out.print("User ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Old Password: ");
        String oldPassword = scanner.nextLine();

        System.out.print("New Password: ");
        String newPassword = scanner.nextLine();

        boolean updated = userService.updateUserPassword(id, oldPassword, newPassword);
        if (updated) System.out.println("Password updated ✔️");
        else System.out.println("Failed ❌ (wrong old password or invalid new password)");
    }

}
