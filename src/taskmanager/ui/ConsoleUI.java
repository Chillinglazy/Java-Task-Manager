package taskmanager.ui;

import taskmanager.model.Priority;
import taskmanager.service.TaskService;

import java.util.Scanner;

public class ConsoleUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskService taskService = new TaskService();
    private static int idNumber;
    private static final String EMPTY_MESSAGE =
            "There are no created tasks.";


    // validation for Option input
    public static void showMenu() {
        int options = 0;
        while (options != 7) {
            System.out.println("====TASK MANAGER====");
            System.out.println("1. create new task.");
            System.out.println("2. show all tasks.");
            System.out.println("3. task finished.");
            System.out.println("4. delete task.");
            System.out.println("5. print completed tasks.");
            System.out.println("6. print not completed tasks.");
            System.out.println("7. exit.");
            System.out.print("Chose one option (1 to 7): ");
            if (scanner.hasNextInt()) {
                options = scanner.nextInt();
                scanner.nextLine();
                switch (options) {
                    case 1:
                        taskService.addTask(readStringTitle(), readString(), readIntPriority(1, 3));
                        idNumber= taskService.tasks.getLast().getId();
                        System.out.println("Task with id: " + idNumber + " added.");
                        break;
                    case 2:
                        if (taskService.tasks.isEmpty()) {
                            System.out.println(EMPTY_MESSAGE);
                        }
                        taskService.showAllTasks(taskService.tasks).forEach(System.out::println);
                        break;
                    case 3:
                        taskService.completeTask();
                        break;
                    case 4:
                        taskService.deleteTask();
                        break;
                    case 5:
                        taskService.printCompletedTasks();
                        break;
                    case 6:
                        taskService.printIncompletedTasks();
                        break;
                    case 7:
                        System.out.println("Thank you for using our task manager.");
                        break;
                    default:
                        System.out.println("Enter a new number between 1 and 7");
                }
            } else {
                System.out.println("Invalid input.");
                scanner.nextLine(); // delete the invalid input
            }
            System.out.println();
        }
    }

    // reading the input as string (for description and only string details).
    public static String readString() {
        System.out.print("Description: ");
        return scanner.nextLine();
    }

    //validation for Title input
    public static String readStringTitle() {
        System.out.print("Title: ");
        while (true) {
            String title = scanner.nextLine();
            if (title.isBlank()) {
                System.out.println("Title cannot be empty.");
                System.out.print("Title: ");
                continue;
            }
            return title;
        }
    }

    // validation for ID input
    public static int readIntID() {
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                System.out.print("ID of task: ");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.print(
                        "Invalid input. Please enter one whole number: "
                );
            }
        }
    }

    // validation for Priority input
    // new: the keyword continue means start the while loop again.
    public static Priority readIntPriority(int min, int max) {
        System.out.print("Priority (1 = LOW, 2 = MEDIUM, 3 = HIGH): ");
        Priority priority;
        int number = 0;
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                System.out.print("Priority (1 = LOW, 2 = MEDIUM, 3 = HIGH): ");
                continue;
            }

            try {
                    number = Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println(
                        "Invalid number. Enter a number between "
                                + min + " and " + max + "."
                );
                System.out.print("Priority (1 = LOW, 2 = MEDIUM, 3 = HIGH): ");
                continue;
            }

            switch (number) {
                case 1:
                    priority = Priority.LOW;
                    break;
                case 2:
                    priority = Priority.MEDIUM;
                    break;
                case 3:
                    priority =  Priority.HIGH;
                    break;
                default:
                    System.out.println(
                            "Invalid number. Enter a number between "
                                    + min + " and " + max + "."
                    );
                    System.out.print("Priority (1 = LOW, 2 = MEDIUM, 3 = HIGH): ");
                    continue;
            }
            break;
        }
        return priority;
    }

}
