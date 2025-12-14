package management;

public class Admin extends User {

    public Admin(int userId, String name, String email,
                 String username, String password) {
        super(userId, name, email, username, password, UserRole.ADMIN);
    }

    @Override
    public void showMenu() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. Add User");
        System.out.println("2. List Users");
        System.out.println("3. Search User");
        System.out.println("4. Logout");
    }
}