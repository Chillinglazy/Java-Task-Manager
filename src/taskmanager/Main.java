package taskmanager;

import taskmanager.ui.ConsoleUI;


public class Main {
    public static void main(String[] args) {
        ConsoleUI.showMenu();
    }
}
// V1 and V2:
    // Functions:
    // create task
    // print all task
    // complete task
    // delete task
    // print completed task
    // print not completed task
    // exit

    // bugs:
    // here we can add 2 tasks with same id solve the bugs,
    // so that each task gets 1 unique id (solved).

    // problems:
    // input-validations (done)
    // recursive problem at completeTask and deleteTask()
    // it can lead into a StackOverflowError
    // when the users give invalid input many times. (done)
    // better title validation (done)

    // testing: (done)

// V3:
    // Cleaning and Refactoring the code


