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

    /**
     * Cleans a file before making any operations on it.
     * Removes the empty lines from the file.
     *
    * @param tableName - Table name aka File name aka Class name.
     */
    public void clean(String tableName)
    {
        try
        {
            File f = new File("data", Helpers.tableNameLoc(tableName));
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
	 * [TODO:description]
	 *
	 * @param tableName [TODO:description]
	 * @param table [TODO:description]
	 *
	 * @return [TODO:description]
	 */
    public int write(String tableName, String[][] table)
    {
        try {
            String[] header = this.readHeaer(tableName);
            File file = new File("data", Helpers.tableNameLoc(tableName));
            BufferedWriter write = new BufferedWriter(new FileWriter(file));

            write.write(header[0] + '\n');
            write.write(header[1] + '\n');

            for (String[] row : Helpers.removeNullRows(table))
            {
                for (int i = 0; i < row.length; i++)
                {
                    write.write(row[i]);
                    if (i + 1 != row.length)
                        write.write("\t\t\t" + this.seperator);
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
        File file = new File("data", Helpers.tableNameLoc(tableName));

        try (BufferedReader bf = new BufferedReader(new FileReader(file));)
        {
            String[] fileLines = bf.lines().toArray(String[]::new);
            int rowsNo = fileLines.length - 2;
            if (rowsNo <= 0)
                return (new String[0][0]);

            int colsNo = Integer.parseInt(fileLines[0].split("=")[1]);
            String[][] table = new String[rowsNo][colsNo];

            for (int i = 2; i < fileLines.length; i++) {
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
    public String[] readHeaer(String tableName)
    {
        this.clean(tableName);
        File file = new File("data", Helpers.tableNameLoc(tableName));

        try (BufferedReader bf = new BufferedReader(new FileReader(file));)
        {
            String[] fileLines = bf.lines().toArray(String[]::new);
            String[] headerLines = new String[fileLines.length];

            if (fileLines.length <= 0)
                return (new String[0]);

            for (int i = 0; i < fileLines.length; i++)
                headerLines[i] = fileLines[i];
            bf.close();
            return (headerLines);
        }
        catch (IOException e)
        {
            return (new String[0]);
        }
    }


	/**
	 * [TODO:description]
	 *
	 * @param tableName [TODO:description]
	 * @param columns [TODO:description]
	 * @param values [TODO:description]
	 *
	 * @return [TODO:description]
	 *
	 * @throws Exception [TODO:description]
	 */
    public String[][] read(String tableName, int[] columns, String[] values) throws Exception
    {
        return (this.read(tableName, columns, values, false));
    }


	/**
	 * [TODO:description]
	 *
	 * @param tableName [TODO:description]
	 * @param columns [TODO:description]
	 * @param values [TODO:description]
	 * @param invert [TODO:description]
	 *
	 * @return [TODO:description]
	 *
	 * @throws Exception [TODO:description]
	 */
    public String[][] read(String tableName, int[] columns, String[] values, boolean invert) throws Exception
    {
        String[][] table = this.read(tableName);

        if (table.length == 0)
            return (new String[0][0]);
        try 
        {
            if (table[0].length - 1 < Helpers.maxArr(columns))
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

            return (Helpers.removeNullRows(result));
        }
        catch (IOException e)
        {
            return (new String[0][0]);
        }
    }


	/**
	 * [TODO:description]
	 *
	 * @param tableName [TODO:description]
	 * @param columns [TODO:description]
	 * @param values [TODO:description]
	 *
	 * @return [TODO:description]
	 *
	 * @throws Exception [TODO:description]
	 */
    public int delete(String tableName, int[] columns, String[] values) throws Exception
    {
        String[][] table = this.read(tableName, columns, values, true);
        if (table.length == 0)
            return (1);

        return (this.write(tableName, table));
    }


	/**
	 * [TODO:description]
	 *
	 * @param tableName [TODO:description]
	 * @param values [TODO:description]
	 *
	 * @return [TODO:description]
	 *
	 * @throws Exception [TODO:description]
	 */
    public int add(String tableName, String[] values) throws Exception
    {
        File file = new File("data", Helpers.tableNameLoc(tableName));
        try (BufferedWriter write = new BufferedWriter(new FileWriter(file, true))) {
            String[][] table = this.read(tableName);

            if (values.length != table[0].length)
                throw new Exception("Invalid number of values to insert...");

            
            for (int i = 0; i < values.length; i++)
            {
                write.write(values[i]);
                if (i + 1 != values.length)
                    write.write("\t\t\t" + this.seperator);
            }
            write.newLine();
            write.flush();
        }
        return (0);
    }


	/**
	 * [TODO:description]
	 *
	 * @return [TODO:description]
	 */
    @Override
    public String toString()
    {
        return ("TODO");
    }
}
