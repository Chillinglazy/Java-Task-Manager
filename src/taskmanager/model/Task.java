package taskmanager.model;

public class Task {
    private final int id; // id number
    private String title; // name of the task
    private String description; // tasks description
    private Priority priorityStatus; // Priority of the tasks in text
    private boolean completed; // progression status done or not

    public Task(int id,
                String title,
                String description,
                Priority priorityStatus,
                boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priorityStatus = priorityStatus;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriorityStatus() {
        return priorityStatus;
    }

    public void setPriorityStatus(Priority priorityStatus) {
        this.priorityStatus = priorityStatus;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        String status = completed ? "DONE" : "OPEN";
        return ("ID: " + id
                + " ,Title: " + title
                + ", Description: " + description
                + ", Priority: " + priorityStatus
                + ", Completed: " + status);
    }
}
