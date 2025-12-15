package management;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private ArrayList<Task> list_tasks = new ArrayList<>();
    private int nextTaskId = 1;

    public void loadTasks() {

        list_tasks.clear();

        List<Task> loadedTasks = FileManager.loadTasks();
        if (loadedTasks == null) return;

        list_tasks.addAll(loadedTasks);

        nextTaskId = 1;
        for (Task t : list_tasks) {
            if (t.getTaskId() >= nextTaskId) {
                nextTaskId = t.getTaskId() + 1;
            }
        }
    }

    private void save() {
        FileManager.saveTasks(list_tasks);
    }

    public Task createTask(int projectId, String title, String description) {

        if (title == null || title.trim().isEmpty()) return null;

        Task task = new Task(nextTaskId++, title, description, projectId);
        list_tasks.add(task);
        save();
        return task;
    }

    public boolean assignTask(int taskId, int userId) {

        Task task = getTaskById(taskId);
        if (task == null) return false;

        task.setAssignedUserId(userId);
        save();
        return true;
    }

    public boolean updateStatus(int taskId, TaskStatus status) {

        Task task = getTaskById(taskId);
        if (task == null || status == null) return false;

        task.setStatus(status);
        save();
        return true;
    }

    public Task getTaskById(int id) {
        for (Task t : list_tasks) {
            if (t.getTaskId() == id) return t;
        }
        return null;
    }

    public ArrayList<Task> getTasksByProject(int projectId) {

        ArrayList<Task> result = new ArrayList<>();
        for (Task t : list_tasks) {
            if (t.getProjectId() == projectId) {
                result.add(t);
            }
        }
        return result;
    }

    public ArrayList<Task> getTasksByUser(int userId) {

        ArrayList<Task> result = new ArrayList<>();
        for (Task t : list_tasks) {
            if (t.getAssignedUserId() == userId) {
                result.add(t);
            }
        }
        return result;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(list_tasks);
    }
}
