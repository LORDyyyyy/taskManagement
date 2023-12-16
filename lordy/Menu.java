import static ConsoleColors.ConsoleColors.*;
import java.io.Console;

public class Menu {

	protected Helpers hlp = new Helpers();
	protected String errMessage = RED + "Wrong Input!" + RESET;
	protected String seperator = CYAN_BOLD + "-------------------------" + RESET; 
	protected String welcome = """
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
	private AdminMenu adminMenu;
	private LeaderMenu leaderMenu;
	private EmployeeMenu empMenu;


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
		Console cnsl = System.console(); 
		String name = hlp.readWord("Your name: ");
		// String password = hlp.readWord("Your Password: ");
		String password = new String(cnsl.readPassword(YELLOW_BOLD + "Your Password: "));

		try {
			if (type.equals("emp"))
			{
				this.emp = new Employee(name, password);
				while (!emp.login(emp.getName(), emp.getPassword()))
				{
					System.out.println(RED_BOLD + "Account was not found!");
					System.out.println("Please Re-enter your information or exit the program" + RESET);
					emp.setName(hlp.readWord("Your name: "));
					emp.setPassword(String.valueOf(cnsl.readPassword(YELLOW_BOLD + "Your Password: ")));
				}
				empMenu = new EmployeeMenu(emp);
				emp = null;
				empMenu.EmployeesMenu();
			}
			else if (type.equals("leader"))
			{
				this.leader = new Leader(name, password);

				while (!leader.login(leader.getName(), leader.getPassword()))
				{
					System.out.println(RED_BOLD + "Account was not found!");
					System.out.println("Please Re-enter your information or exit the program" + RESET);
					leader.setName(hlp.readWord("Your name: "));
					leader.setPassword(String.valueOf(cnsl.readPassword(YELLOW_BOLD + "Your Password: ")));
				}
				leaderMenu = new LeaderMenu(leader);
				leader = null;
				leaderMenu.LeadersMenu();
			}
			else if (type.equals("admin"))
			{
				this.admin = new Admin(name, password);

				while (!admin.login(admin.getName(), admin.getPassword()))
				{
					System.out.println(RED_BOLD + "Account was not found!");
					System.out.println("Please Re-enter your information or exit the program" + RESET);
					admin.setName(hlp.readWord("Your name: "));
					admin.setPassword(String.valueOf(cnsl.readPassword(YELLOW_BOLD + "Your Password: ")));
				}
				adminMenu = new AdminMenu(admin);
				admin = null;
				adminMenu.AdminsMenu();
			}
		} catch (Exception e) {
			this.MainMenu();
		}
	}
}