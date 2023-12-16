import static ConsoleColors.ConsoleColors.*;


class LeaderMenu extends Menu {

    private Leader leader;


    /**
     * LeaderMenu class constructor
     * 
     * @param leader - An leader instance that holds the logged in leader
     */
    public LeaderMenu(Leader leader)
    {
        this.leader = leader;
    }


    /**
	 * The Leader Menu that will be displayed for the leader.
	 */
	public void LeadersMenu()
	{
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD  + "Leader Menu" + RESET);
		System.out.println(this.seperator);
		hlp.printOptions("Projects", "Requests", "Calendar");
        // System.out.println(leader.getId());
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
                super.MainMenu();
                return;
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
}
