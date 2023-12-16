import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Employee extends Person {


    public Employee(String name, String password) {
        super(name, password, "emp");
    }


    /** 
    * employee see respond to his request
    * @return 2D array on success
    */
    public String[][] seeRespond() throws Exception {
        String[][] see = fs.read("request", hlp.intToArr(1), hlp.paramsToArr(this.getId()));
        return (see);
    }

    /**
     * employee send request
     * 
     * @param emp_id - employee id who send request
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
        } catch (Exception e) {}
    }

    // // @Override
    // // public void finalize() throws Throwable {
    // //     // try {
    // //     System.out.println("i am in the finilize");
    // //     this.markDeparture();
    // //     // } finally {
    // //     //     // Always call the superclass finalize method
    // //     //     super.finalize();
    // //     // }
    // // }
    // public void finalize() throws Exception {
    //     this.markDeparture();
    //     System.out.println("garbage collected");
    // }

}
