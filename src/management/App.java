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
                case 3 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    /* ================= LOGIN ================= */
    private void handleLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.authenticate(username, password);
        if (user == null) { System.out.println("Invalid username or password ❌"); return; }

        System.out.println("\nWelcome, " + user.getName());

        if (user instanceof ProjectManager pm) showProjectManagerMenu(pm);
        else if (user instanceof Admin admin) showAdminMenu(admin);
        else if (user instanceof TeamMember tm) showTeamMemberMenu(tm);
    }

    /* ================= REGISTER ================= */
    private void handleRegister() {
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Username: "); String username = scanner.nextLine();
        System.out.print("Password: "); String password = scanner.nextLine();

        System.out.println("Role:");
        System.out.println("1. Team Member");
        System.out.println("2. Project Manager");
        System.out.println("3. Admin");
        int roleChoice = readInt();

        // Generate safe ID
        int id = 1;
        for (User u : userService.getAllUsers()) if (u.getUserId() >= id) id = u.getUserId() + 1;

        User user;
        switch (roleChoice) {
            case 2 -> user = new ProjectManager(id, name, email, username, password);
            case 3 -> user = new Admin(id, name, email, username, password);
            default -> user = new TeamMember(id, name, email, username, password);
        }

        if (userService.registerUser(user)) {
            System.out.println("Registered successfully ✔️");
            System.out.println("\nWelcome, " + user.getName());
            if (user instanceof ProjectManager pm) showProjectManagerMenu(pm);
            else if (user instanceof Admin admin) showAdminMenu(admin);
            else if (user instanceof TeamMember tm) showTeamMemberMenu(tm);
        } else {
            System.out.println("Registration failed ❌ (username exists or invalid data)");
        }
    }

    /* ================= LOAD DATA ================= */
    private void loadData() {
        userService.loadUsers(FileManager.loadUsers());
        projectService.loadProjects(FileManager.loadProjects());
        taskService.loadTasks();
    }

    /* ================= MENUS ================= */
    private void showProjectManagerMenu(ProjectManager pm) {
        while (true) {
            System.out.println("\n===== Project Manager Menu =====");
            System.out.println("1. Create Project");
            System.out.println("2. Create Task");
            System.out.println("3. Assign Task");
            System.out.println("4. View My Projects");
            System.out.println("5. View Tasks");
            System.out.println("0. Logout");
            System.out.print("Choose: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> createProject(pm);
                case 2 -> createTask(pm);
                case 3 -> assignTask(pm);
                case 4 -> viewMyProjects(pm);
                case 5 -> viewTasks(pm);
                case 0 -> { System.out.println("Logging out..."); return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void showAdminMenu(Admin admin) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add User");
            System.out.println("2. List Users");
            System.out.println("3. Search User");
            System.out.println("0. Logout");
            System.out.print("Choose: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> handleRegister(); // Admin can add users
                case 2 -> {
                    System.out.println("\nUsers List:");
                    for (User u : userService.getAllUsers())
                        System.out.println(u.getUserId() + ". " + u.getName() + " (" + u.getUsername() + ")");
                }
                case 3 -> {
                    System.out.print("Enter username to search: ");
                    String uname = scanner.nextLine();
                    User u = userService.getAllUsers().stream().filter(x -> x.getUsername().equals(uname)).findFirst().orElse(null);
                    if (u != null) System.out.println(u.getUserId() + ". " + u.getName() + " (" + u.getUsername() + ")");
                    else System.out.println("User not found");
                }
                case 0 -> { System.out.println("Logging out..."); return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void showTeamMemberMenu(TeamMember tm) {
        while (true) {
            System.out.println("\n===== Team Member Menu =====");
            System.out.println("1. View My Tasks");
            System.out.println("2. Update Task Status");
            System.out.println("0. Logout");
            System.out.print("Choose: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("\nMy Tasks:");
                    for (Task t : taskService.getTasksByUser(tm.getUserId()))
                        System.out.println(t.getTaskId() + ". " + t.getTitle() + " [" + t.getStatus() + "]");
                }
                case 2 -> {
                    System.out.print("Enter Task ID to update: ");
                    int tid = readInt();
                    Task task = taskService.getTaskById(tid);
                    if (task != null && task.getAssignedUserId() == tm.getUserId()) {
                        System.out.println("Select status: 1. TODO 2. IN_PROGRESS 3. DONE");
                        int s = readInt();
                        TaskStatus st = switch (s) {
                            case 2 -> TaskStatus.IN_PROGRESS;
                            case 3 -> TaskStatus.DONE;
                            default -> TaskStatus.TODO;
                        };
                        taskService.updateStatus(tid, st);
                        System.out.println("Task updated successfully ✅");
                    } else System.out.println("Task not found or not assigned to you");
                }
                case 0 -> { System.out.println("Logging out..."); return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    /* ================= HELPER METHODS ================= */
    private void createProject(ProjectManager pm) {
        System.out.print("Project name: ");
        String name = scanner.nextLine();
        System.out.print("Project description: ");
        String desc = scanner.nextLine();

        Project project = projectService.createProject(name, pm.getUserId(), desc);
        if (project != null) System.out.println("Project \"" + name + "\" created successfully ✅");
        else System.out.println("Failed to create project ❌");
    }

    private void createTask(ProjectManager pm) {
        System.out.print("Project ID: ");
        int pid = readInt();
        Project p = projectService.getProjectById(pid);
        if (p == null || p.getManagerId() != pm.getUserId()) {
            System.out.println("Project not found or not yours ❌");
            return;
        }

        System.out.print("Task title: ");
        String title = scanner.nextLine();
        System.out.print("Task description: ");
        String desc = scanner.nextLine();

        Task task = taskService.createTask(pid, title, desc);
        if (task != null) System.out.println("Task \"" + title + "\" created successfully ✅");
        else System.out.println("Failed to create task ❌");
    }

    private void assignTask(ProjectManager pm) {
        System.out.print("Task ID: ");
        int tid = readInt();
        Task task = taskService.getTaskById(tid);
        if (task == null) { System.out.println("Task not found ❌"); return; }

        System.out.print("User ID to assign: ");
        int uid = readInt();
        User u = userService.getUserById(uid);
        if (u == null) { System.out.println("User not found ❌"); return; }

        taskService.assignTask(tid, uid);
        System.out.println("Task assigned successfully ✅");
    }

    private void viewMyProjects(ProjectManager pm) {
        System.out.println("\nMy Projects:");
        for (Project p : projectService.getProjectsByManager(pm.getUserId()))
            System.out.println(p.getId() + ". " + p.getName());
    }

    private void viewTasks(ProjectManager pm) {
        System.out.println("\nAll Tasks:");
        for (Task t : taskService.getAllTasks())
            System.out.println(t.getTaskId() + ". " + t.getTitle() + " [Project ID: " + t.getProjectId() + "]");
    }

    private int readInt() {
        try { return Integer.parseInt(scanner.nextLine()); }
        catch (Exception e) { return -1; }
    }
}
