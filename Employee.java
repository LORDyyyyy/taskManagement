public class Employee extends Person 
{

    public Employee(String name, String password) 
    {
        super(name, password, "emp");
    }

    public boolean login(String username, String password) throws Exception {
        return super.login(username, password, "emp");
    }
}
