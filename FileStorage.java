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

	/**
     * Cleans a file before making any operations on a file.
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

            for (String line: fileLines)
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
        } catch (IOException e) {
            return;
        }
    }
    
    /**
     * reads all rows from a file
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

            for (int i = 2; i < fileLines.length; i++)
            {
                String[] words = fileLines[i].split("\\|");

                for (int j = 0; j < colsNo; j++)
                {
                    String word = words[j].strip();
                    table[i - 2][j] = word;
                }
            }
            
            bf.close();
            return (table);
        }
        catch (IOException e)
        {
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
        this.clean(tableName);
        String[][] table = this.read(tableName);

        if (table.length == 0)
            return (new String[0][0]);
        try 
        {
            if (table[0].length - 1 < Helpers.maxArr(columns))
                throw new Exception("Columns out of index....");
            if (columns.length != values.length)
                throw new Exception("""
                                        Columns to compare length
                                        is not equal to Columns values length
                                    """);

            String[][] result = new String[table.length][table[0].length];
            int counterResult = 0;

            for (String[] row : table) {
                boolean flag = false;
                for (int i = 0; i < columns.length; i++) {
                    if (row[columns[i]].equals(values[i]))
                        flag = true;
                    else {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    result[counterResult++] = row;
            }

            return (Helpers.removeNullRows(result));
        }
        catch (IOException e)
        {
            return (new String[0][0]);
        }
    }
    

    @Override
    public String toString()
    {
        return ("TODO");
    }
}
