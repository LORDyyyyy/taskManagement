import static ConsoleColors.ConsoleColors.*;


class AdminMenu extends Menu{

    private Admin admin;


    /**
     * AdminMenu class constructor
     * 
     * @param admin - An admin instance that holds the logged in admin
     */
    public AdminMenu(Admin admin)
    {
        this.admin = admin;
    }


    /**
     * 
     * The Admins' Menu that will be displayed for the Admin.
     */
	public void AdminsMenu()
	{
		System.out.println(super.seperator);
		System.out.println(BLUE_BOLD + "Admin Menu" + RESET);
		System.out.println(super.seperator);
        hlp.printOptions("Add Leaders/Employees", "Update Leaders/Employees", "Delete Leaders/Employees", "Show Leaders/Employees");
        // System.out.println(admin.getId());
		String answer = hlp.readWord("Your Choice:");
        
		while (!hlp.rightInput(true, answer, 1, 2, 3, 4, "add", "update", "delete", "show"))
		{
			System.out.println(errMessage);
			answer = hlp.readWord("Your Choice:");
		}

		switch (answer) {
			case "exit":
				System.exit(0);
			case "back":
                super.MainMenu();
                return;
		}

		String answer2 = hlp.readWord("User Type (leader/employee):").toLowerCase();
		while (!hlp.rightInput(false, answer2, 1, 2, "leader", "employee"))
		{
			System.out.println(errMessage);
			answer2 = hlp.readWord("Your Choice:").toLowerCase();
		}
		String type = (answer2.equals("1") || answer2.equals("leader")) ? "leader" : "emp";

		switch (answer.toLowerCase()) {
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
				break;
			case "4":
			case "show":
				this.AdminShow(type);
				break;
            default:
                this.AdminsMenu();
		}
	}


	private void AdminAdd(String type)
	{
		String name = hlp.readWord("User name: ");
		String password = hlp.readWord("User Password: ");

		try {
			while (!this.admin.add(name, password, type))
			{
				System.out.println(RED_BOLD + "An Error occured!");
				System.out.println("Please Re enter the info." + RESET);
				name = hlp.readWord("User name: ");
				password = hlp.readWord("User Password: ");
				if (name.equals("back") || password.equals("back"))
					this.AdminsMenu();
			}

			System.out.println(GREEN_BOLD + "User Added Successfully!" + RESET);
			this.AdminsMenu();
		} catch (Exception e) {
			this.AdminsMenu();
		}
	}


	private void AdminUpdate(String type)
	{
		String id = hlp.readWord("User ID: ");
		String name = hlp.readWord("New user name: ");

		try {
			while (!id.matches("^[0-9]+$") || !this.admin.update(Integer.parseInt(id), name, type))
			{
				System.out.println(RED_BOLD + "An Error occured!");
				System.out.println("Please Re enter the info." + RESET);
				id = hlp.readWord("User ID: ");
				name = hlp.readWord("User name: ");

				if (name.equals("back"))
					this.AdminsMenu();
			}

			System.out.println(GREEN_BOLD + "User Updated Successfully!" + RESET);
			this.AdminsMenu();
		} catch (Exception e) {
			this.AdminsMenu();
		}
	}

	private void AdminDelete(String type)
	{
		String id = hlp.readWord("User ID: ");

		try {
			while (!id.matches("^[0-9]+$") || !this.admin.delete(Integer.parseInt(id), type)) {
				System.out.println(RED_BOLD + "An Error occured!");
				System.out.println("Please Re enter the info." + RESET);
				id = hlp.readWord("User ID: ");
				if (id.equals("back"))
					this.AdminsMenu();
			}

			System.out.println(GREEN_BOLD + "User Deleted Successfully!" + RESET);
			this.AdminsMenu();
		} catch (Exception e) {
			this.AdminsMenu();
		}
	}
	
	private void AdminShow(String type)
	{
		try {
			System.out.print(BLACK_BACKGROUND + WHITE_BOLD);
			System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
			System.out.printf("|%-14s%-16s|%-13s%-17s|%-12s%-18s|\n", " ", "ID", " ", "Name", " " , "Password");
			System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
			for (String[] row: admin.read(type))
			{
				for (int i = 0; i < row.length; i++) {
					System.out.printf("|%-30s", row[i]);
				}
				System.out.println("|");
			}
			System.out.printf("+%-30s+%-30s+%-30s+\n", "-".repeat(30), "-".repeat(30), "-".repeat(30));
			System.out.print(RESET);
			this.AdminsMenu();
			
		} catch (Exception e) {
			this.AdminsMenu();
		}
	}
}