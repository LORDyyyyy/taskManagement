public class Main {
    public static void main(String[] args) throws Exception
    {
        FileStorage fs = new FileStorage();
        Helpers hlp = new Helpers();
        Menu menu = new Menu();

        menu.MainMenu();



        // menu.EmployeeMenu();
        // menu.AdminMenu();
        // menu.LeadersMenu();
        // menu.ProjectsMenu();
        // menu.TasksMenu();

        // fs.createTable("alo", "id", "task_id");
        // fs.reload("task");
        // fs.createTable("tasklog","task_log_id","task_id","emp_id","start_date","finish_date", "duration");
        // System.out.println("file has successfulyy created");
        // fs.reload("alo");
        // fs.add("tasklog", hlp.paramsToArr(fs.getNextID("tasklog"), fs.getNextID("tasklog"), "88", "null", "null", "null"));

        // System.out.println("." + hlp.printCell("id", 16) + ".");

        // System.out.println((1.0f / 0));


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
        

        // fs.add("emp", hlp.paramsToArr(fs.getNextID("emp"), "ali", "password122155"));
        // String[][] res = fs.read("emp.txt", temp, Helpers.paramsToArr("khalid"));
        // int[] temp = new int[] {0, 1 };
        // int[] temp2 = new int[] {1, 2};
        // if (fs.delete("emp", temp, Helpers.paramsToArr("ahmed", "ALIII")) == 1)
        //     System.out.println("error!");

        // fs.add("emp", hlp.paramsToArr(1, "ahmed", "sadsa"));

        // SELECT * FROM emp WHERE column[0] = 5
        // String[][] res = fs.read("emp", hlp.intToArr(0), hlp.paramsToArr(5));

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




        // fs.delete("emp", hlp.intToArr(0), hlp.paramsToArr(14));


        // UPDATE emp SET name = ahmed AND password = newpass WHERE ID = 3 ;
        //                  columns id index = 0
        //                  column name index = 1
        //                  column password index = 2
        // fs.update("emp",
        //             hlp.intToArr(0),
        //             hlp.paramsToArr(15),
        //             hlp.intToArr(2),
        //             hlp.paramsToArr( "newpass")
        //         );




        // System.out.println();
        // fs.add("emp", hlp.paramsToArr(fs.getNextID("emp"), "ali mohammed", "newPass"));
        // String[][] res = fs.read("emp",  hlp.intToArr(1), hlp.paramsToArr("khalid"));

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
