import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author: LORDy
 */
public class FileStorage {

    private String seperator = "|";
    private Helpers hlp = new Helpers();
    public int cellWidth = 32;

    /**
     * The constructor method for the FileStorage Class
     * 
     * @param cellWidth - The width of the table's cell
     */
    public FileStorage (int cellWidth)
    {
        this.cellWidth = cellWidth;
    }

    /**
     * The constructor method for the FileStorage Class
     * 
     */
    public FileStorage()
    {
        // this.cellWidth = 32;
    };


    /**
     * Creates a new table/file with the passed paramters as the columns' names
     *
     * @param tableName Table name aka File name aka Class name.
     * @param columns the columns to add to the table
     *
     * @return true on success, false otherwise
     *
     * @throws Exception An error with file/values
     */
    public boolean createTable(String tableName, String... columns) throws Exception
    {
        File directory = new File("data");

        if (!directory.exists())
            directory.mkdirs();

        File f = new File("data", hlp.tableNameLoc(tableName));

        try 
        {
            if (f.exists())
                f.delete();
            f.createNewFile();
            try (BufferedWriter write = new BufferedWriter(new FileWriter(f)))
            {
                for (int i = 0; i < columns.length; i++)
                {
                    write.write(columns[i]);
                    if (i + 1 != columns.length)
                        write.write(hlp.printCell(columns[i], this.cellWidth) + this.seperator);
                }
                write.write("=" + columns.length);
                write.newLine();
                
                for (int i = 0; i < columns.length; i++)
                {
                    for (int j = 0; j < this.cellWidth; j++)
                        write.write("-");
                    if (i + 1 != columns.length)
                        write.write(this.seperator);
                }
                write.flush();
            }
            return (true);
        } 
        catch (IOException e) {
            return false;
        }
    }


    /**
     * Cleans a file before making any operations on it.
     * Removes the empty lines from the file.
     *
     * @param tableName - Table name aka File name aka Class name.
     */
    private void clean(String tableName)
    {
        try
        {
            File f = new File("data", hlp.tableNameLoc(tableName));
            BufferedReader read = new BufferedReader(new FileReader(f));
            String[] fileLines = read.lines().toArray(String[]::new);

            read.close();

            BufferedWriter write = new BufferedWriter(new FileWriter(f));

            for (String line : fileLines)
            {
                if (line.equals(""))
                    continue;
                else
                {
                    write.write(line);
                    write.newLine();
                    write.flush();
                }
            }
            write.close();
        }
        catch (IOException e)
        {
            return;
        }
    }
    

	/**
	 * Overwrites a file with a new table
	 *
	 * @param tableName - Table name aka File name aka Class name.
	 * @param table - The new table to overwrite
	 *
	 * @return 0 on success, 1 otherwise
	 */
    private int write(String tableName, String[][] table)
    {
        try {
            String[] header = this.readHeader(tableName);

            if (header.length == 0)
                return (1);

            File file = new File("data", hlp.tableNameLoc(tableName));
            BufferedWriter write = new BufferedWriter(new FileWriter(file));
            
            write.write(header[0] + '\n');
            write.write(header[1] + '\n');

            for (String[] row : hlp.removeNullRows(table))
            {
                for (int i = 0; i < row.length; i++)
                {
                    write.write(row[i]);
                    if (i + 1 != row.length)
                        write.write(hlp.printCell(row[i], this.cellWidth) + this.seperator);
                }
                write.newLine();
                write.flush();
            }
            write.close();
            return (0);
        } catch (IOException e) {
            return (1);
        }
    }

    /**
     * Reload a table and adjust the cells width.
     * 
     * @param tableName - Table name aka File name aka Class name.
     */
    public void reload(String tableName)
    {
        this.write(tableName, this.read(tableName));
    }

    /**
     * Reads all rows from a file
     * and converts the table's rows and cells into a 2D String Array.
     *
     * @param tableName - Table name aka File name aka Class name.
     *
     * @return 2D String array contains the full table on success, empty 2D array otherwise.
     */
    public String[][] read(String tableName)
    {
        this.clean(tableName);
        File file = new File("data", hlp.tableNameLoc(tableName));

        try (BufferedReader bf = new BufferedReader(new FileReader(file));)
        {
            String[] fileLines = bf.lines().toArray(String[]::new);
            int rowsNo = fileLines.length - 2;

            if (rowsNo <= 0)
                return (new String[0][0]);

            int colsNo = Integer.parseInt(fileLines[0].split("=")[1].strip());
            String[][] table = new String[rowsNo][colsNo];

            for (int i = 2; i < fileLines.length; i++)
            {
                String[] words = fileLines[i].split("\\" + this.seperator);

                for (int j = 0; j < colsNo; j++)
                    table[i - 2][j] = words[j].strip();
            }

            bf.close();
            return (table);
        } catch (IOException e) {
            return (new String[0][0]);
        }
    }


