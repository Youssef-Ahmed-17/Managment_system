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
            System.out.println("6. Update Project Name");
            System.out.println("7. Update Project Description");
            System.out.println("8. Delete Project");
            System.out.println("9. View All Projects");
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
                case 6 -> updateProjectName();
                case 7 -> updateProjectDescription();
                case 8 -> deleteProject();
                case 9 -> viewAllProjects();
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
        System.out.println(project != null ? "Project created successfully ✔️" : "Failed to create project ❌");
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
        System.out.println(task != null ? "Task created successfully ✔️" : "Failed to create task ❌");
    }

    private void assignTask() {
        System.out.print("Task ID: ");
        int taskId = Integer.parseInt(scanner.nextLine());
        System.out.print("User ID to assign: ");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.println(taskService.assignTask(taskId, userId) ? "Task assigned ✔️" : "Failed to assign task ❌");
    }

    private void viewMyProjects() {
        System.out.println("=== My Projects ===");
        for (Project p : projectService.getProjectsByManager(getUserId())) {
            System.out.println(p);
        }
    }

    private void viewTasks() {
        System.out.println("=== My Tasks ===");
        for (Project p : projectService.getProjectsByManager(getUserId())) {
            for (Task t : taskService.getTasksByProject(p.getId())) {
                System.out.println(t);
            }
        }
    }

    private void updateProjectName() {
        System.out.print("Enter Project ID: ");
        int projectId = Integer.parseInt(scanner.nextLine());
        System.out.print("New Project Name: ");
        String newName = scanner.nextLine();
        System.out.println(projectService.updateProjectName(projectId, newName) ? "Project name updated ✔️" : "Failed ❌");
    }

    private void updateProjectDescription() {
        System.out.print("Enter Project ID: ");
        int projectId = Integer.parseInt(scanner.nextLine());
        System.out.print("New Description: ");
        String newDesc = scanner.nextLine();
        System.out.println(projectService.updateDescription(projectId, newDesc) ? "Project description updated ✔️" : "Failed ❌");
    }

    private void deleteProject() {
        System.out.print("Enter Project ID: ");
        int projectId = Integer.parseInt(scanner.nextLine());
        System.out.println(projectService.deleteProject(projectId) ? "Project deleted ✔️" : "Failed ❌");
    }

    private void viewAllProjects() {
        System.out.println("=== All Projects ===");
        for (Project p : projectService.getAllProjects()) {
            System.out.println(p);
        }
    }
}
