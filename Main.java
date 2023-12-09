public class Main {
    public static void main(String[] args) throws Exception
    {
        FileStorage fs = new FileStorage();


        int[] temp = new int[] {0, 1};
        // String[] tempstr = new String[] { "man" };

        String[][] ans = fs.read("emp.txt", temp, Helpers.paramsToArr(1, "man"));

        // String[][] ans = fs.read("emp.txt");

        System.out.println(fs.readHeaer("emp.txt")[0]);
        System.out.println(fs.readHeaer("emp.txt")[1]);
        
        // if (Helpers.isEmptyTable(ans))
            // System.out.println("its empty!!!");

        for (String[] row : ans) {
            for (String col : row) {
                System.out.print(col + " \t| ");
            }
            System.out.println();
        }

        // System.out.println(fs.toString());
        // System.out.println(Helpers.maxArr(new int[] { 1, 88, 2, 3, 6 }));
    }
}
