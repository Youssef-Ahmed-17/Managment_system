package management;

public class TeamMember extends User {

    public TeamMember(int userId, String name, String email,
                      String username, String password) {
        super(userId, name, email, username, password, UserRole.TEAM_MEMBER);
    }

    @Override
    public void showMenu() {
        System.out.println("===== Team Member Menu =====");
        System.out.println("1. View My Tasks");
        System.out.println("2. Update Task Status");
        System.out.println("0. Logout");
    }
}
