import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public LocalDate date = Settings.GAME_START_DAY;
    Scanner keyboard = new Scanner(System.in);
    Integer intSwitch = 0;
    Integer zus = 0;
    Integer lookForClient = 0;

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
                case 4: /// do szukaj klientow
                    lookForClient += 1;
                    // if lookForClient == 5 generate new project
                    break;
                case 5: /// programuj
                    break;
                case 6: /// testuj
                    break;
                case 7: /// zatrudnij
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


}
