package management;

public class ProjectManager extends User {

    public ProjectManager(int userId, String name, String email,
                          String username, String password) {
        super(userId, name, email, username, password, UserRole.PROJECT_MANAGER);
    }

    @Override
    public void showMenu() {
        System.out.println("===== Project Manager Menu =====");
        System.out.println("1. Create Project");
        System.out.println("2. Create Task");
        System.out.println("3. Assign Task");
        System.out.println("4. View My Projects");
        System.out.println("5. View Tasks");
        System.out.println("0. Logout");
    }
}
