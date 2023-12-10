public class Main {
    public static void main(String[] args) throws Exception
    {
        FileStorage fs = new FileStorage();
        Helpers hlp = new Helpers();


        // Get a record from a table, specify the columns to check, and the values to compare
        

        // Get all record from a table
        // String[][] table = fs.read("emp");

        // Get the header of a table.
        // System.out.println(fs.readHeader("emp.txt")[0]);
        // System.out.println(fs.readHeader("emp.txt")[1]);
        
        // if (Helpers.isEmptyTable(res))
        //     System.out.println("its empty!!!");



        // String[][] res = fs.read("emp.txt", temp, Helpers.paramsToArr("man"));
        // for (String[] row : res)
        // {
        //     for (String value : row) {
        //         System.out.print("(" + value + ")" + " \t    | ");
        //     }
        //     System.out.println();
        // }

        // System.out.println("---------");

        // res = fs.read("emp.txt", temp, Helpers.paramsToArr("man"), true);
        // for (String[] row : res)
        // {
        //     for (String value : row) {
        //         System.out.print("(" + value + ")" + " \t    | ");
        //     }
        //     System.out.println();
        // }

        // System.out.println("-----------------");


        // if (fs.delete("emp.txt", temp, Helpers.paramsToArr("khalid")) == 1)
        // System.out.println("error!");
        

        // fs.add("emp", Helpers.paramsToArr(fs.getNextID("emp"), "khalid", "password122155"));
        // String[][] res = fs.read("emp.txt", temp, Helpers.paramsToArr("khalid"));
        // int[] temp = new int[] {0, 1 };
        // int[] temp2 = new int[] {1, 2};
        // if (fs.delete("emp", temp, Helpers.paramsToArr("ahmed", "ALIII")) == 1)
        //     System.out.println("error!");

        // fs.add("emp", hlp.paramsToArr(1, "ahmed", "sadsa"));

        // String[][] res = fs.read("emp", hlp.intToArr(1,2), hlp.paramsToArr("ahmed", "newPass"));

        // fs.update("emp",
        //         hlp.intToArr(1), hlp.paramsToArr(881),
        //         hlp.intToArr(1), hlp.paramsToArr("Ali"));


        // fs.createTable("tmp", "id", "name", "password", "email", "date");


        // fs.delete("emp", hlp.intToArr(0),

        // hlp.paramsToArr(889, "dsas", true, false));

        // String[][] res = fs.read("emp",
        //                                     hlp.intToArr(1, 2),
        //                                     hlp.paramsToArr("ali", "newPass"));
        // if (hlp.isEmptyTable(res))
        //     System.out.println("acc not found");
        // else
        //     System.out.println("Login done");


        // String[][] res = fs.read(
        //         "emp",
        //         hlp.intToArr(1),
        //         hlp.paramsToArr("ahmed"));




        // fs.delete("emp", hlp.intToArr(0, 1), hlp.paramsToArr(3, "ahmed"));


        // UPDATE emp SET name = Ahmed where ID = 3;
        //                 column name index = 1
        //                  columns id index = 0
        fs.update("emp",
                hlp.intToArr(1),
                hlp.paramsToArr("ALOOOOOO"),
                hlp.intToArr(1),
                hlp.paramsToArr("NOOOOOOOO"));




        // System.out.println();
        // fs.add("emp", hlp.paramsToArr(fs.getNextID("emp"), "ali mohammed", "newPass"));
        // String[][] res = fs.read("emp");

        // for (String[] row : res)
        // {
        //     for (String value : row)
        //     {
        //         System.out.print("(" + value + ")" + " \t    | ");
        //     }
        //     System.out.println();
        // }

        // System.out.println(fs.toString()); // TODO
    }
    
}
