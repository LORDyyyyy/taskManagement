package javaapplication66;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class JavaApplication66 {

    public static void main(String[] args) throws Exception {
        
   int empId = 10; // Use your employee ID

        EmployeeTaskLogManager taskManager = new EmployeeTaskLogManager();

        System.out.println("Welcome to employee mode");
        System.out.println("Choose 1 to add task");
        System.out.println("Choose 2 to see all tasks");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        if (choice == 1) {
            taskManager.addTask(empId);
        } else if (choice == 2) {
            taskManager.viewTasks(empId);

            System.out.println("Choose 1 to add start time");
            System.out.println("Choose 2 to add finish time");

            int empChoice = input.nextInt();

            if (empChoice == 1) {
                taskManager.addStartTime(empId);
            } else if (empChoice == 2) {
                taskManager.addFinishTime(empId);
            } else {
                System.out.println("Invalid choice");
            }
        } else {
            System.out.println("Invalid choice");    
        }
    }
}


       

    

