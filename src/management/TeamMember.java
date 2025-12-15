package management;

import java.util.Scanner;

public class TeamMember extends User {

    private Scanner scanner = new Scanner(System.in);
    private TaskService taskService;

    public TeamMember(int userId, String name, String email,
                      String username, String password) {
        super(userId, name, email, username, password, UserRole.TEAM_MEMBER);
    }

    public void setServices(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void showMenu() {
        int choice;
        do {
            System.out.println("\n===== Team Member Menu =====");
            System.out.println("1. View My Tasks");
            System.out.println("2. Update Task Status");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> viewMyTasks();
                case 2 -> updateTaskStatus();
                case 0 -> System.out.println("Logged out successfully.");
                default -> System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }

    private void viewMyTasks() {
        System.out.println("=== My Tasks ===");
        for (Task t : taskService.getTasksByUser(getUserId())) {
            System.out.println(t);
        }
    }

    private void updateTaskStatus() {
        System.out.print("Task ID: ");
        int taskId = Integer.parseInt(scanner.nextLine());
        System.out.println("Select status: 1. TODO 2. IN_PROGRESS 3. DONE");
        int statusChoice = Integer.parseInt(scanner.nextLine());
        TaskStatus status = switch (statusChoice) {
            case 2 -> TaskStatus.IN_PROGRESS;
            case 3 -> TaskStatus.DONE;
            default -> TaskStatus.TODO;
        };
        System.out.println(taskService.updateStatus(taskId, status) ? "Status updated ✔️" : "Failed ❌");
    }
}
