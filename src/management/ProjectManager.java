package management;

import java.util.Scanner;

public class ProjectManager extends User {

    private Scanner scanner = new Scanner(System.in);
    private ProjectService projectService;
    private TaskService taskService;
    private UserService userService;

    public ProjectManager(int userId, String name, String email,
                          String username, String password) {
        super(userId, name, email, username, password, UserRole.PROJECT_MANAGER);
    }

    public void setServices(ProjectService projectService, TaskService taskService, UserService userService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
    }

    @Override
    public void showMenu() {

        int choice;

        do {
            System.out.println("\n===== Project Manager Menu =====");
            System.out.println("1. Create Project");
            System.out.println("2. Create Task");
            System.out.println("3. Assign Task");
            System.out.println("4. View My Projects");
            System.out.println("5. View Tasks");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> createProject();
                case 2 -> createTask();
                case 3 -> assignTask();
                case 4 -> viewMyProjects();
                case 5 -> viewTasks();
                case 0 -> System.out.println("Logged out successfully.");
                default -> System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }

    private void createProject() {
        System.out.print("Project name: ");
        String name = scanner.nextLine();
        System.out.print("Project description: ");
        String desc = scanner.nextLine();
        Project project = projectService.createProject(name, getUserId(), desc);
        if (project != null) System.out.println("Project created successfully ✔️");
        else System.out.println("Failed to create project ❌");
    }

    private void createTask() {
        System.out.print("Enter Project ID: ");
        int projectId = Integer.parseInt(scanner.nextLine());
        if (!projectService.projectExists(projectId)) {
            System.out.println("Project not found ❌");
            return;
        }
        System.out.print("Task title: ");
        String title = scanner.nextLine();
        System.out.print("Task description: ");
        String desc = scanner.nextLine();
        Task task = taskService.createTask(projectId, title, desc);
        if (task != null) System.out.println("Task created successfully ✔️");
        else System.out.println("Failed to create task ❌");
    }

    private void assignTask() {
        System.out.print("Task ID: ");
        int taskId = Integer.parseInt(scanner.nextLine());
        System.out.print("User ID to assign: ");
        int userId = Integer.parseInt(scanner.nextLine());
        if (taskService.assignTask(taskId, userId)) System.out.println("Task assigned ✔️");
        else System.out.println("Failed to assign task ❌");
    }

    private void viewMyProjects() {
        System.out.println("=== My Projects ===");
        for (Project p : projectService.getProjectsByManager(getUserId())) {
            System.out.println(p);
        }
    }

    private void viewTasks() {
        System.out.println("=== My Tasks ===");
        for (Task t : taskService.getTasksByProject(getUserId())) {
            System.out.println(t);
        }
    }
}
