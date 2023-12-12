import java.util.Scanner;
import static ConsoleColors.ConsoleColors.*;

public class Menu {

	private Scanner input = new Scanner(System.in);
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
		System.out.print(YELLOW_BOLD + message + " ");
		String word = input.next();

		System.out.print(RESET);
		input.nextLine();

		return (word);
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
	private boolean rightInput(String input, Object... options)
	{
		for (int i = 0; i < options.length; i++)
			if (String.valueOf(options[i]).toLowerCase().equals(input.toLowerCase()))
				return (true);

		return (input.toLowerCase().equals("exit")  || input.toLowerCase().equals("back"));
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

		while (!this.rightInput(answer, 1, 2, 3, "admin", "leader", "employee"))
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
				this.AdminMenu();
				break;
			case "2":
			case "leader":
				this.LeadersMenu();
				break;
			case "3":
			case "employee":
				this.EmployeeMenu();
		}
	}


	/**
	 * The Leader Menu that will be displayed for the leader.
	 */
	public void LeadersMenu()
	{
		System.out.println(seperator);
		System.out.println(BLUE_BOLD  + "Leader Menu" + RESET);
		System.out.println(seperator);
		this.printOptions("Projects", "Requests", "Calendar");

		String answer = this.readWord("Your Choice:");

		while (!this.rightInput(answer, 1, 2, 3, "projects", "requests", "calendar"))
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


	public void ProjectsMenu()
	{
		System.out.println(seperator);
		System.out.println(BLUE_BOLD  + "Projects Menu" + RESET);
		System.out.println(seperator);
		this.printOptions("Show Current Projects", "Add", "Update", "Delete");

		String answer = this.readWord("Your Choice:");

		while (!this.rightInput(answer, 1,2,3,4, "projects", "Add", "Update", "Delete"))
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


	public void ShowProjects()
	{
		System.out.println(seperator);
		System.out.println(BLUE_BOLD  + "Projects" + RESET);
		System.out.println(seperator);
		this.printOptions("All projects will be here...");
	}

	public void TasksMenu()
	{
		System.out.println("Tasks Menu");
		System.out.println("----------");
		System.out.println("1 => Add"
				+ "\n" + "2 => Update"
				+ "\n" + "3 => Delete\n");
	}

	public void EmployeeMenu()
	{
		System.out.println("Employee Menu");
		System.out.println("---------------------------");
		System.out.println("1 => Send a Mission Request"
				+ "\n" + "2 => Send a Vacation Request"
				+ "\n" + "3 => Task log\n");
	}

	public void AdminMenu()
	{;
		System.out.println(seperator);
		System.out.println(BLUE_BOLD  + "Admin Menu" + RESET);
		System.out.println(seperator);
		this.printOptions("Add Leaders/Employees", "Update Leaders/Employees", "Delete Leaders/Employees");

		String answer = this.readWord("Your Choice:");
		while (!this.rightInput(answer, 1, 2, 3, "add", "update", "delete"))
		{
			System.out.println(errMessage);
			answer = this.readWord("Your Choice:");
		}

		String answer2 = this.readWord("User Type (leader/employee):");
		while (!this.rightInput(answer2, 1, 2, "leader", "employee"))
		{
			System.out.println(errMessage);
			answer2 = this.readWord("Your Choice:");
		}

		switch (answer2) {
			case "exit":
				System.exit(0);
				break;
			case "back":
				this.LeadersMenu();
				break;
			default:
				System.out.println("TODO!");
				break;
		}
	}
}
