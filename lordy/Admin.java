public class Admin extends Person {

    public Admin(String name, String password) 
    {
        super(name, password, "admin");
    }

    public boolean add(String Name, String password, String type) throws Exception
    {
        if (type.equals("leader"))
        {
            Leader lead = new Leader(Name, password);
            fs.add(lead.table_name, hlp.paramsToArr(lead.getId(), Name, password));
            return (true);
        }
        else if (type.equals("emp"))
        {
            Employee emp = new Employee(Name, password);
            fs.add(emp.table_name, hlp.paramsToArr(emp.getId(), Name, password));
            return (true);
        }

        return (false);
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

    public String[][] read(String fileName) throws Exception {
        String[][] res = fs.read(fileName);
        return res;
    }
}