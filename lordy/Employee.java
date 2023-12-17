import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Employee extends Person {

    public Employee(String name, String password) {
        super(name, password, "emp");
    }

    /**
     * employee see respond to his request
     * 
     * @return 2D array on success
     */
    public String[][] seeRespond() throws Exception {
        String[][] see = fs.read("request", hlp.intToArr(1), hlp.paramsToArr(this.getId()));
        return (see);
    }

    /**
     * employee send request
     * 
     * @param emp_id  - employee id who send request
     * @param massage - request who wants to be send
     * @return 0 if success
     */
    public int sendRequest(String massage) throws Exception {
        int Id = fs.getNextID("request");
        LocalDate time = LocalDate.now();
        try {
            fs.add("request", hlp.paramsToArr(Id, this.getId(), massage, time, "Under Review"));
            return 0;
        } catch (Exception ex) {
            throw new Exception("Request not added..." + ex.getMessage());
        }
    }

    public void markAttendance() throws Exception {
        LocalDateTime currentDay = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedCurrentDay = currentDay.format(dateFormatter);

        LocalDateTime attendanceDate = LocalDateTime.now();
        DateTimeFormatter attendanceFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedAttendanceDate = attendanceDate.format(attendanceFormatter);

        fs.add("timestamp",
                hlp.paramsToArr(fs.getNextID("timestamp"),
                        formattedCurrentDay,
                        this.getId(),
                        formattedAttendanceDate, "null"));
    }

    public void markDeparture() {
        LocalDateTime departureDate = LocalDateTime.now();
        DateTimeFormatter departureDateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDepartureDate = departureDate.format(departureDateFormatter);

        try {
            fs.update("timestamp",
                    hlp.intToArr(2),
                    hlp.paramsToArr(this.getId()),
                    hlp.intToArr(4),
                    hlp.paramsToArr(formattedDepartureDate),
                    true,
                    1);
        } catch (Exception e) {
        }
    }

    public boolean addStartTime(int task_id) {
        try {
            String[][] existingTaskLog = fs.read("task_log", hlp.intToArr(1), hlp.paramsToArr(task_id));

            if (existingTaskLog.length > 0 && "null".equals(existingTaskLog[0][3])) {
                LocalDateTime startTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedStartTime = startTime.format(formatter);
                fs.update(
                        "task_log",
                        hlp.intToArr(1),
                        hlp.paramsToArr(task_id),
                        hlp.intToArr(3),
                        hlp.paramsToArr(formattedStartTime));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public String[][] viewTasks() {
        try {
            return (fs.read("tasks", hlp.intToArr(3), hlp.paramsToArr(this.getId())));
        } catch (Exception e) {
            return new String[0][0];
        }
    }

    public boolean addFinishTime(int taskId) {
        FileStorage fs = new FileStorage();
        Helpers hlp = new Helpers();
        LocalDateTime attendanceDate = LocalDateTime.now();
        DateTimeFormatter attendanceFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedAttendanceDate = attendanceDate.format(attendanceFormatter);
        LocalDateTime departureDate = LocalDateTime.now();
        DateTimeFormatter departureDateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDepartureDate = departureDate.format(attendanceFormatter);

        try {
            String[][] existingTaskLog = fs.read("task_log", hlp.intToArr(1, 2), hlp.paramsToArr(taskId, this.getId()));

            if (existingTaskLog.length > 0 &&
                    !"null".equals(existingTaskLog[0][3]) &&
                    ("null".equals(existingTaskLog[0][4])) &&
                    ("null".equals(existingTaskLog[0][5])))
            {
                try {
                    String startDateString = existingTaskLog[0][3];
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime startTime = LocalTime.parse(startDateString, timeFormatter);
                    LocalDate currentDate = LocalDate.now();
                    LocalDateTime startDateTime = LocalDateTime.of(currentDate, startTime);
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    Duration duration = Duration.between(startDateTime, currentDateTime);
                    String formattedDuration = String.format("%d:%02d:%02d",
                            duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());

                    fs.update(
                            "task_log",
                            hlp.intToArr(1),
                            hlp.paramsToArr(taskId),
                            hlp.intToArr(4, 5),
                            hlp.paramsToArr(formattedDepartureDate, formattedDuration));
                    return true;
                } catch (DateTimeParseException e) {
                    return false;
                }
            }
            else
            {
                System.out.println(existingTaskLog[0][0]);
                System.out.println("empty table");
                
                return false;
            }
        } catch (Exception e)
        {
            return false;
        }
    }
}
