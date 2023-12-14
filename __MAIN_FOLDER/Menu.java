import java.util.Scanner;
import static ConsoleColors.ConsoleColors.*;


public class Menu {

	private Scanner input = new Scanner(System.in);
	private Helpers hlp = new Helpers();
	private String errMessage = RED + "Wrong Input!" + RESET;
	private String seperator = CYAN_BOLD + "-------------------------" + RESET; 
	private String welcome = """
		██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗██╗
		██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝██║
		██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗  ██║
		██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝  ╚═╝
		╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗██╗
		 ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝
			""";
	private Admin admin;
	private Employee emp;
	private Leader leader;


	/**
	 * The first Menu that will be displayed for all users.
	 */
	public void MainMenu()
	{
		System.out.println(BLUE_BOLD + this.welcome + RESET);
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD + "At any page, enter exit to quit the program, or enter back to go a step back." + RESET);
		System.out.println(this.seperator);
		hlp.printOptions("Admin", "Leader", "Employee");

		String answer = hlp.readWord("Your Choice:");

		while (!hlp.rightInput(true, answer, 1, 2, 3, "admin", "leader", "employee"))
		{
			System.out.println(errMessage);
			answer = hlp.readWord("Your Choice:");
		}

		switch (answer.toLowerCase()) {
			case "exit":
			case "back":
				System.exit(0);
			case "1":
			case "admin":
				this.Login("admin");
				break;
			case "2":
			case "leader":
				this.Login("leader");
				break;
			case "3":
			case "employee":
				this.Login("emp");
		}
	}


	/**
	 * The Login function for all users
	 * 
	 * @param type - User type, admin/leader/employee
	 */
	private void Login(String type)
	{
		String name = hlp.readWord("Your name: ");
		String password = hlp.readWord("Your Password: ");
		try {
			if (type.equals("emp"))
			{
				this.emp = new Employee(name, password);
				while (!emp.login(emp.getName(), emp.getPassword()))
				{
					System.out.println(RED_BOLD + "Account was not found!");
					System.out.println("Please Re-enter your information or exit the program" + RESET);
					emp.setName(hlp.readWord("Your name: "));
					emp.setPassword(hlp.readWord("Your Password: "));
				}
				this.EmployeeMenu();
			}
			else if (type.equals("leader"))
			{
				this.leader = new Leader(name, password);

				while (!leader.login(leader.getName(), leader.getPassword()))
				{
					System.out.println(RED_BOLD + "Account was not found!");
					System.out.println("Please Re-enter your information or exit the program" + RESET);
					leader.setName(hlp.readWord("Your name: "));
					leader.setPassword(hlp.readWord("Your Password: "));
				}
				this.LeadersMenu();
			}
			else if (type.equals("admin"))
			{
				this.admin = new Admin(name, password);

				while (!admin.login(admin.getName(), admin.getPassword()))
				{
					System.out.println(RED_BOLD + "Account was not found!");
					System.out.println("Please Re-enter your information or exit the program" + RESET);
					admin.setName(hlp.readWord("Your name: "));
					admin.setPassword(hlp.readWord("Your Password: "));
				}
				this.AdminMenu();
			}
		} catch (Exception e) {
			this.MainMenu();
		}
	}

	/**
	 * The Leader Menu that will be displayed for the leader.
	 */
	private void LeadersMenu()
	{
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD  + "Leader Menu" + RESET);
		System.out.println(this.seperator);
		hlp.printOptions("Projects", "Requests", "Calendar");

		String answer = hlp.readWord("Your Choice:");

		while (!hlp.rightInput(true, answer, 1, 2, 3, "projects", "requests", "calendar"))
		{
			System.out.println(errMessage);
			answer = hlp.readWord("Your Choice:");
		}

		switch (answer) {
			case "exit":
				System.exit(0);
				break;
			case "back":
				this.MainMenu();
				break;
			case "1":
			case "projects":
				this.ProjectsMenu();
				break;
			case "2":
			case "requests":
				// this.RequestsMenu();
				break;
			case "3":
			case "calendar":
				// this.Calender
		}
	}


	private void ProjectsMenu()
	{
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD  + "Projects Menu" + RESET);
		System.out.println(this.seperator);
		hlp.printOptions("Show Current Projects", "Add", "Update", "Delete");

		String answer = hlp.readWord("Your Choice:");

		while (!hlp.rightInput(true, answer, 1,2,3,4, "projects", "Add", "Update", "Delete"))
		{
			System.out.println(errMessage);
			answer = hlp.readWord("Your Choice:");
		}

		switch (answer) {
			case "exit":
				System.exit(0);
				break;
			case "back":
				this.LeadersMenu();
				break;
			case "1":
			case "projects":
				this.ShowProjects();
				break;
			case "2":
			case "requests":
				// this.AddProject();
				break;
			case "3":
			case "calendar":
				// this.Update();
				break;
			case "4":
			case "delete":
				// this.Delete();
		}
	}


	private void ShowProjects()
	{
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD  + "Projects" + RESET);
		System.out.println(this.seperator);
		hlp.printOptions("All projects will be here...");
	}

	private void TasksMenu()
	{
		System.out.println("Tasks Menu");
		System.out.println("----------");
		System.out.println("1 => Add"
				+ "\n" + "2 => Update"
				+ "\n" + "3 => Delete\n");
	}


	/**
	 * The Employee Menu that will be displayed for the Employee.
	 */
	private void EmployeeMenu()
	{
		System.out.println("Employee Menu");
		System.out.println("---------------------------");
		System.out.println("1 => Send a Mission Request"
				+ "\n" + "2 => Send a Vacation Request"
				+ "\n" + "3 => Task log\n");
	}


	/**
	 * The Admin Menu that will be displayed for the Admin.
	 */
	private void AdminMenu()
	{
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD + "Admin Menu" + RESET);
		System.out.println(this.seperator);
		hlp.printOptions("Add Leaders/Employees", "Update Leaders/Employees", "Delete Leaders/Employees");

		String answer = hlp.readWord("Your Choice:");
		while (!hlp.rightInput(true, answer, 1, 2, 3, "add", "update", "delete"))
		{
			System.out.println(errMessage);
			answer = hlp.readWord("Your Choice:");
		}

		switch (answer) {
			case "exit":
				System.exit(0);
			case "back":
				this.MainMenu();
				break;
		}

		String answer2 = hlp.readWord("User Type (leader/employee):");
		while (!hlp.rightInput(false, answer2, 1, 2, "leader", "employee"))
		{
			System.out.println(errMessage);
			answer2 = hlp.readWord("Your Choice:");
		}
		String type = (answer2.equals("1") || answer2.equals("leader")) ? "leader" : "emp";

		switch (answer) {
			case "1":
			case "add":
				this.AdminAdd(type);
				break;
			case "2":
			case "update":
				this.AdminUpdate(type);
				break;
			case "3":
			case "delete":
				this.AdminDelete(type);
		}
	}


	private void AdminAdd(String type)
	{
		String name = hlp.readWord("User name: ");
		String password = hlp.readWord("User Password: ");

		try {
			while (!this.admin.add(name, password, type))
			{
				System.out.println(RED_BOLD + "An Error occured!");
				System.out.println("Please Re enter the info." + RESET);
				name = hlp.readWord("User name: ");
				password = hlp.readWord("User Password: ");
				if (name.equals("back") || password.equals("back"))
					this.AdminMenu();
			}

			System.out.println(GREEN_BOLD + "User Added Successfully!" + RESET);
			this.AdminMenu();
		} catch (Exception e) {
			this.AdminMenu();
		}
	}


	private void AdminUpdate(String type)
	{
		String id = hlp.readWord("User ID: ");
		String name = hlp.readWord("New user name: ");

		try {
			while (!id.matches("^[0-9]+$") || !this.admin.update(Integer.parseInt(id), name, type))
			{
				System.out.println(RED_BOLD + "An Error occured!");
				System.out.println("Please Re enter the info." + RESET);
				id = hlp.readWord("User ID: ");
				name = hlp.readWord("User name: ");

				if (name.equals("back"))
					this.AdminMenu();
			}

			System.out.println(GREEN_BOLD + "User Updated Successfully!" + RESET);
			this.AdminMenu();
		} catch (Exception e) {
			this.AdminMenu();
		}
	}

	private void AdminDelete(String type)
	{
		String id = hlp.readWord("User ID: ");

		try {
			while (!id.matches("^[0-9]+$") || !this.admin.delete(Integer.parseInt(id), type))
			{
				System.out.println(RED_BOLD + "An Error occured!");
				System.out.println("Please Re enter the info." + RESET);
				id = hlp.readWord("User ID: ");
				if (id.equals("back"))
					this.AdminMenu();
			}

			System.out.println(GREEN_BOLD + "User Deleted Successfully!" + RESET);
			this.AdminMenu();
		} catch (Exception e) {
			this.AdminMenu();
		}
	}
}
