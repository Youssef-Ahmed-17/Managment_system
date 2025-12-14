package management;

public class Task {
    private int taskId;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private int assignedUserId;
    private int projectId;

    // Full constructor
    public Task(String title, int assignedUserId, int projectId) {
        this.title = title;
        this.status = TaskStatus.TODO;
        this.priority = TaskPriority.MEDIUM;
        this.assignedUserId = -1;
        this.projectId = projectId;

    }
    public void changeStatus(TaskStatus newStatus) {
        this.status = newStatus;
    }

    public void assignToUser(int userId) {
        this.assignedUserId = userId;
    }


    // Getters
    public int getTaskId() {

        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public int getAssignedUserId() {
        return assignedUserId;
    }

    public int getProjectId() {
        return projectId;
    }

    // Setters

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

