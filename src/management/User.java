package management;

public abstract class User {

    private int userId;
    private String name;
    private String email;
    private String username;
    private String password;
    private UserRole role;

    public User(int userId, String name, String email, String username, String password, UserRole role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserId() { return userId; }
    public void setUserId(int id) { this.userId = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserRole getRole() { return role; }

    public boolean checkPassword(String pwd) {
        return password != null && password.equals(pwd);
    }

    @Override
    public String toString() {
        return userId + ". " + name + " (" + username + ") [" + role + "]";
    }

    public abstract void showMenu();
}
