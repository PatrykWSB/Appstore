import java.time.LocalDate;
import java.util.Random;


public class Project {
    public Integer[] timeForTask = new Integer[6];
    /// front-end[0] ; backend[1] ; bazy[2] ; mobile[3] ; wordpress[4] ; prestashop [5]
    public Client client;
    public LocalDate commissionDate;
    public Integer penalty;
    public Integer cost;
    public LocalDate paymentDate;
    public Integer level; //0-2
    public Integer bugs;
    public Integer testDays;
    public Integer totalTaskDays = projectLength();
    public Programmer programmer;
    Random random = new Random();

    public void generate(LocalDate today){
        this.level = random.nextInt(3)+1;
        int exit=level;
        int buffor;
        while(exit!=0){
            buffor = random.nextInt(6);
            if(this.timeForTask[buffor]==null) {
                this.timeForTask[buffor] = random.nextInt(7) + 1;
                exit-=1;
            }
        }
        this.client = Settings.clientList[random.nextInt(3)];
        this.commissionDate = today.plusDays(projectLength()+7);
        this.paymentDate = commissionDate.plusDays(random.nextInt(14));
        this.cost = projectLength()*50*(level+1);
        this.penalty = cost/10;
    }

    public Integer projectLength(){
        Integer length=0;
        for(int i=0; i<6; i++){
            if(timeForTask[i]!=null) {
                length += timeForTask[i];
            }
        }
        return length;
    }

   /* public boolean isProjectFinished(){
        boolean result = true;
        for(int i=0; i<6; i++){
            if(timeForTask[i] != 0) {
                result = false;
                break;
            }
        }
        return result;
    }*/

    public Project setTimeForTask(Integer i){
        this.timeForTask[i]--;
        return this;
    }

    public Project setProgrammerForTask(Programmer programmer) {
        this.programmer = programmer;
        return this;
    }

    public Project setPaymentDate(LocalDate date) {
        this.paymentDate = date;
        return this;
    }

    public Project setTotalTimeForTask(){
        this.totalTaskDays--;
        return this;
    }

    public Project setBug(){
        this.bugs++;
        return this;
    }


    public String toString(){
        return "FE: " + timeForTask[0] + "; BE: " + timeForTask[1] + "; BASES: " + timeForTask[2] + "; MOB: " + timeForTask[3] + "; WP: " + timeForTask[4] + "; PS: " + timeForTask[5] + "  ZA " + cost + "zł do oddania do " + commissionDate + " zapłata do " + paymentDate + "\n";
    }
}