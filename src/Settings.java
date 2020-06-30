import java.time.LocalDate;

public class Settings {
    public final static LocalDate GAME_START_DAY = LocalDate.of(2020,1, 1);
    public final static Integer GAME_START_CASH = 10000;
    public final static String[] namesList = new String[]{"Andrzej","Janusz","Bartek","Olgierd","Patryk","Marcin","Wojtek","Tomek","Adrian","Michał","Justyna","Agnieszka","Martyna","Barbara","Marta","Żesika","Danuta","Małgosia","Julia","Elżbieta"};

    public final static Client COOL_CLIENT = new Client(30,20,0,0);
    public final static Client TOUGH_CLIENT = new Client(0,0,50,0);
    public final static Client MF_CLIENT = new Client(30,0,100,1);
    public static Client[] clientList = new Client[]{COOL_CLIENT,TOUGH_CLIENT,MF_CLIENT};

    public final static Integer[] STUDENT_SKILL_LIST = new Integer[]{1,1,1,1,1,1};
    public static Programmer bestStudent = new Programmer("Kacper",STUDENT_SKILL_LIST,100,100,500);
    public static Programmer averageStudent = new Programmer("Melchior",STUDENT_SKILL_LIST,90,100,350);
    public static Programmer worstStudent = new Programmer("Baltazar",STUDENT_SKILL_LIST,80,80,200);




}
