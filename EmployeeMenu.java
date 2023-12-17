import static ConsoleColors.ConsoleColors.*;


class EmployeeMenu extends Menu {

    private Employee emp;


    /**
     * EmployeeMenu class constructor
     * 
     * @param emp - An employee instance that holds the logged in employee
     */
    public EmployeeMenu(Employee emp)
    {
        this.emp = emp;
        try {
            emp.markAttendance();
        } catch (Exception e) {
        }
    }


	/**
	 * The Employee Menu that will be displayed for the Employee.
	 */
	public void EmployeesMenu()
    {
        System.out.println(this.seperator);
        System.out.println(BLUE_BOLD + "Employee Menu" + RESET);
        System.out.println(this.seperator);
        hlp.printOptions("Assigned Tasks", "Send a Request", "See Requests", "Start a Task", "End a Task");

        String answer = hlp.readWord("Your Choice:");

        while (!hlp.rightInput(true, answer, 1, 2, 3, 4, 5,"tasks", "send", "requests")) {
            System.out.println(errMessage);
            answer = hlp.readWord("Your Choice:");
        }

        switch (answer.toLowerCase()) {
            case "exit":
                emp.markDeparture();
                System.exit(0);
                break;
            case "back":
                emp.markDeparture();
                super.MainMenu();
                return;
            case "1":
            case "tasks":
                this.EemployeeTasks();
                break;
            case "2":
            case "send":
                this.EmployeeSendReq();
                break;
            case "3":
            case "requests":
                this.EmployeeSeeReq();
                break;
            case "4":
                this.StartTask();
            case "5":
                this.FinishTask();
            default:
                this.EmployeesMenu();
        }
    }
    
    private void EemployeeTasks() {
        String[][] tasks = emp.viewTasks();
        int[] columns = new int[] { 0, 1, 4, 5, 6, 7, 8 };

        try {
			    System.out.print(BLACK_BACKGROUND + WHITE_BOLD);
				System.out.printf("+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+\n", "-".repeat(26), "-".repeat(26), "-".repeat(26),
						"-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26));
				System.out.printf("|%-10s%-16s|%-10s%-16s|%-9s%-17s|%-10s%-16s|%-10s%-16s|%-10s%-16s|%-10s%-16s|\n",
						" ", "ID",
						" ", "ProjectID",
						" ", "Title",
						" ", "Desc",
						" ", "StartTime",
						" ", "EndTime",
						" ", "Phase");
				System.out.printf("+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+\n", "-".repeat(26), "-".repeat(26), "-".repeat(26),
						"-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26));
			for (String[] row: tasks)
			{
                for (int i = 0; i < columns.length; i++)
                {
					System.out.printf("|%-26s", row[columns[i]]);
				}
				System.out.println("|");
			}
			System.out.printf("+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+%-26s+\n", "-".repeat(26), "-".repeat(26), "-".repeat(26),
						"-".repeat(26), "-".repeat(26), "-".repeat(26), "-".repeat(26));
			System.out.println(RESET);
			this.EmployeesMenu();
			
        } catch (Exception e) {
            System.out.println(e.getMessage());
			this.EmployeesMenu();
		}
    }


    private void EmployeeSendReq()
    {
        try {
            String message = hlp.readLine("Enter the message you want to attach to the request:");

            while (message.contains("|")) {
                System.out.println(errMessage);
                message = hlp.readLine("RE-enter the message you want to attach to the request:");
            }
            if (message.equals("back"))
                this.EmployeesMenu();

            emp.sendRequest(message);
            System.out.println(GREEN_BOLD + BLACK_BACKGROUND + "Request sent successfully!" + RESET);
            this.EmployeesMenu();
        } catch (Exception e) {
            this.EmployeesMenu();
        }
    }


    private void EmployeeSeeReq()
    {
        try {
            String[][] messages = emp.seeRespond();
            int[] columns = new int[] { 2, 3, 4 };

            if (hlp.isEmptyTable(messages))
                System.out.println(RED_BOLD + BLACK_BACKGROUND + "You didn't send any requests before!" + RESET);
            else {
                System.out.print(BLACK_BACKGROUND + WHITE_BOLD);
                System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
                System.out.printf("|%-12s%-18s|%-11s%-19s|%-12s%-18s|\n", " ", "Message", " ", "Send_Date", " ",
                        "Status");
                System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
                for (String[] row : messages) {
                    for (int i = 0; i < 3; i++)
                        System.out.printf("|%-30s", row[columns[i]]);
                    System.out.println("|");
                }
                System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
                System.out.print(RESET);
            }
            this.EmployeesMenu();

        } catch (Exception e) {
            this.EmployeesMenu();
        }
    }
    
    private void StartTask()
    {
        String id = hlp.readWord("Enter the task id:");

        while (!id.matches("^[0-9]+$") || !emp.addStartTime(Integer.parseInt(id))) {
            System.out.println(errMessage);
            id = hlp.readWord("Re-Enter the task id:");
        }
        System.out.println(GREEN_BOLD + BLACK_BACKGROUND + "Task Started!" + RESET);
        this.EmployeesMenu();
    }
    
    private void FinishTask()
    {
        String id = hlp.readWord("Enter the task id:");

        while (!id.matches("^[0-9]+$") || !emp.addFinishTime(Integer.parseInt(id)))
        {
			System.out.println(errMessage);
			id = hlp.readWord("Re-Enter the task id:");
		}
		System.out.println(GREEN_BOLD + BLACK_BACKGROUND + "Task Finished!" + RESET);
		this.EmployeesMenu();
    }
}
