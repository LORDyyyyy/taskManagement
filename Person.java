public class Person {
    private int ID;
    private String name, password;
    private FileStorage fs = new FileStorage();
    private Helpers hlp = new Helpers();

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


    public boolean login(String name, String password, String nameFile) throws Exception {
        String[][] res = fs.read(nameFile, hlp.intToArr(1, 2), hlp.paramsToArr(getName(), getPassword()));
        for (String[] row : res) {
            if (getName().equals(name) && getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void add(String nameFile) throws Exception {
        fs.add(nameFile, hlp.paramsToArr(getId(), getName(), getPassword()));
    }

    public boolean update(int ID, String name, String nameFile) throws Exception {
        String[][] res = fs.read(nameFile, hlp.intToArr(0), hlp.paramsToArr(ID));
        for (String[] row : res) {
            if (Integer.parseInt(row[0]) == ID) {
                setName(name);
                fs.update(nameFile, hlp.intToArr(0), hlp.paramsToArr(ID), hlp.intToArr(1), hlp.paramsToArr(name));
                return true;
            }
        }
        return false;
    }
    
    public boolean delete(int ID, String nameFile) throws Exception {
        String[][] res = fs.read(nameFile, hlp.intToArr(0), hlp.paramsToArr(ID));
        for (String[] row : res) {
            if (Integer.parseInt(row[0]) == ID) {
                fs.delete(nameFile, hlp.intToArr(0), hlp.paramsToArr(ID));
                return true;
            }
        }
        return false;
    }
    
}
