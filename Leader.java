public class Leader extends Person 
{
    private String email;

    public Leader(String name, String password, String email)
     {
        super(name, password, "leader");
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
