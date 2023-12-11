public class Project extends information{
    Task[] tasks;   
    public Project(){
        id = fs.getNextID("projects");
    }
    public Project(String title,String description, String starTime, String endTime,int numberOfTasks){
        super(title, description, starTime, endTime);
        System.out.println("connected");
        tasks= new Task[numberOfTasks];
        id = fs.getNextID("projects");
    }
}