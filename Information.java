public abstract class Information {

    protected FileStorage fs = new FileStorage();
    protected Helpers hlp = new Helpers();
    protected int id;
    private String title;
    private String description;
    private String starTime;
    private String endTime;

    public Information() {
    }

    public Information(String title, String description, String starTime, String endTime) {
        this.title = title;
        this.description = description;
        this.starTime = starTime;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStarTime() {
        return starTime;
    }

    public void setStarTime(String starTime) {
        this.starTime = starTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }
    abstract public String[] getInformation();
}
