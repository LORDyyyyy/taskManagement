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
				this.Calender();
				break;
			default:
				this.LeadersMenu();
		}
	}

	private void ProjectsMenu() {
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD + "Projects Menu" + RESET);
		System.out.println(this.seperator);
		hlp.printOptions("Show Current Projects", "Add", "Update", "Delete", "Enter Projects Page");

		String answer = hlp.readWord("Your Choice:");

		while (!hlp.rightInput(true, answer, 1, 2, 3, 4, 5, "projects", "Add", "Update", "Delete", "enter")) {
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
				this.AddProject();
				break;
			case "3":
			case "calendar":
				this.UpdateProject();
				break;
			case "4":
			case "delete":
				this.DeleteProject();
			case "5":
			case "enter":
				this.EnterProject();
		}
	}

	private void ShowProjects() {
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD + "Projects" + RESET);
		System.out.println(this.seperator);
		String[][] projects = leader.showProjects();

		if (hlp.isEmptyTable(projects))
			System.out.println(RED_BOLD + BLACK_BACKGROUND + "There are no projects!" + RESET);
		else {
			System.out.print(BLACK_BACKGROUND + WHITE_BOLD);
			System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
			System.out.printf("|%-12s%-18s|%-11s%-19s|%-12s%-18s|%-12s%-18s|%-12s%-18s|\n",
					" ", "ID",
					" ", "Title",
					" ", "Description",
					" ", "Start Date",
					" ", "End Date");
			System.out.printf("+%-30s+%-30s+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30),
					"-".repeat(30), "-".repeat(30));
			for (String[] row : projects) {
				for (int i = 0; i < row.length; i++)
					System.out.printf("|%-30s", row[i]);
				System.out.println("|");
			}
			System.out.printf("+%-30s+%-30s+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30),
					"-".repeat(30), "-".repeat(30));
			System.out.print(RESET);
		}
		this.LeadersMenu();
	}
	
	private void AddProject() {
		String title = hlp.readLine("Enter the project title:");
		String desc = hlp.readLine("Enter the project description :");
		String startDate = hlp.readWord("Enter the Start Date");
		String endDate = hlp.readWord("Enter the End Date");

		while (!leader.addProject(title, desc, startDate, endDate)) {
			System.out.println(errMessage);
			title = hlp.readLine("Re-Enter the project title:");
			desc = hlp.readLine("Re-Enter the project description :");
			startDate = hlp.readWord("Re-Enter the Start Date");
			endDate = hlp.readWord("Re-Enter the End Date");
		}
		System.out.println(GREEN_BOLD + BLACK_BACKGROUND + "Project added successfully!" + RESET);
		this.LeadersMenu();
	}

	private void UpdateProject()
	{
		String id = hlp.readWord("Enter the project id:");
		
		System.out.println("""
				To change the title, press 1.
				To change description , press 2.
				To change the start time, press 3.
				To change the end time, press 4.
				""");
		String col = hlp.readWord("Your choice:");
		String newVal = hlp.readLine("Enter the new value:");

		while (!id.matches("^[0-9]+$") || !col.matches("^[1-4]+$") || !leader.updateProject(Integer.parseInt(id), Integer.parseInt(col), newVal))
		{
			System.out.println(errMessage);
			id = hlp.readWord("Re-Enter the project id:");
			col = hlp.readWord("Re-Enter which information to change:");
			newVal = hlp.readLine("RE-Enter the new value:");
		}
		System.out.println(GREEN_BOLD + BLACK_BACKGROUND + "Project Updated successfully!" + RESET);
		this.LeadersMenu();
	}

	private void DeleteProject()
	{
		String id = hlp.readWord("Enter the project id:");

		while (!id.matches("^[0-9]+$") || !leader.deleteProject(Integer.parseInt(id)))
		{
			System.out.println(errMessage);
			id = hlp.readWord("Re-Enter the project id:");
		}
		System.out.println(GREEN_BOLD + BLACK_BACKGROUND + "Project Deleted successfully!" + RESET);
		this.LeadersMenu();
	}

	private void EnterProject()
	{
		String id = hlp.readWord("Enter the project id:");

		while (!id.matches("[0-9]+")) {
			System.out.println(errMessage);
			id = hlp.readWord("RE-Enter the project id:");
		}
		this.TasksMenu(Integer.parseInt(id));
	}


	private void TasksMenu(int project_id) {
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD + "Tasks Menu" + RESET);
		System.out.println(this.seperator);
		hlp.printOptions("Add Task", "Update Task", "Delete Task", "Task Log", "Show Tasks");

		String answer = hlp.readWord("Your Choice:");

		while (!hlp.rightInput(true, answer, 1, 2, 3, 4, 5, "add", "update", "delete", "log", "show", "tasks"))
		{
			System.out.println(errMessage);
			answer = hlp.readWord("Your Choice:");
		}

		switch (answer.toLowerCase()) {
			case "exit":
				System.exit(0);
				break;
			case "back":
				this.ProjectsMenu();
				break;
			case "1":
			case "add":
				this.AddTask(project_id);
				break;
			case "2":
			case "update":
				this.UpdateTask(project_id);
				break;
			case "3":
			case "delete":
				this.DeleteTask(project_id);
				break;
			case "4":
			case "log":
				this.TaskLog(project_id);
				break;
			case "5":
			case "show":
			case "tasks":
				this.ShowTasks(project_id);
			default:
				this.ProjectsMenu();
		}
	}

	private void ShowTasks(int project_id)
	{
		System.out.println(this.seperator);
		System.out.println(BLUE_BOLD + "Tasks" + RESET);
		System.out.println(this.seperator);
		try {
			String[][] tasks = leader.showTasks(project_id);
			if (hlp.isEmptyTable(tasks))
					System.out.println(YELLOW_BOLD + BLACK_BACKGROUND + "There are no tasks at the moment.");
			else 
			{
				System.out.print(BLACK_BACKGROUND + WHITE_BOLD);
				System.out.printf("+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+\n", "-".repeat(26), "-".repeat(26), "-".repeat(26),
						"-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26));
				System.out.printf("|%-10s%-16s|%-10s%-16s|%-9s%-17s|%-10s%-16s|%-10s%-16s|%-10s%-16s|%-10s%-16s|%-10s%-16s|%-10s%-16s|\n",
						" ", "ID",
						" ", "ProjectID",
						" ", "EmpID",
						" ", "Title",
						" ", "Desc",
						" ", "StartTime",
						" ", "EndTime",
						" ", "Phase",
						" ", "Estimated Hours");
				System.out.printf("+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+\n", "-".repeat(26), "-".repeat(26), "-".repeat(26),
						"-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26));
				for (String[] row : tasks)
				{
					for (int i = 0; i < row.length; i++)
					{
						if (i == 2)
							continue;
						System.out.printf("|%-26s", row[i]);
					}
					System.out.println("|");
				}
				System.out.printf("+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+\n", "-".repeat(26), "-".repeat(26), "-".repeat(26),
						"-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26));
				System.out.println(RESET);
				this.TasksMenu(project_id);
			}
		} catch (Exception e)
		{
			this.TasksMenu(project_id);
		}
	}

	private void AddTask(int project_id)
	{
		String title = hlp.readLine("Enter the task title:");
		String description = hlp.readLine("Enter the task description:");
		String startTime = hlp.readLine("Enter the task start time:");
		String phase = "On Going";
		String endTime = hlp.readLine("Enter the task end time:");
		String estimationHours = hlp.readLine("Enter the task estimation hours:");
		String priority = hlp.readLine("Enter the task priority:");
		String empId = hlp.readWord("Enter the assigned employee id:");

		while (!empId.matches("[0-9]+") || !leader.addTask(title, description, startTime, endTime, phase,
				estimationHours, priority, project_id, Integer.parseInt(empId))) {
			System.out.println(errMessage);
			empId = hlp.readWord("Re-Enter the assigned employee id:");
		}
		System.out.println(GREEN_BOLD + BLACK_BACKGROUND + "Task added successfully!" + RESET);
		this.TasksMenu(project_id);
	}


	private void UpdateTask(int project_id)
	{
		String id = hlp.readWord("Enter the task id:");
		
		System.out.println("""
				To change the Assigned Employee, press 1.
				To change the title, press 2.
				To change description , press 3.
				To change the start time, press 4.
				To change the end time, press 5.
				To change the phase, press 6.
				To change the estimation hours, press 7.
				To change priority, press 8.
				""");
		String col = hlp.readWord("Your choice:");
		String newVal = hlp.readLine("Enter the new value:");

		while (!id.matches("^[0-9]+$") || !col.matches("^[1-7]+$") || !leader.updateTask(Integer.parseInt(id), Integer.parseInt(col) + 2, newVal))
		{
			System.out.println(errMessage);
			id = hlp.readWord("Re-Enter the task id:");
			col = hlp.readWord("Re-Enter which information to change:");
			newVal = hlp.readLine("Re-Enter the new value:");
		}
		System.out.println(GREEN_BOLD + BLACK_BACKGROUND + "Task Updated successfully!" + RESET);
		this.TasksMenu(project_id);
	}

	private void DeleteTask(int project_id)
	{
		String id = hlp.readWord("Enter the task id:");

		while (!id.matches("^[0-9]+$") || !leader.deleteTask(Integer.parseInt(id))) {
			System.out.println(errMessage);
			id = hlp.readWord("Re-Enter the task id:");
		}
		System.out.println(GREEN_BOLD + BLACK_BACKGROUND + "Task Deleted successfully!" + RESET);
		this.TasksMenu(project_id);
	}
	
	public void TaskLog(int project_id)
	{
		String id = hlp.readWord("Enter the task id:");

		while (!id.matches("^[0-9]+$")) {
			System.out.println(errMessage);
			id = hlp.readWord("Re-Enter the task id:");
		}

		String[][] res = leader.taskLog(Integer.parseInt(id));
		if (hlp.isEmptyTable(res)) {
			System.out.println(" Table is empty!");
		} else {
			System.out.printf("+%-30s+%-30s+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30),
					"-".repeat(30), "-".repeat(30));
			System.out.printf("|%-14s%-16s|%-13s%-17s|%-12s%-18s|%-11s%-19s|%-10s%-20s|\n", " ", "ID", " ", "Task_id",
					" ", "Employee_id", " ", "Start_time", " ", "End_time");
			System.out.printf("+%-30s+%-30s+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30),
					"-".repeat(30), "-".repeat(30));
			for (String[] row : res) {
				for (int i = 0; i < row.length - 1; i++) {
					System.out.printf("|%-30s", row[i]);
				}
				System.out.println("|");
			}
			System.out.printf("+%-30s+%-30s+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30),
					"-".repeat(30), "-".repeat(30));
		}

		this.TasksMenu(project_id);
	}
	
	private void Calender()
    {
        String[][] all = leader.Calendar(); 
        if(all.length == 0)
        {
            System.out.println(" Table is empty!");
        }
        else
        {
                System.out.printf("+%-30s+%-30s+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30) , "-".repeat(30) , "-".repeat(30));
                System.out.printf("|%-14s%-16s|%-13s%-17s|%-12s%-18s|%-11s%-19s|%-10s%-20s|\n", " ", "ID", " ", "Date", " " , "Employee_id" , " " , "Start_date" , " ", "Finish_date");
                System.out.printf("+%-30s+%-30s+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30) , "-".repeat(30) , "-".repeat(30));
                for (String[] row: all)
                {
                    for (int i = 0; i < row.length; i++)
                    {
                        System.out.printf("|%-30s", row[i]);
                    }
                    System.out.println("|");
                }
                System.out.printf("+%-30s+%-30s+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30) , "-".repeat(30) , "-".repeat(30));
        } 
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