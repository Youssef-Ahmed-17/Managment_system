package management;

import management.UserRole;

public abstract class User {

    protected int userId ;
    protected String name;
    protected String email;
    protected String username;
    protected String password;
    protected UserRole role;

    public User(int userId, String name, String email,
                String username, String password, UserRole role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean checkPassword(String inputPassword) {
        if (inputPassword == null) return false;
        return this.password.equals(inputPassword);
    }


    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public UserRole getRole() { return role; }
    public String getPassword() {return password;}


    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }

    public abstract void showMenu();
}