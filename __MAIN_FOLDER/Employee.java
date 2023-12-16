import java.time.LocalDate;
import java.time.Duration;

public class Employee extends Person 
{
    public Employee(String name, String password) 
    {
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
        }
        catch (Exception ex){
            throw new Exception("Request not added..."+ex.getMessage());
        }
    }

}
