public class Task extends Information {
    private int empId;
    private String phase;
    private String estimationHours;
    private String priority;
    private int creatorId;

    public Task() {
        super();
        id = fs.getNextID("tasks");
    }

    public Task(String title, String description, String starTime, String endTime, String phase, String estimationHours,
        String priority, int creatorId, int empId) {
        super(title, description, starTime, endTime);
        this.phase = phase;
        this.estimationHours = estimationHours;
        this.priority = priority;
        id = fs.getNextID("tasks");
        this.creatorId = creatorId;
        this.empId = empId;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String[] getInformation() {
        return hlp.paramsToArr(getId(), getTitle(), getDescription(), getStarTime(), getEndTime(), getPhase(), getEmpId(),
            getCreatorId());
    }
}
