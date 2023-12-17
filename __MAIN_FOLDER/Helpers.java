import static ConsoleColors.ConsoleColors.*;
import java.util.Scanner;
import java.util.Arrays;


public class Helpers {
	
	private Scanner input = new Scanner(System.in);


	/**
	 * Takes an unknown number and data type of parameters
	 * and convert them to a String Array
	 *
	 * @param params - Infinite number of parameters with an unknown data type of them
	 *
	 * @return A String Array
	 */
    public String[] paramsToArr(Object... params)
	{
		String[] paramsArr = new String[params.length];

		for (int i = 0; i < params.length; i++)
			paramsArr[i] = String.valueOf(params[i]);

		return (paramsArr);
	}


	/**
	 * Takes an unknown number of int parameters
	 * and convert them to an int Array
	 *
	 * @param params - Infinite number of int parameters
	 *
	 * @return An Int Array
	 */
	public int[] intToArr(int... params)
	{
		int[] paramsArr = new int[params.length];

		for (int i = 0; i < params.length; i++)
			paramsArr[i] = params[i];

		return (paramsArr);
	}


	/**
	 * Convert the table name to a file name if it's not already the file name
	 *
	 * @param tableName - Table name aka Class name or File name.
	 *
	 * @return Table's File name
	 */
    public String tableNameLoc(String tableName)
	{
		if (tableName.toLowerCase().endsWith(".txt"))
			return (tableName.toLowerCase());
		return (tableName.toLowerCase() + ".txt");
	}


	/**
	 * Get the max element in an array of integers
	 *
	 * @param array an array of integers
	 *
	 * @return Max Element
	 */
	public int maxArr(int[] array)
	{
		return ( Arrays.stream(array).max().orElse(0));
	}


	/**
	 * No need to make a documentation.
	 *
	 * @param array - The array to check
	 *
	 * @return true | false
	 */
	public boolean isEmptyTable(String[][] array)
	{
		for (String[] row : array)
			for (String str : row)
				if (str != null && !str.isEmpty())
					return false;
		return true;
	}


	/**
	 * Remove the null Rows from a table
	 *
	 * @param table - The table to check.
	 *
	 * @return A new version of the table without the null Rows.
	 */
	public String[][] removeNullRows(String[][] table)
	{
		if (table.length == 0)
			return (new String[0][0]);
		int colNo = table[0].length;
		int countNotNull = 0;

		for (String[] row : table) {
			boolean flagNullRow = true;
			for (String value : row) {
				if (value != null) {
					flagNullRow = false;
					break;
				}
			}
			if (!flagNullRow)
				countNotNull++;
		}
		String[][] resultArray = new String[countNotNull][colNo];
		int resultRowIndex = 0;

		for (String[] row : table) {
			boolean flagNullRow = true;
			for (String cell : row) {
				if (cell != null) {
					flagNullRow = false;
					break;
				}
			}
			if (!flagNullRow)
				resultArray[resultRowIndex++] = row;
		}

		return (resultArray);
	}


	/**
	 * Get the number of spaces that should be printed in the cell after the cell's value.
	 * 
	 * @param cellValue
	 * @param cellWidth
	 * @return A string contains the right number of spaces
	 */
	public String printCell(String cellValue, int cellWidth)
	{
		String spaces = new String();

		for (int i = 0; i < Math.abs(cellWidth - cellValue.length()); i++)
			spaces += " ";

		return (spaces);
	}

	/**
	 * Read a single word from the user's input
	 * And delete the the trailing new line
	 *
	 * @param message - The inputed word
	 *
	 * @return The inputed word
	 */
	public String readWord(String message)
	{
		System.out.print(YELLOW_BOLD + message + " " + GREEN_BOLD);
		String word = input.next();

		System.out.print(RESET);
		input.nextLine();

		return (word.strip());
	}


	/**
	 * Read a line from the user's input
	 *
	 * @param message - The inputed line
	 *
	 * @return - The inputed line
	 */
	public String readLine(String message)
	{
		System.out.print(YELLOW_BOLD + message + " " + GREEN_BOLD);
		String line = input.nextLine();

		System.out.print(RESET);

		return (line.strip());
	}


	/**
	 * Check if the user's choice is right.
	 *
	 * @param Back - weather the user can go a step back or not.Z
	 * @param input - The choice that the user entered
	 * @param options - The option that the user should choice from.
	 *
	 * @return true | false
	 */
	public boolean rightInput(boolean Back, String input, Object... options)
	{
		for (int i = 0; i < options.length; i++)
			if (String.valueOf(options[i]).toLowerCase().equals(input.toLowerCase()))
				return (true);
		return ((Back ? (input.toLowerCase().equals("exit") || input.toLowerCase().equals("back")) : false));
	}


	/**
	 * Print the options that the user should choose from
	 *
	 * @param options - The options
	 */
	public void printOptions(String... options)
	{
		for (int i = 0; i < options.length; i++)
			System.out.println(BLUE_BOLD + (i + 1) + RESET + ". " + options[i]);
		System.out.println();
	}
}
