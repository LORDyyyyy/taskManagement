import java.util.Scanner;

public class Menu {

	private Scanner input = new Scanner(System.in);
	private String errMessage = "Wrong Input!";
	private String seperator = "------------";


	private String readWord(String message)
	{
		System.out.print(message + " ");

		String word = input.next();

		input.nextLine();

		return (word);
	}


	/**
	 * dasdas
	 *
	 * @param message dsadsa
	 *
	 * @return dsaldkjaslj
	 */
	private String readLine(String message)
	{
		System.out.print(message + " ");

		String line = input.nextLine();

		return (line);
	}


	private boolean rightInput(String input, Object... options)
	{
		for (int i = 0; i < options.length; i++)
			if (String.valueOf(options[i]).toLowerCase().equals(input.toLowerCase()))
				return (true);

		return (input.toLowerCase().equals("exit")  || input.toLowerCase().equals("back"));
	}


	private void printOptions(String... options)
	{
		for (int i = 0; i < options.length; i++)
			System.out.println((i + 1) + ". " + options[i]);
		System.out.println();
	}

	public void MainMenu()
	{
		System.out.println("Welcome!");
		System.out.println(seperator);
		System.out.println("At any page, enter exit to quit the program, or enter back to go a step back.");
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
				// this.AdminMenu()
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


	public void LeadersMenu()
	{
		System.out.println(seperator);
		System.out.println("Leader Menu");
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
		System.out.println("Projects Menu");
		System.out.println("-------------");
		System.out.println("1 => Add"
				+ "\n" + "2 => Update"
				+ "\n" + "3 => Delete\n");
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
	{
		System.out.println("Admin Menu");
		System.out.println("----------------------------------");
		System.out.println("1 => Add"
				+ "\n" + "2 => Create"
				+ "\n" + "3 => Delete Leaders and Employees\n");
	}

}
