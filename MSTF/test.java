public class test {
    public static void addeProject(String title, String description, String startDate, String endDate, int numberOfTasks)
            throws Exception {

        Project project = new Project(title, description, startDate, endDate, numberOfTasks);
        project.fs.add("projects", project.hlp.paramsToArr(project.getId(), project.getTitle(),
                project.getDescription(), project.getStarTime(), project.getEndTime()));
    }

    public static void main(String[] args) throws Exception {
        // Task task1= new Task("javatask","pl2 project","22/12","30/12","working
        // on","7",1);
        // String title, String description, String starTime,String endTime,String
        // phase, String estimationHours, int priority
        FileStorage fs=new FileStorage();
        // try{
        fs.createTable("projects","project_id","project_title","project_description","project_startTime","project_endtime");
        System.out.println("file has successfulyy created");
        // }catch(Exception e){
        // System.out.println("the file isn't created");
        // }
        // try{
        
      
        addeProject("try", "a try", "1/11", "2/11", 6);
    }
}
