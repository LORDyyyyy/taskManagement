
public class Menu {

	public static void MainMenu()
	{
		System.out.println("Main Menu");
    	System.out.println("---------");
    	System.out.println("1 => Leader"
    			+ "\n" + "2 => Employee"
    			+ "\n" +   "3 => Admin\n");
	}
	
	
	public static void LeadersMenu()
	{
		System.out.println("Leader Menu");
    	System.out.println("-----------");
    	System.out.println("1 => Projects"
    			+ "\n" + "2 => Tasks"
    			+ "\n" +   "3 => Calendar\n");
	}
	
	
	public static void ProjectsMenu()
	{
		System.out.println("Projects Menu");
    	System.out.println("-------------");
    	System.out.println("1 => Add"
    			+ "\n" + "2 => Update"
    			+ "\n" +   "3 => Delete\n");
	}
	
	
	public static void TasksMenu()
	{
		System.out.println("Tasks Menu");
    	System.out.println("----------");
    	System.out.println("1 => Add"
    			+ "\n" + "2 => Update"
    			+ "\n" +   "3 => Delete\n");
	}
	
	
	public static void EmployeeMenu()
	{
		System.out.println("Employee Menu");
    	System.out.println("---------------------------");
    	System.out.println("1 => Send a Mission Request"
    			+ "\n" + "2 => Send a Vacation Request"
    			+ "\n" +   "3 => Task log\n");
	}
	
	
	public static void AdminMenu()
	{
		System.out.println("Admin Menu");
    	System.out.println("----------------------------------");
    	System.out.println("1 => Add"
    			+ "\n" + "2 => Create"
    			+ "\n" +   "3 => Delete Leaders and Employees\n");
	}
	
	
}
