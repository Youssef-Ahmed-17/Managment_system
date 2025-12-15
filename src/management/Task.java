package management;

public class Task {

    private int taskId;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private int assignedUserId;
    private int projectId;

    public Task(int taskId, String title, String description, int projectId) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.projectId = projectId;
        this.status = TaskStatus.TODO;
        this.priority = TaskPriority.MEDIUM;
        this.assignedUserId = -1; // not assigned yet
    }

    public int getTaskId() { return taskId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public TaskPriority getPriority() { return priority; }
    public int getAssignedUserId() { return assignedUserId; }
    public int getProjectId() { return projectId; }

    public void setStatus(TaskStatus status) { this.status = status; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
    public void setAssignedUserId(int assignedUserId) {
        this.assignedUserId = assignedUserId;
    }
}
