import java.util.ArrayList;

public class Player {
    public String name;
    public Integer[] skillList = new Integer[]{1,0,1,0,1,1};
    public Integer cash;
    public ArrayList<Programmer> programmerList = new ArrayList<>();
    public ArrayList<Integer> sellersList = new ArrayList<>();
    public Integer testers;
    public ArrayList<Project> availableProjectsList = new ArrayList<>();
    public ArrayList<Project> startedProjectsList = new ArrayList<>();
    public ArrayList<Project> closedProjectsList = new ArrayList<>();
}
/// front-end[0] ; backend[1] ; bazy[2] ; mobile[3] ; wordpress[4] ; prestashop [5]