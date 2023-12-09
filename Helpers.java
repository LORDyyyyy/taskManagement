import java.util.Arrays;

public class Helpers {
	/**
	 * Takes an unknown number and data type of parameters
	 * and convert them to a String Array
	 *
	 * @param params - Infinite number of parameters with an unknown data type of them
	 *
	 * @return A String Array
	 */
    public static String[] paramsToArr(Object... params)
    {
        String[] paramsArr = new String[params.length];

        for (int i = 0; i < params.length; i++)
            paramsArr[i] = String.valueOf(params[i]);

        return (paramsArr);
    }

	/**
	 * Convert the table name to a file name if it's not already the file name
	 *
	 * @param tableName - Table name aka Class name or File name.
	 *
	 * @return Table's File name
	 */
    public static String tableNameLoc(String tableName)
	{
		if (tableName.endsWith(".txt"))
			return (tableName);
		return (tableName + ".txt");
	}
	

	/**
	 * Get the max element in an array of integers
	 *
	 * @param array an array of integers
	 *
	 * @return Max Element
	 */
	public static int maxArr(int[] array) {
		return (Arrays.stream(array).max().orElseThrow());
	}
	

	/**
	 * No need to make a documentation.
	 *
	 * @param array - The array to check
	 *
	 * @return true | false
	 */
	public static boolean isEmptyTable(String[][] array)
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
	public static String[][] removeNullRows(String[][] table)
	{
        int colNo = table[0].length;
		int countNotNull = 0;
		
		for (String[] row : table)
		{
            boolean flagNullRow = true;
			for (String value : row) 
			{
				if (value != null)
				{
					flagNullRow = false;
					break;
				}
            }
            if (!flagNullRow)
                countNotNull++;
        }
        String[][] resultArray = new String[countNotNull][colNo];
		int resultRowIndex = 0;
		
		for (String[] row : table)
		{
			boolean flagNullRow = true;
			for (String cell : row)
			{
				if (cell != null)
				{
					flagNullRow = false;
					break;
				}
			}
			if (!flagNullRow)
				resultArray[resultRowIndex++] = row;
		}
		
        return (resultArray);
    }
}
