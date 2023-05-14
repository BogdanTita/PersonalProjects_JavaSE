package games;
import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlayerStatus.setGameName("BATTLEFIELDS"); // apelarea clasei
        System.out.println("Welcome to " + PlayerStatus.getGameName() + "\n");
        System.out.println("Choose a name for your soldier :");
        String playerName = sc.nextLine();
        PlayerStatus player1 = new PlayerStatus(playerName, 1, 15000, "knife");
        PlayerStatus opponent = new PlayerStatus("Enemy", "ak47", 100, 15000, 2700, 2700);
        ImportClass.printMenu();   // ....Start game -> Open menu...
        if(player1.menuOption(opponent) == 9){ //....code for Leave Game.........................
            System.out.println("Game will exit");//.........pt verificare............
        }
    }
}