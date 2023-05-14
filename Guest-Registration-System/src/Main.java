import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GuestList seminar = AuxMethods.createGuestList(sc);
        seminar.directAdd(new Guest("Unescu", "Unu", "unu@gmail.com", "0111111111"));
        seminar.directAdd(new Guest("Doiescu", "Doi", "doi@gmail.com", "0222222222"));
        seminar.directAddWaiting(new Guest("Treiescu", "Trei", "trei@gmail.com", "0333333333"));


        String input;
        do{
            input = sc.nextLine();
            switch (input){
                case "quit":
                    System.out.println("Aplicatia se inchide...");
                    break;
                case "help":
                    AuxMethods.printMenu();
                    break;
                case "check":
                    seminar.printCheckResult(AuxMethods.searchCriteria(sc));
                    break;
                case "add" :
                    String lastName = AuxMethods.validLastName(sc);
                    String firstName = AuxMethods.validFirstName(sc);
                    String email = AuxMethods.validEmail(sc);
                    String phoneNumber = AuxMethods.validPhoneNumber(sc);
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