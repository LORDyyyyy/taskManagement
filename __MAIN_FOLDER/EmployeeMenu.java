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
		System.out.println(BLUE_BOLD  + "Employee Menu" + RESET);
		System.out.println(this.seperator);
        hlp.printOptions("Assigned Tasks", "Send a Mission Request",
                                    "Send a Vacation Request", "See Requests");

		String answer = hlp.readWord("Your Choice:");
        
		while (!hlp.rightInput(true, answer, 1, 2, 3, 4, "tasks", "mission",
                                                                        "vacation", "requests"))
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
        }
	}
}
