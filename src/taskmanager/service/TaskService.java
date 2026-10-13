package taskmanager.service;

import taskmanager.ui.ConsoleUI;
import taskmanager.model.Priority;
import taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    public final List<Task> tasks = new ArrayList<>();
    private static final String EMPTY_MESSAGE =
            "There are no created tasks.";

    // option 1
    public void addTask(String title, String description, Priority priority) {
        int newId;
        // check if the id is already exist.
        if (tasks.isEmpty()){
             newId = 1;
        } else {
            newId = tasks.getLast().getId() + 1;
        }

        Task task = new Task(
                newId,
                title,
                description,
                priority,
                false
        );

        tasks.add(task);

    }

    //option 2
    public List<Task> showAllTasks(List<Task> tasks) {
        return tasks;
    }

    // option 3
    public void completeTask() {
        List<Integer> ids = new ArrayList<>();
        for (Task task : tasks) {
            ids.add(task.getId());
        }

        while (true) {
            if (tasks.isEmpty()) {
                System.out.println(EMPTY_MESSAGE);
                return;
            }
            System.out.print("ID of task: ");
            int selectedId = ConsoleUI.readIntID();

            //stream for searching the id and setCompleted to true.
            var toCompleteTask = tasks.stream()
                    .filter(task -> task.getId() == selectedId) // try to return the tasks with the given id
                    .findFirst();

            if (toCompleteTask.isEmpty()) {
                System.out.println("Task with id " + selectedId + " not found.");
                System.out.println("There are only tasks with these ids " + ids);
                continue;
            }

            Task task = toCompleteTask.get();

            if (task.getCompleted()) {
                System.out.println(
                        "Task with id " + selectedId + " is already completed."
                );
                return;
            }
            task.setCompleted(true);
            System.out.println("Task with id " + selectedId + " completed.");
            return;
        }
    }

    // option 4
    public void deleteTask() {
        List<Integer> ids = new ArrayList<>();
        for (Task task : tasks) {
            ids.add(task.getId());
        }

        while (true) {
            if (tasks.isEmpty()) {
                System.out.println(EMPTY_MESSAGE);
                return;
            }

            System.out.print("ID of task: ");
            int selectedId = ConsoleUI.readIntID();
            var toRemoveTask = tasks.stream()
                        .filter(task -> task.getId() == selectedId)
                        .findFirst();

            // check if the optional Task really has a value,
            // and remove the task if it has a value.
            if (toRemoveTask.isEmpty()) {
                System.out.println("Task with id " + selectedId + " not found.");
                System.out.println("There are only tasks with these ids " + ids);
                continue;
            }
                tasks.remove(toRemoveTask.get());
                System.out.println("Task with id " + selectedId + " is removed.");
            return;
        }
    }

    // option 5
    public void printCompletedTasks() {
        if (tasks.isEmpty()) {
            System.out.println(EMPTY_MESSAGE);
        }
        List<Task> completedTasks = tasks.stream()
                .filter(Task::getCompleted)
                .toList();

        if (completedTasks.isEmpty()) {
            System.out.println("There are no completed tasks.");
            return;
        }
        System.out.println("There are " + completedTasks.size() + " completed tasks.");
        tasks.stream()
                .filter(Task::getCompleted)
                .forEach(task ->
                        System.out.println("ID: " + task.getId()
                                + " | " + "Title: " + task.getTitle()
                                + " | " + "Description: " + task.getDescription())
                );
    }

    // option 6
    public void printIncompletedTasks() {
        if (tasks.isEmpty()) {
            System.out.println(EMPTY_MESSAGE);
        }

        List<Task> openTasks = tasks.stream()
                .filter(task -> !task.getCompleted())
                .toList();

        if (openTasks.isEmpty()) {
            System.out.println("There are no incompleted tasks.");
            return;
        }
        System.out.println("There are "  + openTasks.size() + " incompleted tasks.");
        tasks.stream()
                .filter(task -> !task.getCompleted())
                .forEach(task ->
                        System.out.println("ID: " + task.getId()
                                + " | " + "Title: " + task.getTitle()
                                + " | " + "Description: " + task.getDescription())
                );
    }
}
