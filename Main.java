public class Main {
    public static void main(String[] args) throws Exception
    {
        FileStorage fs = new FileStorage();


        // Get a record from a table, specify the columns to check, and the values to compare
        int[] temp = new int[] {0, 1};
        String[][] res = fs.read("emp.txt", temp, Helpers.paramsToArr(1, "man"));

        // Get all record from a table
        String[][] table = fs.read("emp");

        // Get the header of a table.
        System.out.println(fs.readHeaer("emp.txt")[0]);
        System.out.println(fs.readHeaer("emp.txt")[1]);
        
        if (Helpers.isEmptyTable(res))
            System.out.println("its empty!!!");

            for (String[] row : res)
        {
            for (String value : row)
            {
                System.out.print(value + " \t| ");
            }
            System.out.println();
        }

        System.out.println(fs.toString()); // TODO
    }
}