    /**
	 * Read the 2 header lines of the table.
	 *
	 * @param tableName - Table name aka File name aka Class name.
	 *
	 * @return The 2 header lines of the table.
	 */
    private String[] readHeader(String tableName)
    {
        this.clean(tableName);
        File file = new File("data", hlp.tableNameLoc(tableName));

        try (BufferedReader bf = new BufferedReader(new FileReader(file));)
        {
            String[] headerLines = new String[2];
            int lineCount = 0;

            while (lineCount < 2)
            {
                String line = bf.readLine();

                if (line == null)
                    return (new String[0]);
                headerLines[lineCount] = line;
                lineCount++;
            }

            String fixedHeaderRow = new String();
            String[] columns = headerLines[0].split("\\" + this.seperator);

            for (int i = 0; i < columns.length; i++)
            {
                String cleanColName = columns[i].strip();
                fixedHeaderRow += cleanColName;
                if (i + 1 != columns.length)
                    fixedHeaderRow += hlp.printCell(cleanColName, this.cellWidth) + this.seperator;
            }
            headerLines[0] = fixedHeaderRow;

            fixedHeaderRow = "";
            for (int i = 0; i < columns.length; i++)
            {
                for (int j = 0; j < this.cellWidth; j++)
                    fixedHeaderRow += "-";
                if (i + 1 != columns.length)
                    fixedHeaderRow += this.seperator;
            }
            headerLines[1] = fixedHeaderRow;
            bf.close();
            return (headerLines);
        }
        catch (IOException e)
        {
            return (new String[0]);
        }
    }


	/**
	 * Read a row from the table with a specific condition
	 *
	 * @param tableName - Table name aka File name aka Class name.
	 * @param columns - The columns which will be checked by a condition
	 * @param values - The condition value]
	 *
	 * @return 2D String array contains the resutls on success, empty 2D array otherwise.
	 *
	 * @throws Exception An error with file/values
	 */
    public String[][] read(String tableName, int[] columns, String[] values) throws Exception
    {
        return (this.read(tableName, columns, values, false));
    }


    /**
	 * Read a row from the table with a specific condition
	 *
	 * @param tableName - Table name aka File name aka Class name.
	 * @param columns - The columns index which will be checked by a condition
	 * @param values - The condition value
	 * @param invert - Add the NOT operator to the condtion, default false
	 *
     * @return 2D String array contains the resutls on success, empty 2D array otherwise.
	 *
     * @throws Exception An error with file/values
	 */
    public String[][] read(String tableName, int[] columns, String[] values, boolean invert) throws Exception
    {
        String[][] table = this.read(tableName);

        if (table.length == 0)
            return (new String[0][0]);
        try 
        {
            if (this.getTableColsNo(tableName) - 1 < hlp.maxArr(columns))
                throw new Exception("Columns out of index....");
            if (columns.length != values.length)
                throw new Exception("""
                                        Number of Columns to compare
                                        is not equal to Number of values!
                                    """);

            String[][] result = new String[table.length][table[0].length];
            int counterResult = 0;

            for (String[] row : table)
            {
                boolean flag = false;
                for (int i = 0; i < columns.length; i++)
                {
                    if (row[columns[i]].equals(values[i]))
                        flag = true;
                    else
                    {
                        flag = false;
                        break;
                    }
                }
                if (invert ? !flag : flag)
                    result[counterResult++] = row;
            }

            return (hlp.removeNullRows(result));
        }
        catch (IOException e)
        {
            return (new String[0][0]);
        }
    }


	/**
	 * Delete a line/row from the table 
	 *
	 * @param tableName - Table name aka File name aka Class name.
	 * @param columns - The columns index which will be checked by a condition
	 * @param values - The condition value
	 *
	 * @return 0 on success, 1 otherwise
	 *
	 * @throws Exception an error with file/values
	 */
    public int delete(String tableName, int[] columns, String[] values) throws Exception
    {
        String[][] table = this.read(tableName, columns, values, true);

        return (this.write(tableName, table));
    }


