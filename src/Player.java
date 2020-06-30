import java.util.ArrayList;

public class Player {
    public String name;
    public Integer[] list = new Integer[]{1,0,1,0,1,1};
    public Integer cash;
    public ArrayList<Programmer> programmerList = new ArrayList<>();
    public ArrayList<Integer> sellersList = new ArrayList<>();
    public Integer testers;
    public ArrayList<Project> availableProjectsList = new ArrayList<>();
    public ArrayList<Project> startedProjectsList = new ArrayList<>();
}
