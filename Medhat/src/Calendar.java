import java.io.BufferedReader;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
*
* @author Mohamed Medhat
*/


public class Calendar  {

	private FileStorage fs ;
    private Helpers hlp ;
    private int Project_id;
    private int Task_log;
    private int Task_id;
    
	

	
	public void ShowallProjects()
	{
		try {
		     String[][] result = fs.read("project");
		      for(String[] Row : result)
		      {
		    	  for(String col : Row)
		    	  {
		    		  System.out.print(col + " ");
		    	  }
		    	  System.out.println();
		      }
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public void ShowTasklog()
	{
		try {
			String[][] result = fs.read("tasklog");
			for(String[] row : result)
			{
				for(String col : row)
				{
					System.out.print(col + " ");
				}
				System.out.println();
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	public void ShowallTasks()
	{
		try {
			String[][] result = fs.read("task");
			for(String[] row : result)
			{
				for(String col : row)
				{
					System.out.print(col + " ");
				}
				System.out.println();
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	public void ShowTask(int Task_id)
	{
		try
		{
			String[][] result = fs.read("task", hlp.intToArr(2), hlp.paramsToArr(this.Task_id));
			for(String[] row : result)
			{
				for(String col : row)
				{
					System.out.print(col + " ");
				}
				    System.out.println();
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
	
	public void ShowUnfinishedTasks()
	{
		try
		{
			String[][] result = fs.read("task", hlp.intToArr(2), hlp.paramsToArr(null));
			for(String[] row : result)
			{
				for(String col : row)
				{
					System.out.print(col + " ");
				}
				    System.out.println();
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public void ShowProject (int Project_id)
	{
		try {
			String[][] result = fs.read("project", hlp.intToArr(2), hlp.paramsToArr(this.Project_id));
			for(String[]row : result )
			{
				for(String col : row )
				{
					System.out.print(col + " ");
				}
				System.out.println();
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
}
