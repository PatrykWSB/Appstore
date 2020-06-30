import java.util.Random;

public class Programmer {
    public String name;
    public Integer[] skillList = new Integer[6];
    public Integer accuracy;
    public Integer punctuality;
    public Integer cost;
    public Random random;

    public Programmer(String name, Integer[] skillList, Integer accuracy, Integer punctuality, Integer cost){
        this.name = name;
        this.skillList = skillList;
        this.accuracy = accuracy;
        this.punctuality = punctuality;
        this.cost = cost;
    }

    public void generate(){
        int buffor=0;
        this.name = Settings.namesList[random.nextInt(20)];
        for (int i=0; i<6; i++){
            skillList[i] = random.nextInt(2);
            buffor++;
        }
        this.accuracy = random.nextInt(20)+80;
        this.punctuality = random.nextInt(20)+80;
        cost = buffor*50 + random.nextInt(7)*50;
    }

    public String toString(){
        return name + " FE:" + skillList[0] + "; BE:" + skillList[1] + "; BASES:" + skillList[2] + "; MOB:" + skillList[3] + "; WP:" + skillList[4] + "; PS:" + skillList[5] + " dokładność: " + accuracy + " punktualność: " + punctuality + " wypłata: " + cost + "\n";
    }


}
