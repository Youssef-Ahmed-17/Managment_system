package management;

import management.UserRole;

public abstract class User {

    protected String userId;
    protected String name;
    protected String email;
    protected String username;
    protected String password;
    protected UserRole role;

    public User(String userId, String name, String email,
                String username, String password, UserRole role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public UserRole getRole() { return role; }
    public String getPassword() {return password;}

    public abstract void showMenu();
}