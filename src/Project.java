import java.time.LocalDate;

public class Project {
    public String projectName;
    public Integer[] timeForTask = new Integer[6];
    /// front-end[0] ; backend[1] ; bazy[2] ; mobile[3] ; wordpress[4] ; prestashop [5]
    public Client client;
    public LocalDate commissionDate;
    public Double penalty;
    public Double cost;
    public LocalDate paymentDate;
    public Integer level; //1- 3
    public Double loan;
    public Integer bugs;
    public Integer testDays;

    public boolean isProjectFinished(){
        boolean result = true;
        for(int i=0; i<6; i++){
            if(timeForTask[i] != 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}