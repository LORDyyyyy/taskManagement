import java.time.LocalDate;
import java.time.Duration;


public abstract class Person {

    private int ID;
    private String name;
    private String password;
    public String table_name;
    protected FileStorage fs = new FileStorage();
    protected Helpers hlp = new Helpers();


    public Person(String filename) {
        this.table_name = filename;
    }

    public Person(String name, String password, String filename) {
        this.ID = fs.getNextID(filename);
        this.name = name;
        this.password = password;
        this.table_name = filename;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void add() throws Exception {
        fs.add(this.table_name, hlp.paramsToArr(getId(), getName(), getPassword()));
    }

    public boolean login(String name, String password) throws Exception
    {
        String[][] res = fs.read(this.table_name, hlp.intToArr(1, 2), hlp.paramsToArr(getName(), getPassword()));
        filterRequests();
        for (String[] row : res) {
            if (getName().equals(name) && getPassword().equals(password))
            {
                this.ID = Integer.parseInt(row[0]);
                return true;
            }
        }
        return false;
    }

    static private void filterRequests() throws Exception 
    {
        FileStorage file = new FileStorage();
        Helpers help = new Helpers();
        String[][] all = file.read("request");
        LocalDate now = LocalDate.now();

        for (String[] row : all)
        {
            LocalDate sendTime = LocalDate.parse(row[3]);
            Duration diff = Duration.between(now.atStartOfDay(), sendTime.atStartOfDay());
            long days = diff.toDays();

            if (days > 7)
            {
                try
                {
                    file.delete("request", help.intToArr(0), help.paramsToArr(row[0]));
                } catch (Exception ex) {
                    continue;
                }
            }
        }
    }
}
