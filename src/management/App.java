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
                case 1 -> login();
                case 2 -> register();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void register() {

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

        int id = userService.getAllUsers().size() + 1; // ID تلقائي
        User user;

        switch (roleChoice) {
            case 2 -> user = new ProjectManager(id, name, email, username, password);
            case 3 -> user = new Admin(id, name, email, username, password); // Admin
            default -> user = new TeamMember(id, name, email, username, password);
        }

        if (userService.registerUser(user)) {
            System.out.println("Registered successfully ✔️");
        } else {
            System.out.println("Registration failed ❌ (username exists or invalid data)");
        }
    }

    private void login() {

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.authenticate(username, password);

        if (user == null) {
            System.out.println("Invalid username or password");
            return;
        }

        System.out.println("\nWelcome, " + user.getName());
        user.showMenu();   // polymorphism
    }

    private void loadData() {

        List<User> users = FileManager.loadUsers();
        userService.loadUsers(users);

        List<Project> projects = FileManager.loadProjects();
        projectService.loadProjects(projects);

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


//package management;
//
//import java.util.Scanner;
//
//public class App {
//
//    private Scanner scanner;
//    public App() {
//        scanner = new Scanner(System.in);}
//    public void start() {
//        int choice = 0;
//        System.out.println("   Project Management System");
//        while (choice != 3) {
//            System.out.println("\nMain Menu");
//            System.out.println("1- Projects");
//            System.out.println("2- Tasks");
//            System.out.println("3- Exit");
//            System.out.print("Enter your choice: ");
//
//            choice = scanner.nextInt();
//            scanner.nextLine();
//
//            if (choice == 1) {
//
//                System.out.println("\nProjects Menu");
//                System.out.println("1- Add New Project");
//                System.out.println("2- View Projects");
//                System.out.println("3- Back");
//                System.out.print("Choose: ");
//
//                int projectChoice = scanner.nextInt();
//                scanner.nextLine();
//
//                if (projectChoice == 1) {
//                    System.out.print("Enter the project name: ");
//                    String projectName = scanner.nextLine();
//
//                    System.out.println("Project" + projectName + "is added");}
//                else if (projectChoice == 2) {
//                    System.out.println("Showing the projects");}
//                else {
//                    System.out.println("Back to main menu.");}
//            }
//            else if (choice == 2) {
//                System.out.println("\nTask Menu");
//                System.out.println("1- Add Task");
//                System.out.println("2- View Task");
//                System.out.println("3- Back");
//                System.out.print("Choose: ");
//
//                int taskChoice = scanner.nextInt();
//                scanner.nextLine();
//                if (taskChoice == 1) {
//                    System.out.print("Enter task name: ");
//                    String taskname = scanner.nextLine();
//
//                    System.out.println("Task \"" + taskname + "is added ");}
//                else if (taskChoice == 2) {
//                    System.out.println("Showing the tasks");}
//                else {
//                    System.out.println("Back to main menu.");}
//            }
//            else if (choice == 3) {
//                System.out.println("Exiting");}
//            else {
//                System.out.println("Invalid choice, try again.");
//            }
//        }
//    }
//}