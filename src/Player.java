import java.util.ArrayList;

public class Player {
    public String name;
    public String[] skillList = {"DATABASE","FRONTEND","WORDPRESS","PRESTASHOP"};
    public Double cash;
    public ArrayList<Programmer> programmerList = new ArrayList<>();
    public Integer sellers;
    public Integer testers;
    public ArrayList<Project> availableProjectsList = new ArrayList<>();
    public ArrayList<Project> startedProjectsList = new ArrayList<>();


}
