
package javaapplication65;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class JavaApplication65 {

   
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter employee ID:");
        int empId = input.nextInt();

        EmployeeAttendanceSystem attendanceSystem = new EmployeeAttendanceSystem(empId);

        System.out.println("Choose an option:\n1. Mark Attendance\n2. Mark Departure");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                attendanceSystem.markAttendance();
                break;
            case 2:
                attendanceSystem.markDeparture();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}