	/**
	 * Append a new line/row to the table
	 *
	 * @param tableName - Table name aka File name aka Class name
	 * @param values - the row to be added in an array os strings
	 *
	 * @return 0 on success, 1 otherwise
	 *
	 * @throws Exception - an error with file/values
	 */
    public int add(String tableName, String[] values) throws Exception
    {
        File file = new File("data", hlp.tableNameLoc(tableName));

        try (BufferedWriter write = new BufferedWriter(new FileWriter(file, true))) {
            if (values.length != this.getTableColsNo(tableName))
                throw new Exception("Invalid number of values to insert...");

            for (int i = 0; i < values.length; i++) {
                write.write(values[i]);
                if (i + 1 != values.length)
                    write.write(hlp.printCell(values[i], this.cellWidth) + this.seperator);
            }
            write.newLine();
            write.flush();
        }
        return (0);
    }


    /**
	 * Update a row's value in the table
	 *
	 * @param tableName - Table name aka File name aka Class name.
	 * @param columns - The columns index which will be checked by a condition
	 * @param values - The condition value
	 * @param newCols - The new columns index which will be updated
	 * @param newValues - The new values
	 *
	 * @return 0 on success, 1 otherwise
	 *
	 * @throws Exception an error with file/values
	 */
    public int update(String tableName, int[] columns, String[] values, int[] newCols, String[] newValues) throws Exception
    {
        return (this.update(tableName, columns, values, newCols, newValues, false, Integer.MAX_VALUE));
    }
	/**
     * Update a row's value in the table
     *
     * @param tableName - Table name aka File name aka Class name.
     * @param columns - The columns index which will be checked by a condition
     * @param values - The condition value
     * @param newCols - The new columns index which will be updated
     * @param newValues - The new values
     * @param backwards - true | false, weather to loop from the start or the end. default is false
     * @param count - How many values do you want to update? default is INT_MAX
     *
     * @return 0 on success, 1 otherwise
     *
     * @throws Exception an error with file/values
     */
    public int update(String tableName, int[] columns, String[] values, int[] newCols, String[] newValues, boolean backwards, int count) throws Exception
    {
        String[][] table = this.read(tableName);
        int colsNo = this.getTableColsNo(tableName);

        if (table.length == 0)
            return (0);

        if (values.length > colsNo || newValues.length > colsNo || columns.length > colsNo || newCols.length > colsNo)
            throw new Exception("Invalid number of values to insert...");
        if (colsNo - 1 < hlp.maxArr(columns) || colsNo - 1 < hlp.maxArr(newCols))
            throw new Exception("Columns out of index....");
        if (columns.length != values.length || newCols.length != newValues.length )
                throw new Exception("""
                                        Number of Columns to compare
                                        is not equal to Number of values!
                        """);

        int start = backwards ? table.length - 1 : 0;
        int end = backwards ? 0 : table.length;
        int iteratorNewValue = backwards ? -1 : 1;

        for (int row = start; backwards ? row >= end : row < end; row += iteratorNewValue)
        {
            boolean flag = false;
            for (int i = 0; i < columns.length; i++) {
                if (table[row][columns[i]].equals(values[i]))
                    flag = true;
                else
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
            {
                for (int i = 0; i < newCols.length; i++)
                    table[row][newCols[i]] = newValues[i];
                if (--count == 0)
                    break;
            }
        }
        
        return (this.write(tableName, table));
    }


	/**
     * Get the next id to be inserted in a table.
     * SQL AUTO_INCREMENT Like.
     *
     * @param tableName - Table name aka File name aka Class name.
     *
     * @return the new id
     */
    public int getNextID(String tableName)
    {
        String[][] table = this.read(tableName);

        if (table.length == 0)
            return (1);
        return (Integer.parseInt(table[table.length - 1][0]) + 1);

    }


	/**
	 * Get the number of a table's columns
	 *
	 * @param tableName - Table name aka File name aka Class name.
	 *
	 * @return Number of columns
	 */
    private int getTableColsNo(String tableName)
    {
        String[] header = this.readHeader(tableName);

        return (header[0].split("\\" + this.seperator).length);
    }


	/**
	 * The string representation of the FileStorage Class
	 *
	 * @return String
	 */
    @Override
    public String toString()
    {
        return ("TODO");
    }
}
