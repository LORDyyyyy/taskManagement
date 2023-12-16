public class Project extends information {
    Task[] tasks = new Task[99];

    public Project() {
        id = fs.getNextID("projects");
    }

    public Project(String title, String description, String starTime, String endTime) {
        super(title, description, starTime, endTime);
        id = fs.getNextID("projects");
    }
    public String[] getInformation(){
        return hlp.paramsToArr(getId(),getTitle(),getDescription(),getStarTime(),getEndTime());
    }
}