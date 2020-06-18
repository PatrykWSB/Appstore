import java.util.Date;

public class Project {
    private String projectName;
    private Integer[] timeForTask = new Integer[6];
    /// front-end[0] ; backend[1] ; bazy[2] ; mobile[3] ; wordpress[4] ; prestashop [5]
    private Client client;
    private Date commissionDate;
    private Double penalty;
    private Double cost;
    private Date paymentDate;
    private Byte level; //1- 3
    private Double loan;

}