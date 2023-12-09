public class Main {
    public static void main(String[] args) throws Exception
    {
        FileStorage fs = new FileStorage();


        // Get a record from a table, specify the columns to check, and the values to compare
        int[] temp = new int[] {1};

        // Get all record from a table
        // String[][] table = fs.read("emp");

        // Get the header of a table.
        System.out.println(fs.readHeader("emp.txt")[0]);
        System.out.println(fs.readHeader("emp.txt")[1]);
        
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
        
        fs.add("emp", Helpers.paramsToArr(fs.getNextID("emp"), "khalid", "password122155"));
        String[][] res = fs.read("emp.txt", temp, Helpers.paramsToArr("khalid"));
        for (String[] row : res)
        {
            for (String value : row) {
                System.out.print("(" + value + ")" + " \t    | ");
            }
            System.out.println();
        }

        // System.out.println(fs.toString()); // TODO
    }
}
