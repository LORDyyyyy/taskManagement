import static ConsoleColors.ConsoleColors.*;


class LeaderMenu extends Menu {

	private Leader leader;

	/**
	 * LeaderMenu class constructor
	 * 
	 * @param leader - An leader instance that holds the logged in leader
	 */
	public LeaderMenu(Leader leader) {
		this.leader = leader;
	}

	/**
	 * The Leader Menu that will be displayed for the leader.
	 */
	public void LeadersMenu() {
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD + "Leader Menu" + RESET);
		System.out.println(this.seperator);
		hlp.printOptions("Projects", "See Requests", "Respond", "Calendar");
		// System.out.println(leader.getId());
		String answer = hlp.readWord("Your Choice:");

		while (!hlp.rightInput(true, answer, 1, 2, 3, 4, "projects", "see", "respond", "calendar")) {
			System.out.println(errMessage);
			answer = hlp.readWord("Your Choice:");
		}

		switch (answer) {
			case "exit":
				System.exit(0);
				break;
			case "back":
				super.MainMenu();
				return;
			case "1":
			case "projects":
				this.ProjectsMenu();
				break;
			case "2":
			case "requests":
				this.RequestsMenu();
				break;
			case "3":
			case "respond":
				this.RespondReq();
				break;
			case "4":
			case "calendar":
				// this.Calender
				break;
			default:
				this.LeadersMenu();
		}
	}

	private void ProjectsMenu() {
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD + "Projects Menu" + RESET);
		System.out.println(this.seperator);
		hlp.printOptions("Show Current Projects", "Add", "Update", "Delete");

		String answer = hlp.readWord("Your Choice:");

		while (!hlp.rightInput(true, answer, 1, 2, 3, 4, "projects", "Add", "Update", "Delete")) {
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

	private void ShowProjects() {
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD + "Projects" + RESET);
		System.out.println(this.seperator);
		hlp.printOptions("All projects will be here...");
	}

	private void TasksMenu() {
		System.out.println("Tasks Menu");
		System.out.println("----------");
		System.out.println("1 => Add"
				+ "\n" + "2 => Update"
				+ "\n" + "3 => Delete\n");
	}

	private void RequestsMenu() {
		try {
			String[][] messages = leader.allRequest();
			int[] columns = new int[] { 0, 1, 2, 3 };

			if (hlp.isEmptyTable(messages))
				System.out.println(YELLOW_BOLD + BLACK_BACKGROUND + "There are no new requests at the moment.");
			else {
				System.out.print(BLACK_BACKGROUND + WHITE_BOLD);
				System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
				System.out.printf("|%-12s%-18s|%-12s%-18s|%-11s%-19s|%-12s%-18s|\n",
						" ", "Request ID",
						" ", "Employee ID",
						" ", "Messsge",
						" ", "Send Data");
				System.out.printf("+%-30s+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30),
						"-".repeat(30));
				for (String[] row : messages) {
					for (int i = 0; i < 4; i++)
						System.out.printf("|%-30s", row[columns[i]]);
					System.out.println("|");
				}
				System.out.printf("+%-30s+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30),
						"-".repeat(30));
				System.out.print(RESET);
			}
			this.LeadersMenu();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			this.LeadersMenu();
		}
	}

	private void RespondReq() {
		try {
			String id = hlp.readWord("Enter the Request ID:");
			String status = hlp.readWord("The request is approved/refused?").toLowerCase();

			while (!id.matches("^[0-9]+$") ||!this.leader.respondRequest(id, status))
			{
				if (status.equals("back"))
					this.LeadersMenu();
				System.out.println(errMessage);
				id = hlp.readWord("Re-Enter the Request ID:");
				status = hlp.readWord("The request is approved/refused?").toLowerCase();
			}
			System.out.println(GREEN_BOLD + BLACK_BACKGROUND + "Request responded successfully!" + RESET);
			this.LeadersMenu();

		} catch (Exception e) {
			this.LeadersMenu();
		}
	}
}
