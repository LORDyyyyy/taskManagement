public class employee {
    int id;
    String name;
    public employee(int id,String name){
        this.id=id;
        this.name=name;
    }
}
class leader{

  public  void addeProject(String title,String description, String startDate, String endDate ,int numberOfTasks) throws Exception
    {
        
        Project project= new Project( title, description, startDate, endDate,numberOfTasks);
        project.fs.add("projects", project.hlp.paramsToArr(project.getId(),project.getTitle(),project.getDescription(),project.getStarTime(),project.getEndTime()));
    }
}
