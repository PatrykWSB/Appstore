import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public LocalDate date = Settings.GAME_START_DAY;
    public Scanner keyboard = new Scanner(System.in);
    public Integer intSwitch = 0;
    public Integer zus = 0;
    public Integer lookForClient = 0;
    public ArrayList<Programmer> availableProgrammers = new ArrayList<>();
    public Integer advertisement = 0;
    public Integer availableTesters = 0;
    public Integer availableSellers = 0;
    public Boolean exit = true;
    public Random random= new Random();
    public boolean win = true;

    public void day(Player player){
        while(win){
            payment(player);
            workerPrograming(player);
            round(player);
            win = !checkWin(player);
            checkZus();
        }
    }

    public void round(Player player) {
        while (exit) { // zmienić na exit
            System.out.println("Dziś jest " + date + " co chcesz zrobić?");
            System.out.println("(1) Dostępne Projekty"); // podpisac nowy
            System.out.println("(2) Sprawdź stan realizacji projektow");  // oddaj projekt
            System.out.println("(3) Sprawdź stan konta");
            System.out.println("(4) Szukaj klientów");
            System.out.println("(5) Programuj");
            System.out.println("(6) Testuj");
            System.out.println("(7) Zatrudnij pracownika");
            System.out.println("(8) Przejrzyj/zwolnij pracowników");
            System.out.println("(9) Rozlicz ZUS");

            intSwitch = keyboard.nextInt();
            switch (intSwitch) {
                case 1:
                    System.out.println(player.availableProjectsList);
                    signProject(player);
                    break;
                case 2:
                    openProjects(player);
                    break;
                case 3:
                    System.out.println("Na koncie masz: " + player.cash + "zł");
                    break;
                case 4:
                    lookForClient += 1;
                    if(lookForClient==5){
                        lookForClient = 0;
                        newProject(player);
                    }
                    exit = false;
                    break;
                case 5:
                    System.out.println("Aby programować samemu wpisz 0. Aby zlecić projekt komuś innemu wpisz 1");
                    intSwitch = keyboard.nextInt();
                    if(intSwitch==0) programming(player);
                    else if (intSwitch==1) assignProgrammer(player);
                    break;
                case 6:
                    test(player);
                    break;
                case 7:
                    hireWorker(player);
                    break;
                case 8:
                    fireWorker(player);
                    break;
                case 9:
                    zus += 1;
                    exit = false;
                    break;
            }
        }
        date = date.plusDays(1);
    }

    public void openProjects(Player player){ /// do rozwinięcia (klienci bugi i inne)
        for(int i=0; i<player.startedProjectsList.size(); i++){
            System.out.println((i+1) + ". " + player.startedProjectsList.get(i) + "\n");
        }
        if (player.startedProjectsList.size() != 0) {
            System.out.println("Czy chcesz oddać któryś projekt? (aby oddać wpisz numer projektu aby wrócić wpisz 0");
            intSwitch = keyboard.nextInt();
            if (player.startedProjectsList.get(intSwitch - 1).totalTaskDays==0) {
                closeProject(player, intSwitch-1);
            }
        }
        else System.out.println("Nie masz żadnych dostepnych projektów");
    }

    public void newProject(Player player){
        Project project = new Project();
        project.generate(date);
        player.availableProjectsList.add(project);
    }

    public void hireWorker(Player player){
        if(availableProgrammers.size() == 0) System.out.println("Nie ma żadnych dostępnych pracowników");
        else{
            System.out.println("Dostępnych jest " + availableTesters + " testerów. Każdy kosztuje 300zl miesiecznie");
            System.out.println("Dostępnych jest " + availableSellers + " sprzedawców. Każdy kosztuje 100zl miesięcznie");
            for(int i=0; i<availableProgrammers.size(); i++){
                System.out.println("(" + i+2 + ")" + availableProgrammers.get(i));
            }
            System.out.println("Aby zatrudnić testera wpisz 0, aby zatrudnić sprzedawcę wpisz 1, aby zatrudnić programistę wpisz przypisany mu numer");
            intSwitch = keyboard.nextInt();
            if(intSwitch == 0 && availableTesters != 0) {
                player.cash -= 30;
                player.testers++;
                exit = false;
            }
            else if(intSwitch == 0 && availableTesters == 0) System.out.println("Nie masz żadnych testerów do zatrudnienia");
            else if(intSwitch == 1 && availableSellers != 0) {
                player.cash -= 10;
                player.sellersList.add(0);
                exit = false;
            }
            else if(intSwitch == 1 && availableSellers == 0) System.out.println("Nie masz żadnych sprzedawców do zatrudnienia");
            else if(intSwitch-2<availableProgrammers.size()){
                player.programmerList.add(availableProgrammers.get(intSwitch-2));
                player.cash -= availableProgrammers.get(intSwitch-2).cost/10;
                exit = false;
            }
        }
    }

    public void fireWorker(Player player){
        System.out.println("Posiadasz " + player.testers + " testerów");
        System.out.println("Posiadasz " + player.sellersList.size() + " sprzedawców");
        if (player.programmerList.size() == 0) System.out.println("Nie posiadasz żadnych programistów");
        else {
            System.out.println("oto Twoi pracownicy:");
            for (int i = 0; i < player.programmerList.size(); i++) {
                System.out.println("(" + i+3 + ")" + player.programmerList.get(i));
            }
            System.out.println("Aby zwolnić testera wpisz 1, aby zwolnić sprzedawcę wpisz 2, aby zwolnić programistę wpisz odpowiadający mu numer. Aby wrócić wpisz 0");
            intSwitch = keyboard.nextInt();
            if(intSwitch==1) {
                player.sellersList.remove(player.sellersList.size()-1);
                exit = false;
            }
            else if(intSwitch==2) {
                player.testers--;
                exit = false;
            }
            else if(intSwitch<player.programmerList.size()+3) {
                player.programmerList.remove(intSwitch-3);
                exit = false;
            }
        }
    }

    public void test(Player player){
        System.out.println("Który projekt chcesz testować (Aby wrócić wpisz 0)");
        for(int i=0; i<player.startedProjectsList.size(); i++){
            System.out.println("(" + i+1 + ")" + player.startedProjectsList.get(i));
        }
        intSwitch = keyboard.nextInt();
        if(intSwitch!=0 && intSwitch<player.startedProjectsList.size()+1) {
            player.startedProjectsList.get(intSwitch-1).testDays++;
            exit = false;
        }
    }

    public void programming(Player player){
        int buffor;
        System.out.println("Nad którym projektem chcesz pracować");
        for(int i=0; i<player.startedProjectsList.size(); i++){
            System.out.println("(" + player.startedProjectsList.get(i)+1 + ")");
        }
        intSwitch = keyboard.nextInt();
        if(intSwitch<player.startedProjectsList.size()+1){
            buffor = intSwitch;
            System.out.println("Nad którą technologią projektu chcesz pracować? (0)FE  (1)BE  (2)Bazy (3)Mobile  (4)WP  (5)PS");
            intSwitch = keyboard.nextInt();
            if(player.skillList[intSwitch]==1 && player.startedProjectsList.get(buffor).timeForTask[intSwitch] > 0) {
                player.startedProjectsList.set(buffor,player.startedProjectsList.get(buffor).setTimeForTask(buffor));
                exit = false;
            }
            else if(player.skillList[intSwitch]!=1) System.out.println("Nie umisz w to technologie");
            else if(player.startedProjectsList.get(buffor).timeForTask[intSwitch]==0) System.out.println("Już więcej w tej technologi nei zdziałasz");
            else System.out.println("ERROR: coś nie działą spróbuj ponownie");
        }
    }

    public void workerPrograming(Player player){
        for(int i=0; i<player.startedProjectsList.size(); i++){
            if(player.startedProjectsList.get(i).programmer != null){
                if(random.nextInt(100) < player.startedProjectsList.get(i).programmer.punctuality){
                    if(random.nextInt(100) > player.startedProjectsList.get(i).programmer.accuracy){
                        player.startedProjectsList.set(i,player.startedProjectsList.get(i).setBug());
                    }
                    player.startedProjectsList.set(i,player.startedProjectsList.get(i).setTotalTimeForTask());
                    if(player.startedProjectsList.get(i).totalTaskDays==0){
                        player.startedProjectsList.set(i,player.startedProjectsList.get(i).setProgrammerForTask(null));
                    }
                }
            }
        }
    }

    public void assignProgrammer(Player player){
        int buffor;
        System.out.println("Do którego projektu przypisać wykonawce");
        for(int i=0; i<player.startedProjectsList.size(); i++){
            System.out.println("(" + player.startedProjectsList.get(i)+1 + ")");
        }
        intSwitch = keyboard.nextInt();
        if(intSwitch < player.startedProjectsList.size()+1 && intSwitch != 0 && player.startedProjectsList.get(intSwitch) != null) {
            buffor = intSwitch;
            System.out.println("Kogo chcesz przypisać do projektu");
            System.out.println("(1) " + Settings.bestStudent);
            System.out.println("(2) " + Settings.averageStudent);
            System.out.println("(3) " + Settings.worstStudent);
            for (int i = 0; i < player.programmerList.size(); i++) {
                System.out.println("(" + i+4 + ")" + player.programmerList.get(i));
            }
            intSwitch = keyboard.nextInt();
            if(!isProgrammerAssigned(player,player.programmerList.get(intSwitch))){
                if(doesProgrammerHaveSkills(player.programmerList.get(intSwitch),player.startedProjectsList.get(buffor))) {
                    player.startedProjectsList.set(buffor, player.startedProjectsList.get(buffor).setProgrammerForTask(player.programmerList.get(intSwitch)));
                }
            }
            else System.out.println("Programista jest już przypisany do projekt");
        }
    }

    public boolean isProgrammerAssigned(Player player, Programmer programmer){
        for(int i=0; i<player.startedProjectsList.size(); i++){
            if(player.startedProjectsList.get(i).programmer == programmer) return true;
        }
        return false;
    }

    public boolean doesProgrammerHaveSkills(Programmer programmer, Project project){
        for(int i=0; i<6; i++){
            if(project.timeForTask[i] != 0 && programmer.skillList[i] == 0) return false;
        }
        return true;
    }

    public void closeProject(Player player, Integer index){
        if(date.compareTo(player.startedProjectsList.get(index).commissionDate)>0) {
            if(random.nextInt(100)>player.startedProjectsList.get(index).client.chanceForNoPenalty) {
                player.cash -= player.startedProjectsList.get(index).penalty;
            }
        }
        if(random.nextInt(100)<player.startedProjectsList.get(index).client.chanceForPaymentDelay){
            player.startedProjectsList.set(index,player.startedProjectsList.get(index).setPaymentDate(player.startedProjectsList.get(index).paymentDate.plusDays(7)));
        }
        if(player.testers*3>player.programmerList.size()){
            if(player.startedProjectsList.get(index).bugs > player.startedProjectsList.get(index).testDays){
                if(random.nextInt(100)<player.startedProjectsList.get(index).client.chanceForLoosingProject)
                    player.startedProjectsList.remove(index);
            }
        }
        if(random.nextInt(100)<=player.startedProjectsList.get(index).client.chanceForNoPayment){
            player.startedProjectsList.remove(index);
        }
        player.closedProjectsList.add(player.startedProjectsList.get(index));
        player.startedProjectsList.remove(index);
        exit = false;
    }

    public void signProject(Player player){
        for(int i=0; i<player.availableProjectsList.size(); i++){
            System.out.println("(" + (i+1) + ")" + player.availableProjectsList.get(i));
        }
        System.out.println("Aby podpisać nowy projekt wpisz jego numer");
        intSwitch = keyboard.nextInt();
        if(intSwitch<player.availableProjectsList.size()+1 && intSwitch > 0){
            player.startedProjectsList.add(player.availableProjectsList.get(intSwitch));
            player.availableProjectsList.remove(intSwitch);
        }
    }

    public void payment(Player player){
         for(int i=0; i<player.closedProjectsList.size(); i++){
             if(player.closedProjectsList.get(i).paymentDate==date) {
                 player.cash += player.closedProjectsList.get(i).cost;
             }
         }
    }

    public boolean checkWin(Player player){
        if(player.cash>1100) return true;
        else return false;
    }

    public void checkZus(){
        if(date.getMonthValue()>date.plusDays(-1).getMonthValue()){
            if(zus<2){
                System.out.println("nie opłaciłeś zusu przegrywasz");
                win=true;
            }
            zus=0;
        }
    }
}
