import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Leader extends Person {
    private Project project;

    public Leader(String name, String password) {
        super(name, password, "leader");
        this.project = new Project();
    }

    /**
     * leader see all request
     * @return all request in 2D array on success,or massage in index[0][0]
     */
    public String[][] allRequest() {
        String[][] all = fs.read("request");
        if (hlp.isEmptyTable(all))
            return all;

        int counter = 0;
        String[][] requests = new String[all.length][all[0].length];
        for (String row[] : all) {
            if (row[4].equals("Under Review")) {
                int count = 0;
                for (String item : row) {
                    requests[counter][count] = item;
                    count++;
                }
            }
            counter++;
        }
        return hlp.removeNullRows(requests);
    }

    /** 
    * leader respond to a request
    * @param Id - request id 
    * @param respond - leader respond on request 
    * @return 0 on success
    */
    public boolean respondRequest(String Id, String respond) throws Exception {
        if (!respond.toLowerCase().equals("approved") && !respond.toLowerCase().equals("refused"))
            return false;
        else {
            fs.update("request",
                    hlp.intToArr(0), hlp.paramsToArr(Id), hlp.intToArr(4), hlp.paramsToArr(respond));
            return true;
        }
    }


    public String[][] showProjects()
    {
        return (fs.read("projects"));
    }


    public boolean addProject(String title, String description, String startDate, String endDate) {
        try {
            project = new Project(title, description, startDate, endDate);
            fs.add("projects", project.getInformation());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteProject(int project_id) {
        try {
            fs.delete("projects", hlp.intToArr(0), hlp.paramsToArr(project_id));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updateProject(int project_id, int column, String newvalue) {
        try {
            fs.update("projects", hlp.intToArr(0), hlp.paramsToArr(project_id),
                    project.hlp.intToArr(column), hlp.paramsToArr(newvalue));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    public String[][] showTasks(int project_id) {
        try {
            return (fs.read("tasks", hlp.intToArr(1), hlp.paramsToArr(project_id)));
        } catch (Exception e) {
            return new String[0][0];
        }
    }


    public boolean addTask(String title, String description, String starTime, String endTime, String phase,
            String estimationHours, String priority, int project_id, int empId) {
        try {
            int index = fs.read("tasks", hlp.intToArr(1), hlp.paramsToArr(project_id)).length;

            project.tasks[index] = new Task(title, description, starTime, endTime, phase, estimationHours, priority, this.getId(),
                    empId);

            // Get the auto-incremented value of id that is put in the "tasks" file

            // Add to "tasks" file
            fs.add("tasks",
                    hlp.paramsToArr(project.tasks[index].getId(), project_id, this.getId(), empId,
                            project.tasks[index].getTitle(), project.tasks[index].getDescription(),
                            project.tasks[index].getStarTime(), project.tasks[index].getEndTime(),
                            project.tasks[index].getPhase(), project.tasks[index].getEstimationHours()));

            // Add to "task_log" file with the auto-incremented value of id from "tasks"
            // file
            fs.add("task_log",
                    hlp.paramsToArr(project.tasks[index].getId(), project.tasks[index].getId(), empId, null, null,
                            null));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    public boolean updateTask(int task_id, int column, String newvalue)
    {
        try {
            fs.update("tasks", hlp.intToArr(0), hlp.paramsToArr(task_id),
                    hlp.intToArr(column), hlp.paramsToArr(newvalue));
            if (column == 1) {
                fs.update("task_log", hlp.intToArr(1), hlp.paramsToArr(task_id),
                        hlp.intToArr(column), hlp.paramsToArr(newvalue));
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteTask(int task_id) {
        try {
            project.fs.delete("tasks", project.hlp.intToArr(0), project.hlp.paramsToArr(task_id));
            project.fs.delete("task_log", project.hlp.intToArr(1), project.hlp.paramsToArr(task_id));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String[][] taskLog(int task_id)
    {
        try {
            return (fs.read("task_log", hlp.intToArr(1), hlp.paramsToArr(task_id)));
        } catch (Exception e) {
            return new String[0][0];
        }
    }
    

    public String[][] Calendar()
    {
        try {
            String[][] all = fs.read("timestamp");
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String nowDate = now.format(formatter);
            String[][] res = new String[all.length][all[0].length] ;
            int counter = 0;
            for(String[] row : all)
            {
                if(nowDate.equals(row[1]))
                {
                    int count=0;
                    for (String item : row)
                    {
                        res[counter][count] = item;
                        count++;
                    }
                }
                counter++;
            }
            return (hlp.removeNullRows(res));
        } catch (Exception e) {
            return new String[0][0];
        }
    }
}
