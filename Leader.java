public class Leader extends Person 
{
    private String email;
    private FileStorage fs = new FileStorage();
    private Helpers hlp = new Helpers();

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

    public void add(String nameFile) throws Exception {
        fs.add(nameFile, hlp.paramsToArr(getId(), getName(), getPassword(), getEmail()));
    }
}
