import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GuestList seminar = null;

        try{
            seminar = GuestList.readFromBinaryFile();
            System.out.println("Bine Ai revenit");
            System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
        }catch (IOException e){
            System.out.println("Bun venit! Creeaza un eveniment nou");
            seminar = AuxMethods.createGuestList(sc);
        }

        String input;
        do{
            input = sc.nextLine();
            switch (input){
                case "quit":
                    try{
                        GuestList.writeToBinaryFile(seminar);
                    }catch (IOException e){
                        System.out.println("Something went wrong");
                        e.printStackTrace();
                    }
                    System.out.println("Aplicatia se inchide...");
                    break;
                case "help":
                    AuxMethods.printMenu();
                    break;
                case "check":
                    seminar.printCheckResult(AuxMethods.searchCriteria(sc));
                    break;
                case "add" :
                    System.out.println("Introduceti numele de familie:");
                    String lastName = sc.nextLine();
                    System.out.println("Introduceti prenumele");
                    String firstName = sc.nextLine();
                    System.out.println("Introduceti email:");
                    String email = sc.nextLine();
                    System.out.println("Introduceti numar de telefon");
                    String phoneNumber = sc.nextLine();
                    seminar.printAddResult(lastName, firstName, email, phoneNumber);
                    break;
                case "remove" :
                    seminar.printRemoveResult(AuxMethods.searchCriteria(sc));
                    break;
                case "update" :
                    seminar.printUpdateResult(AuxMethods.searchCriteria(sc));
                    break;
                case "guests" :
                    seminar.printGuestList();
                    break;
                case "waitlist":
                    seminar.printWaitList();
                    break;
                case "available":
                    seminar.available();
                    break;
                case "flush" :

                    seminar = AuxMethods.createGuestList(sc);
                    File binary = new File("guests.dat");
                    binary.delete();
//                        GuestList.writeToBinaryFile(seminar);

                    break;
                case "guests_no":
                    seminar.guestsNo();
                    break;
                case "waitlist_no":
                    seminar.waitListNo();
                    break;
                case "subscribe_no":
                    seminar.subscribedGuests();
                    break;
                case "search":
                    seminar.searchResult(sc);
                    break;
                default:
                    System.out.println("Comanda invalida...\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
            }
        }while(!input.equals("quit"));
    }
}