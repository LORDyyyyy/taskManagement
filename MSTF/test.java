public class test {
    int id;
    private Project project;

    public test() {
        project = new Project();
        id = 1;
    }

    // functions for the leader inside its class , don't care about thr=e obove
    // attributes and methods it's just for testing
    public boolean addProject(String title, String description, String startDate, String endDate) {
        try {
            project = new Project(title, description, startDate, endDate);
            project.fs.add("projects", project.getInformation());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteProject(int project_id) {
        try {
            project.fs.delete("projects", project.hlp.intToArr(0), project.hlp.paramsToArr(project_id));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updateProject(int project_id, int column, String newvalue) {
        try {
            project.fs.update("projects", project.hlp.intToArr(0), project.hlp.paramsToArr(project_id),
                    project.hlp.intToArr(column), project.hlp.paramsToArr(newvalue));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean addTask(String title, String description, String starTime, String endTime, String phase,
            String estimationHours, String priority, int project_id, int id, int empId) {
        try {
            int index = project.fs.read("tasks", project.hlp.intToArr(1), project.hlp.paramsToArr(project_id)).length;
            project.tasks[index] = new Task(title, description, starTime, endTime, phase, estimationHours, priority, id,
                    empId);

            // Get the auto-incremented value of id that is put in the "tasks" file

            // Add to "tasks" file
            project.fs.add("tasks",
                    project.hlp.paramsToArr(project.tasks[index].getId(), project_id, id, empId,
                            project.tasks[index].getTitle(), project.tasks[index].getDescription(),
                            project.tasks[index].getStarTime(), project.tasks[index].getEndTime(),
                            project.tasks[index].getPhase(), project.tasks[index].getEstimationHours()));

            // Add to "task_log" file with the auto-incremented value of id from "tasks"
            // file
            project.fs.add("task_log",
                    project.hlp.paramsToArr(null, project.tasks[index].getId(), null, null, null, null));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteTask(int task_id) {
        // Delete from "tasks" file
        try {
            project.fs.delete("tasks", project.hlp.intToArr(0), project.hlp.paramsToArr(task_id));

            // Delete from "task_log" file based on the task_id
            project.fs.delete("task_log", project.hlp.intToArr(1), project.hlp.paramsToArr(task_id));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updateTask(int task_id, int column, String newvalue) {
        try {
            project.fs.update("tasks", project.hlp.intToArr(0), project.hlp.paramsToArr(task_id),
                    project.hlp.intToArr(column), project.hlp.paramsToArr(newvalue));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // end of leader tasks
    public static void main(String[] args) {

        FileStorage fs = new FileStorage(25);

        test mstf2 = new test();
        // mstf2.deleteTask(5);
        // mstf2.addProject("moataz", "moataz project", "27/11", "12/12");
        mstf2.updateTask(2, 5, "yarab ostor");
        // fs.createTable("projects",
        // "project_id","project_title","project_description","project_startTime","project_endtime");

      
        // mstf2.addProject("3try", "a try", "1/11", "2/11");
        // mstf2.updateProject(3, 2,"new des");
        // mstf2.deleteProject(3);
        // mstf2.addTask("mstf", "a try fr", "16/11", "22/11", "working on", "5 hours","important", 2, mstf2.id, 3);
    }
}
