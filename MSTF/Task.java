public class Task extends information {
  private employee assignedEmployee;
  private String phase;
  private String estimationHours;
  private int priority;
  private String creatorName;

  public Task() {
    super();
    id= fs.getNextID("tasks");
  }

  public Task(String title, String description, String starTime,String endTime,String phase, String estimationHours, int priority ) {
    super(title, description, starTime, endTime);
    this.phase = phase;
    this.estimationHours = estimationHours;
    this.priority = priority;
    id= fs.getNextID("tasks");
  }

  public String getPhase() {
    return phase;
  }

  public void setPhase(String phase) {
    this.phase = phase;
  }

  public String getEstimationHours() {
    return estimationHours;
  }

  public void setEstimationHours(String estimationHours) {
    this.estimationHours = estimationHours;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

}
