package management;

import java.util.ArrayList;

public class TaskService {

    private ArrayList<Task> list_tasks = new ArrayList<>();
    private int nextTaskId = 1;

    public ArrayList<Task> loadTasks() {
        return list_tasks;
    }
    //File
    public void saveTasks() {
        System.out.println("Tasks saved successfully");
    }

//    public Task createTask(int projectId, String title, String description, String priority) {
//        Task task = new Task(
//                nextTaskId++,
//                title,
//                description,
//                projectId
//        );
//
//        task.setStatus(TaskStatus.TODO);
//        task.setPriority(TaskPriority.MEDIUM);
//
//        list_tasks.add(task);
//        return task;
//    }

//    public boolean assignTask(int taskId, int userId) {
//        Task task = getTaskById(taskId);
//        if (task != null) {
//            task.setAssignedUserId(userId);
//            return true;
//        }
//        return false;
//    }

    public boolean updateStatus(int taskId, String status) {
        Task task = getTaskById(taskId);
        if (task != null) {
            task.setStatus(TaskStatus.valueOf(status.toUpperCase()));
            return true;
        }
        return false;
    }

    public Task getTaskById(int id) {
        for (Task t : list_tasks) {
            if (t.getTaskId() == id) {
                return t;
            }
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
}
