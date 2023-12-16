import java.util.Scanner;
import static ConsoleColors.ConsoleColors.*;

public class Menu {

	private Scanner input = new Scanner(System.in);
	protected String errMessage = RED + "Wrong Input!" + RESET;
	protected String seperator = CYAN_BOLD + "-------------------------" + RESET; 
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
	 * Read a single word from the user's input
	 * And delete the the trailing new line
	 *
	 * @param message - The inputed word
	 *
	 * @return The inputed word
	 */
	private String readWord(String message)
	{
		System.out.print(YELLOW_BOLD + message + " " + GREEN_BOLD);
		String word = input.next();

		System.out.print(RESET);
		input.nextLine();

		return (word.strip());
	}


	/**
	 * Read a line from the user's input
	 *
	 * @param message - The inputed line
	 *
	 * @return - The inputed line
	 */
	private String readLine(String message)
	{
		System.out.print(YELLOW_BOLD + message + " ");
		String line = input.nextLine();

		System.out.print(RESET);

		return (line);
	}


	/**
	 * Check if the user's choice is right.
	 *
	 * @param input - The choice that the user entered
	 * @param options - The option that the user should choice from.
	 *
	 * @return true | false
	 */
	private boolean rightInput(boolean Back, String input, Object... options)
	{
		for (int i = 0; i < options.length; i++)
			if (String.valueOf(options[i]).toLowerCase().equals(input.toLowerCase()))
				return (true);
		return ((Back ? (input.toLowerCase().equals("exit") || input.toLowerCase().equals("back")) : false));
	}


	/**
	 * Print the options that the user should choose from
	 *
	 * @param options - The options
	 */
	private void printOptions(String... options)
	{
		for (int i = 0; i < options.length; i++)
			System.out.println(BLUE_BOLD + (i + 1) + RESET + ". " + options[i]);
		System.out.println();
	}


	/**
	 * The first Menu that will be displayed for all users.
	 */
	public void MainMenu()
	{
		System.out.println(BLUE_BOLD + this.welcome + RESET);
		System.out.println(seperator);
		System.out.println(BLUE_BOLD + "At any page, enter exit to quit the program, or enter back to go a step back." + RESET);
		System.out.println(seperator);
		this.printOptions("Admin", "Leader", "Employee");

		String answer = this.readWord("Your Choice:");

		while (!this.rightInput(true, answer, 1, 2, 3, "admin", "leader", "employee"))
		{
			System.out.println(errMessage);
			answer = this.readWord("Your Choice:");
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

	private void Login(String type)
	{
		String name = this.readWord("Your name: ");
		String password = this.readWord("Your Password: ");
		try {
			if (type.equals("emp"))
			{
				this.emp = new Employee(name, password);

				while (!emp.login(emp.getName(), emp.getPassword()))
				{
					System.out.println(RED_BOLD + "Account was not found!");
					System.out.println("Please Re-enter your information or exit the program" + RESET);
					emp.setName(this.readWord("Your name: "));
					emp.setPassword(this.readWord("Your Password: "));
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
					leader.setName(this.readWord("Your name: "));
					leader.setPassword(this.readWord("Your Password: "));
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
					admin.setName(this.readWord("Your name: "));
					admin.setPassword(this.readWord("Your Password: "));
				}
				this.AdminMenu();
			}
		} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * The Leader Menu that will be displayed for the leader.
	 */
	private void LeadersMenu()
	{
		System.out.println(seperator);
		System.out.println(BLUE_BOLD  + "Leader Menu" + RESET);
		System.out.println(seperator);
		this.printOptions("Projects", "Requests", "Calendar");

		String answer = this.readWord("Your Choice:");

		while (!this.rightInput(true, answer, 1, 2, 3, "projects", "requests", "calendar"))
		{
			System.out.println(errMessage);
			answer = this.readWord("Your Choice:");
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
		System.out.println(seperator);
		System.out.println(BLUE_BOLD  + "Projects Menu" + RESET);
		System.out.println(seperator);
		this.printOptions("Show Current Projects", "Add", "Update", "Delete");

		String answer = this.readWord("Your Choice:");

		while (!this.rightInput(true, answer, 1,2,3,4, "projects", "Add", "Update", "Delete"))
		{
			System.out.println(errMessage);
			answer = this.readWord("Your Choice:");
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
		System.out.println(seperator);
		System.out.println(BLUE_BOLD  + "Projects" + RESET);
		System.out.println(seperator);
		this.printOptions("All projects will be here...");
	}

	private void TasksMenu()
	{
		System.out.println("Tasks Menu");
		System.out.println("----------");
		System.out.println("1 => Add"
				+ "\n" + "2 => Update"
				+ "\n" + "3 => Delete\n");
	}

	private void EmployeeMenu()
	{
		System.out.println("Employee Menu");
		System.out.println("---------------------------");
		System.out.println("1 => Send a Mission Request"
				+ "\n" + "2 => Send a Vacation Request"
				+ "\n" + "3 => Task log\n");
	}

	private void AdminMenu()
	{
		;
		System.out.println(seperator);
		System.out.println(BLUE_BOLD + "Admin Menu" + RESET);
		System.out.println(seperator);
		this.printOptions("Add Leaders/Employees", "Update Leaders/Employees", "Delete Leaders/Employees");

		String answer = this.readWord("Your Choice:");
		while (!this.rightInput(true, answer, 1, 2, 3, "add", "update", "delete"))
		{
			System.out.println(errMessage);
			answer = this.readWord("Your Choice:");
		}

		switch (answer) {
			case "exit":
				System.exit(0);
			case "back":
				this.MainMenu();
				break;
		}

		String answer2 = this.readWord("User Type (leader/employee):");
		while (!this.rightInput(false, answer2, 1, 2, "leader", "employee"))
		{
			System.out.println(errMessage);
			answer2 = this.readWord("Your Choice:");
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
		String name = this.readWord("User name: ");
		String password = this.readWord("User Password: ");

		try {
			while (!this.admin.add(name, password, type)) {
				System.out.println(RED_BOLD + "An Error occured!");
				System.out.println("Please Re enter the info." + RESET);
				name = this.readWord("User name: ");
				password = this.readWord("User Password: ");
			}

			System.out.println(GREEN_BOLD + "User Added Successfully!" + RESET);
			this.AdminMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void AdminUpdate(String type)
	{
		String id = this.readWord("User ID: ");
		String name = this.readWord("New user name: ");

		try {
			while (!id.matches("^[0-9]+$") || !this.admin.update(Integer.parseInt(id), name, type)) {
				System.out.println(RED_BOLD + "An Error occured!");
				System.out.println("Please Re enter the info." + RESET);
				id = this.readWord("User ID: ");
				name = this.readWord("User name: ");
			}

			System.out.println(GREEN_BOLD + "User Updated Successfully!" + RESET);
			this.AdminMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void AdminDelete(String type)
	{
		String id = this.readWord("User ID: ");

		try {
			while (!id.matches("^[0-9]+$") || !this.admin.delete(Integer.parseInt(id), type)) {
				System.out.println(RED_BOLD + "An Error occured!");
				System.out.println("Please Re enter the info." + RESET);
				id = this.readWord("User ID: ");
			}

			System.out.println(GREEN_BOLD + "User Deleted Successfully!" + RESET);
			this.AdminMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
