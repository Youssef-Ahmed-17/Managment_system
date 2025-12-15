package management;

import java.util.List;
import java.util.Scanner;

public class App {

    private UserService userService;
    private ProjectService projectService;
    private TaskService taskService;
    private Scanner scanner;

    public App() {
        scanner = new Scanner(System.in);
        userService = new UserService();
        projectService = new ProjectService();
        taskService = new TaskService();
        loadData();
    }

    public void start() {
        System.out.println("=== Project Management System ===");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> handleLogin();
                case 2 -> handleRegister();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }


    private void handleLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.authenticate(username, password);
        if (user == null) {
            System.out.println("Invalid username or password ❌");
            return;
        }

        System.out.println("\nWelcome, " + user.getName());

        if (user instanceof ProjectManager pm) {
            pm.setServices(projectService, taskService, userService);
            pm.showMenu();
        } else if (user instanceof Admin admin) {
            admin.setServices(userService);
            admin.showMenu();
        } else if (user instanceof TeamMember tm) {
            tm.setServices(taskService);
            tm.showMenu();
        }
    }


    private void handleRegister() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.println("Role:");
        System.out.println("1. Team Member");
        System.out.println("2. Project Manager");
        System.out.println("3. Admin");

        int roleChoice = readInt();

        int id = 1;
        for (User u : userService.getAllUsers()) {
            if (u.getUserId() >= id) id = u.getUserId() + 1;
        }

        User user;
        switch (roleChoice) {
            case 2 -> user = new ProjectManager(id, name, email, username, password);
            case 3 -> user = new Admin(id, name, email, username, password);
            default -> user = new TeamMember(id, name, email, username, password);
        }

        if (userService.registerUser(user)) {
            System.out.println("Registered successfully ✔️");
        } else {
            System.out.println("Registration failed ❌ (username exists or invalid data)");
        }
    }


    private void loadData() {
        userService.loadUsers(FileManager.loadUsers());
        projectService.loadProjects(FileManager.loadProjects());
        taskService.loadTasks();
    }


    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}
