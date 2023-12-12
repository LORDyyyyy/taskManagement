
package javaapplication65;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class EmployeeAttendanceSystem {
    private int empId;
    private FileStorage fileStorage;
    private Helpers helpers;

    public EmployeeAttendanceSystem(int empId) {
        this.empId = empId;
        this.fileStorage = new FileStorage();
        this.helpers = new Helpers();
    }

    public void markAttendance() {
        // Get the current date
        LocalDateTime currentDay = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedCurrentDay = currentDay.format(dateFormatter);

        // Get the current attendance date
        LocalDateTime attendanceDate = LocalDateTime.now();
        DateTimeFormatter attendanceFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedAttendanceDate = attendanceDate.format(attendanceFormatter);

        try {
            File timestampFile = new File("data", helpers.tableNameLoc("timestamp"));
            if (!timestampFile.exists()) {
                fileStorage.createTable("timestamp", "id", "date", "emp_id", "start_date", "finish_date");
            }

            fileStorage.add("timestamp", helpers.paramsToArr(fileStorage.getNextID("timestamp"), formattedCurrentDay, empId, formattedAttendanceDate, "null"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void markDeparture() {
        // Get the current departure date
        LocalDateTime departureDate = LocalDateTime.now();
        DateTimeFormatter departureDateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDepartureDate = departureDate.format(departureDateFormatter);

        try {
            fileStorage.update("timestamp",
                    helpers.intToArr(2), // Column index to check ("emp_id" is at index 2)
                    helpers.paramsToArr(empId), // Condition value to check (replace with the current "empId" value)
                    helpers.intToArr(4), // Column index to update ("finish_date" is at index 4)
                    helpers.paramsToArr(formattedDepartureDate), // New value for the "finish_date" column
                    true,
                    1
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

