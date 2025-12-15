package management;

public class Task {

    private int taskId;
    private String title;
    private String description;
    private int projectId;
    private int assignedUserId = -1;
    private TaskStatus status = TaskStatus.TODO;
    private TaskPriority priority = TaskPriority.MEDIUM; // default priority

    public Task(int taskId, String title, String description, int projectId) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.projectId = projectId;
    }


    public int getTaskId() { return taskId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getProjectId() { return projectId; }
    public int getAssignedUserId() { return assignedUserId; }
    public void setAssignedUserId(int userId) { this.assignedUserId = userId; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }

    @Override
    public String toString() {
        return taskId + ". " + title + " [" + status + "] (Project ID: " + projectId +
                ", Assigned User: " + assignedUserId + ", Priority: " + priority + ")";
    }
}
