package management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String USERS_FILE = "users.txt";
    private static final String PROJECTS_FILE = "projects.txt";
    private static final String TASKS_FILE = "tasks.txt";



    public static void saveUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User u : users) {
                writer.write(
                        u.getUserId() + "|" +
                                u.getName() + "|" +
                                u.getEmail() + "|" +
                                u.getUsername() + "|" +
                                u.getPassword() + "|" +
                                u.getRole()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users");
        }
    }

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(USERS_FILE);

        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length < 6) continue;

                int id = Integer.parseInt(p[0]);
                String name = p[1];
                String email = p[2];
                String username = p[3];
                String password = p[4];
                UserRole role = UserRole.valueOf(p[5]);

                User user;
                switch (role) {
                    case ADMIN -> user = new Admin(id, name, email, username, password);
                    case PROJECT_MANAGER -> user = new ProjectManager(id, name, email, username, password);
                    default -> user = new TeamMember(id, name, email, username, password);
                }

                users.add(user);
            }
        } catch (IOException e) {
            System.out.println("Error loading users");
        }
        return users;
    }



    public static void saveProjects(List<Project> projects) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROJECTS_FILE))) {
            for (Project p : projects) {
                writer.write(
                        p.getId() + "|" +
                                p.getName() + "|" +
                                p.getManagerId() + "|" +
                                p.getDescription()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving projects");
        }
    }

    public static List<Project> loadProjects() {
        List<Project> projects = new ArrayList<>();
        File file = new File(PROJECTS_FILE);

        if (!file.exists()) return projects;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length < 4) continue;

                int id = Integer.parseInt(p[0]);
                String name = p[1];
                int managerId = Integer.parseInt(p[2]);
                String description = p[3];

                projects.add(new Project(id, name, managerId, description));
            }
        } catch (IOException e) {
            System.out.println("Error loading projects");
        }
        return projects;
    }



    public static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TASKS_FILE))) {
            for (Task t : tasks) {
                writer.write(
                        t.getTaskId() + "|" +
                                t.getTitle() + "|" +
                                t.getDescription() + "|" +
                                t.getStatus() + "|" +
                                t.getPriority() + "|" +
                                t.getAssignedUserId() + "|" +
                                t.getProjectId()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks");
        }
    }

    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(TASKS_FILE);

        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length < 7) continue;

                int taskId = Integer.parseInt(p[0]);
                String title = p[1];
                String description = p[2];
                TaskStatus status = TaskStatus.valueOf(p[3]);
                TaskPriority priority = TaskPriority.valueOf(p[4]);
                int assignedUserId = Integer.parseInt(p[5]);
                int projectId = Integer.parseInt(p[6]);

                Task task = new Task(taskId, title, description, projectId);
                task.setStatus(status);
                task.setPriority(priority);
                task.setAssignedUserId(assignedUserId);

                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks");
        }
        return tasks;
    }
}