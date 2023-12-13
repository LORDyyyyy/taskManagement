/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication66;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author moataz mamdouh
 */
public class EmployeeTaskLogManager {
    private FileStorage fileStorage;
    private Helpers helpers;

    public EmployeeTaskLogManager() {
        this.fileStorage = new FileStorage();
        this.helpers = new Helpers();
    }

    public void addTask(int empId) {
        try {
            File timestampFile = new File("data", helpers.tableNameLoc("tasklog"));
            if (!timestampFile.exists()) {
                fileStorage.createTable("tasklog", "task_log_id", "task_id", "emp_id", "start_date", "finish_date", "duration");
            }
            fileStorage.add("tasklog", helpers.paramsToArr(fileStorage.getNextID("tasklog"), 4, empId, "null", "null", "null"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewTasks(int empId) {
        try {
            String[][] result = fileStorage.read("tasklog", helpers.intToArr(2), helpers.paramsToArr(empId));

            for (String[] row : result) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStartTime(int empId) {
        try {
            File timestampFile = new File("data", helpers.tableNameLoc("tasklog"));
            if (!timestampFile.exists()) {
                System.out.println("Task log table does not exist. Please add a task first.");
                return;
            }

            System.out.println("Enter task_id: ");
            Scanner input = new Scanner(System.in);
            int taskId = input.nextInt();

            String[][] existingTaskLog = fileStorage.read("tasklog", helpers.intToArr(1), helpers.paramsToArr(taskId));

            if (existingTaskLog.length > 0 && "null".equals(existingTaskLog[0][3])) {
                LocalDateTime startTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedStartTime = startTime.format(formatter);

                fileStorage.update(
                        "tasklog",
                        helpers.intToArr(1),
                        helpers.paramsToArr(taskId),
                        helpers.intToArr(3),
                        helpers.paramsToArr(formattedStartTime)
                );

                System.out.println("Start time added successfully.");
            } else {
                System.out.println("Start time cannot be added. Task not found or start time already exists.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

 public void addFinishTime(int empId) {
    FileStorage fs = new FileStorage();
        Helpers hlp = new Helpers();
         LocalDateTime attendanceDate = LocalDateTime.now();
        DateTimeFormatter attendanceFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedAttendanceDate = attendanceDate.format(attendanceFormatter);
        
          LocalDateTime departureDate = LocalDateTime.now();
        DateTimeFormatter departureDateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDepartureDate = departureDate.format(attendanceFormatter);
        
         System.out.println("Enter taskId ");
Scanner input9 = new Scanner(System.in);
int taskId = input9.nextInt();

    try {
        File timestampFile = new File("data", helpers.tableNameLoc("tasklog"));
        if (!timestampFile.exists()) {
            System.out.println("Task log table does not exist. Please add a task first.");
            return;
        }

        String[][] existingTaskLog = fileStorage.read("tasklog", helpers.intToArr(1), helpers.paramsToArr(taskId));

        if (existingTaskLog.length > 0 &&
                !"null".equals(existingTaskLog[0][3]) &&
                ("null".equals(existingTaskLog[0][4]) || existingTaskLog[0][4] == null) &&
                ("null".equals(existingTaskLog[0][5]) || existingTaskLog[0][5].isEmpty())) {

            try {
                // Calculate duration using java.time.Duration
                String startDateString = existingTaskLog[0][3];

                // Create a formatter for the time part only
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime startTime = LocalTime.parse(startDateString, timeFormatter);

                // Get the current date
                LocalDate currentDate = LocalDate.now();

                // Combine the current date and start time to create LocalDateTime
                LocalDateTime startDateTime = LocalDateTime.of(currentDate, startTime);

                LocalDateTime currentDateTime = LocalDateTime.now();
                Duration duration = Duration.between(startDateTime, currentDateTime);

                // Format duration as HH:mm:ss
                String formattedDuration = String.format("%d:%02d:%02d",
                        duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());

                fs.update(
                        "tasklog",
                        hlp.intToArr(1),
                        hlp.paramsToArr(taskId),
                        hlp.intToArr(4, 5),
                        hlp.paramsToArr(formattedDepartureDate, formattedDuration),
                        false,
                        3
                );
                System.out.println("Update successful.");
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing start date: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            System.out.println("Update not allowed. Start date haven't been added yet or end date or duration have already been added");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

}

