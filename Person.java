public abstract class Person {
    private int ID;
    private String name, password;
    protected FileStorage fs = new FileStorage();
    protected Helpers hlp = new Helpers();

    public Person() {
    }

    public Person(String name, String password, String filename) {
        this.ID = fs.getNextID(filename);
        this.name = name;
        this.password = password;
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

    public void add(String nameFile) throws Exception {
        fs.add(nameFile, hlp.paramsToArr(getId(), getName(), getPassword()));
    }

    public boolean login(String name, String password, String nameFile) throws Exception {
        String[][] res = fs.read(nameFile, hlp.intToArr(1, 2), hlp.paramsToArr(getName(), getPassword()));
        for (String[] row : res) {
            if (getName().equals(name) && getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
