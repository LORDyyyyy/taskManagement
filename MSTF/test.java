public class test {
    int id;
    private Project project;
    private Task task;

    public test() {
        project = new Project();
        task = new Task();
        id = 1;
    }
    // functions for  the leader inside its class , don't care about thr=e obove attributes and methods it's just for testing 
    public void addeProject(String title, String description, String startDate, String endDate, int numberOfTasks)
            throws Exception {

        project = new Project(title, description, startDate, endDate, numberOfTasks);
        project.fs.add("projects", project.hlp.paramsToArr(project.getId(), project.getTitle(),
                project.getDescription(), project.getStarTime(), project.getEndTime(), numberOfTasks));
    }

    public void deleteProject(int project_id) throws Exception {
        project.fs.delete("projects", project.hlp.intToArr(0), project.hlp.paramsToArr(project_id));
    }

    public void updateProject(int project_id, int column, String newvalue) throws Exception {
        project.fs.update("projects", project.hlp.intToArr(0), project.hlp.paramsToArr(project_id),
                project.hlp.intToArr(column), project.hlp.paramsToArr(newvalue));
    }

    public void addTask(String title, String description, String starTime, String endTime, String phase,
            String estimationHours, String priority, int project_id, int id) throws Exception {
        int index = project.fs.read("tasks", project.hlp.intToArr(1), project.hlp.paramsToArr(project_id)).length;
        project.tasks[index] = new Task(title, description, starTime, endTime, phase, estimationHours, priority, id);
        project.fs.add("tasks",
                project.hlp.paramsToArr(project.tasks[index].getId(), project_id, id, "none",
                        project.tasks[index].getTitle(), project.tasks[index].getDescription(),
                        project.tasks[index].getStarTime(), project.tasks[index].getEndTime(),
                        project.tasks[index].getPhase(), project.tasks[index].getEstimationHours()));

    }

    public void deleteTask(int task_id) throws Exception{
        project.fs.delete("tasks", project.hlp.intToArr(0), project.hlp.paramsToArr(task_id));
    }

    public void updateTask(int project_id, int column, String newvalue) throws Exception{
        project.fs.update("tasks", project.hlp.intToArr(0), project.hlp.paramsToArr(project_id),
                project.hlp.intToArr(column), project.hlp.paramsToArr(newvalue));
    }
    // end of leader tasks
    public static void main(String[] args) throws Exception {
        // Task task1= new Task("javatask","pl2 project","22/12","30/12","working
        // on","7",1);
        // String title, String description, String starTime,String endTime,String
        // phase, String estimationHours, int priority
        FileStorage fs = new FileStorage();
        // // try{
        // fs.createTable("projects","project_id","project_title","project_description","project_startTime","project_endtime","numberOfTasks");
        // System.out.println("file has successfulyy created");
        // }catch(Exception e){
        // System.out.println("the file isn't created");
        // }
        // try{
        // leader mstf=new leader();
        test mstf2 = new test();
            mstf2.updateTask(2, 4, "new try");
        // mstf2.addeProject("3try", "a try", "1/11", "2/11", 6);
        // mstf2.updateProject(1, 1,"mstf");
        // mstf2.addTask("try", "a try fr", "16/11", "22/11", "working on", "5 hours", "important", 2, mstf2.id);
    }
}
