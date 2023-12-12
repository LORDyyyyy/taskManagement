public class Admin extends Person 
{
}


    // public void addEmployee(Employee employee, String nameFile) throws Exception 
    // {
    //     fs.add(nameFile, hlp.paramsToArr(employee.getId(), employee.getName(), employee.getPassword()));
    // }

    // public boolean updateEmployee(int ID, String name, String nameFile) throws Exception 
    // {
    //     String[][] res = fs.read(nameFile, hlp.intToArr(0), hlp.paramsToArr(ID));
    //     for (String[] row : res)
    //     {
    //         if (Integer.parseInt(row[0]) == ID)
    //         {
    //             setName(name);
    //             fs.update(nameFile, hlp.intToArr(0), hlp.paramsToArr(ID), hlp.intToArr(1), hlp.paramsToArr(name));
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // public boolean deleteEmployee(int ID, String nameFile) throws Exception
    // {
    //     String[][] res = fs.read(nameFile, hlp.intToArr(0), hlp.paramsToArr(ID));
    //     for (String[] row : res)
    //     {
    //         if (Integer.parseInt(row[0]) == ID)
    //         {
    //             fs.delete(nameFile, hlp.intToArr(0), hlp.paramsToArr(ID));
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // public void addEmployeeType(String type, String nameFile) throws Exception 
    // {

    //     fs.add(nameFile, hlp.paramsToArr(type));
    // }

    // public boolean updateEmployeeType(String oldType, String newType, String nameFile) throws Exception 
    // {
    //     String[][] res = fs.read(nameFile, hlp.intToArr(0), hlp.paramsToArr(oldType));
    //     for (String[] row : res)
    //     {
    //         if (row[0].equals(oldType))
    //         {
    //             fs.update(nameFile, hlp.intToArr(0), hlp.paramsToArr(oldType), hlp.intToArr(1), hlp.paramsToArr(newType));
    //             return true;
    //         }
    //     }
    //     return false;
    // }
    
