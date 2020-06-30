import java.time.LocalDate;
import java.util.ArrayList;
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

    public void round(Player player) {
        while (intSwitch!=110) {
            System.out.println("Dziś jest " + date + " co chcesz zrobić?");
            System.out.println("(1) Dostępne Projekty"); // podpisac nowy
            System.out.println("(2) Sprawdź stan realizacji projektow");  // oddaj projekt
            System.out.println("(3) Sprawdź stan konta");
            System.out.println("(4) Szukaj klientów");
            System.out.println("(5) Programuj");
            System.out.println("(6) Testuj");
            System.out.println("(7) Zatrudnij pracownika");
            System.out.println("(8) Przejrzyj pracowników"); // zwolnij
            System.out.println("(9) Rozlicz ZUS");

            intSwitch = keyboard.nextInt();
            switch (intSwitch) {
                case 1:
                    System.out.println(player.availableProjectsList);
                    break;
                case 2:
                    openProjects(player);
                    break;
                case 3:
                    System.out.println(player.cash);
                    break;
                case 4:
                    lookForClient += 1;
                    if(lookForClient==5){
                        lookForClient = 0;
                        newProject(player);
                    }
                    break;
                case 5: /// programuj
                    break;
                case 6: /// testuj
                    break;
                case 7:
                    hire(player);
                    break;
                case 8:
                    System.out.println(player.programmerList);
                    break;
                case 9:
                    zus += 1;
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
            if (player.startedProjectsList.get(intSwitch - 1).isProjectFinished()) {
                player.cash += player.startedProjectsList.get(intSwitch - 1).cost;
                player.startedProjectsList.remove(intSwitch - 1);
            }
        }
        else System.out.println("Nie masz żadnych dostepnych projektów");
    }

    public void newProject(Player player){
        Project project = new Project();
        project.generate(date);
        player.availableProjectsList.add(project);
    }

    public void hire(Player player){
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
            }
            else if(intSwitch == 0 && availableTesters == 0) System.out.println("Nie masz żadnych testerów do zatrudnienia");
            else if(intSwitch == 1 && availableSellers != 0) {
                player.cash -= 10;
                player.sellersList.add(0);
            }
            else if(intSwitch == 1 && availableSellers == 0) System.out.println("Nie masz żadnych sprzedawców do zatrudnienia");
            else if(intSwitch-2<availableProgrammers.size()){
                player.programmerList.add(availableProgrammers.get(intSwitch-2));
                player.cash -= availableProgrammers.get(intSwitch-2).cost/10;
            }
        }
    }


}
