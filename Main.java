public class Main {
    public static void main(String[] args) throws Exception
    {
        FileStorage fs = new FileStorage();
        // Helpers hlp = new Helpers();
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
        int[] temp = new int[] {0, 1 };
        int[] temp2 = new int[] {1, 2};
        // if (fs.delete("emp", temp, Helpers.paramsToArr("ahmed", "ALIII")) == 1)
        //     System.out.println("error!");
            
        // fs.add("emp", Helpers.paramsToArr(fs.getNextID("emp"), "ahmed", "ALIIIeee"));

        // String[][] res = fs.read("leader", new int[] {}, Helpers.paramsToArr("khalid", "password122155"));

        fs.update("emp",
                temp, Helpers.paramsToArr(3, "ahmed" ),
                temp2, Helpers.paramsToArr("khalid",  "newPass"));

        // if (Helpers.isEmptyTable(res))
        //     System.out.println("acc not found");
        // else
        //     System.out.println("Login done");

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
