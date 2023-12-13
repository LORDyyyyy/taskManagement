public class Project extends information {
    Task[] tasks = new Task[99];

    public Project() {
        id = fs.getNextID("projects");
    }

    public Project(String title, String description, String starTime, String endTime, int numberOfTasks) {
        super(title, description, starTime, endTime);
        tasks = new Task[numberOfTasks];
        id = fs.getNextID("projects");
    }
}