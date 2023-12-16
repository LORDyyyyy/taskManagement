import static ConsoleColors.ConsoleColors.*;


public class Main {
    public static void main(String[] args) throws Exception
    {
        // FileStorage fs = new FileStorage();
        // Helpers hlp = new Helpers();

        Menu menu = new Menu();
        menu.MainMenu();

        // fs.createTable("projects",
        // "project_id","project_title","project_description","project_startTime","project_endtime");

        // System.gc();
        // fs.createTable("timestamp", "id", "date", "emp_id", "start_date", "finish_date");

        // fs.createTable("request", "id","emp_id", "massage", "send date", "status");

        // Employee emp = new Employee("asd", "qwe");

        // emp.login(emp.getName(), emp.getPassword());

        // emp.send("Hello PLEASE HELP ME");
        // emp.send("Hello PLEASE HELP ME AGAIN");
        // emp.send("give me money pls");

        // for (String[] row: emp.seeRespond(emp.getId()))
        // {
        //     for (int i = 0; i < row.length; i++) {
        //         System.out.printf("|%-30s", row[i]);
        //     }
        //     System.out.println("|");
        // }



        // Admin ad = new Admin("mohamed", "12345");

        // System.out.print(BLACK_BACKGROUND + WHITE_BOLD);
        // System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
        // System.out.printf("|%-14s%-16s|%-13s%-17s|%-12s%-18s|\n", " ", "ID", " ", "Name", " " , "Password");
        // System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
        // for (String[] row: res)
        // {
        //     for (int i = 0; i < row.length; i++)
        //     {
        //         System.out.printf("|%-30s", row[i]);
        //     }
        //     System.out.println("|");
        // }
        // System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
        // System.out.print(RESET);

        // fs.createTable("test", "id", "name");
        // fs.add("test", hlp.paramsToArr("4", "ali"));
        // fs.add("test", hlp.paramsToArr("5", "ahmed"));
        // fs.add("test", hlp.paramsToArr("6", "a"));

        // fs.update("test", hlp.intToArr(0), hlp.paramsToArr(4), hlp.intToArr(1), hlp.paramsToArr("66656"));

        // fs.delete("test", hlp.intToArr(0), hlp.paramsToArr(6));

        // fs.delete("tmp", hlp.intToArr(0), hlp.paramsToArr(5));

        // String[][] res = fs.read("test.txt", hlp.intToArr(0), hlp.paramsToArr("5"), true);
        // for (String[] row : res)
        // {
        //     for (String value : row) {
        //         System.out.print("(" + value + ")" + " \t    | ");
        //     }
        //     System.out.println();
        // }


        // Admin admin = new Admin("mohamed", "12345");

        // if (admin.login("mohamed", "12345"))
        //     System.out.println("wel");
        // else
        //     System.out.println("no");

        // admin.add("asdsad", "1423456", "emp");

        // fs.reload("task");
        // fs.add("task", hlp.paramsToArr(1, 5));

        // menu.EmployeeMenu();
        // menu.AdminMenu();
        // menu.LeadersMenu();
        // menu.ProjectsMenu();
        // menu.TasksMenu();

        // fs.createTable("task", "id", "task_id");
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
