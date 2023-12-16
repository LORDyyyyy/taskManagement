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
    }


	/**
	 * The Employee Menu that will be displayed for the Employee.
	 */
	public void EmployeesMenu()
    {
        System.out.println(this.seperator);
        System.out.println(BLUE_BOLD + "Employee Menu" + RESET);
        System.out.println(this.seperator);
        hlp.printOptions("Assigned Tasks", "Send a Request", "See Requests");

        String answer = hlp.readWord("Your Choice:");

        while (!hlp.rightInput(true, answer, 1, 2, 3, "tasks", "send", "requests")) {
            System.out.println(errMessage);
            answer = hlp.readWord("Your Choice:");
        }

        switch (answer.toLowerCase()) {
            case "exit":
                System.exit(0);
                break;
            case "back":
                super.MainMenu();
                return;
            case "1":
            case "tasks":
                // this.EemployeeTasks();
                break;
            case "2":
            case "send":
                this.EmployeeSendReq();
                break;
            case "3":
            case "requests":
                this.EmployeeSeeReq();
                break;
            default:
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
            else
            {
                System.out.print(BLACK_BACKGROUND + WHITE_BOLD);
                System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
                System.out.printf("|%-12s%-18s|%-11s%-19s|%-12s%-18s|\n", " ", "Message", " ", "Send_Date", " ", "Status");
                System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
                for (String[] row : messages)
                {
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
}
