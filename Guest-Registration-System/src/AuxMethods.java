import java.util.Scanner;

public class AuxMethods {
    public static GuestList createGuestList(Scanner sc){
        System.out.println("Bun venit! Introduceti numarul de locuri disponibile:");
        int input = sc.nextInt();
        sc.nextLine();
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
        return new GuestList(input);
    }

    public static int searchCriteria(Scanner sc){
        System.out.println("\tAlege modul de autentificare, tastand:\n" +
                "\"1\" - Nume si prenume\n" +
                "\"2\" - Email\n" +
                "\"3\" - Numar de telefon (format \"+40733386463\")");
        int input = sc.nextInt();
        sc.nextLine();
        while(input < 1 || input > 3){
            System.out.println("Nu este o optiune valida. Selectati un numar intre \"1\" si \"3\"");
            input = sc.nextInt();
            sc.nextLine();
        }
        return input;
    }
    public static String updateField(Scanner sc){
        System.out.println("Alege campul de actualizat, tastand:\n" +
                "\"1\" - Nume\n" +
                "\"2\" - Prenume\n" +
                "\"3\" - Email\n" +
                "\"4\" - Numar de telefon (format \"+40733386463\")");
        int input = sc.nextInt();
        sc.nextLine();
        while(input < 1 || input > 4){
            System.out.println("Nu este o optiune valida. Selectati un numar intre \"1\" si \"4\"");
            input = sc.nextInt();
            sc.nextLine();
        }
        if(input == 1){
            return "lastName";
        }
        if(input == 2){
            return "firstName";
        }
        if(input == 3){
            return "email";
        }
        return "phoneNumber";
    }
    public static String validLastName(Scanner sc){
        System.out.println("Se adauga o noua persoana...");
        System.out.println("Introduceti numele de familie:");
        String lastName;
        boolean valid;
        do{
            valid = true;
            lastName = sc.nextLine();
            for(int i = 0; i < lastName.length(); i++){
                if(!Character.isLetter(lastName.charAt(i))){
                    valid = false;
                    System.out.println("Format invalid de nume. Incearca din nou, doar litere");
                    break;
                }
            }
        }while(!valid);
        return lastName;
    }
    public static String validFirstName(Scanner sc){
        System.out.println("Introduceti prenumele");
        String firstName;
        boolean valid;
        do{
            valid = true;
            firstName = sc.nextLine();
            for(int i = 0; i < firstName.length(); i++){
                if(!Character.isLetter(firstName.charAt(i))){
                    valid = false;
                    System.out.println("Format invalid de prenume. Incearca din nou, doar litere");
                    break;
                }
            }
        }while(!valid);
        return firstName;
    }
    public static String validEmail(Scanner sc){
        System.out.println("Introduceti email:");
        String email;
        boolean valid;
        do{
            valid = true;
            email = sc.nextLine();
            if(!email.endsWith(".com") || !email.contains("@")){
                System.out.println("Format invalid de email. Trebuie sa contina \"@\" si sa se termine in \".com\"");
                valid = false;
            }
        }while(!valid);
        return email;
    }
    public static String validPhoneNumber(Scanner sc){
        System.out.println("Introduceti numar de telefon (format \"(+4)0733386463\"):");
        String phoneNumber;
        boolean valid;
        do{
            valid = true;
            phoneNumber = sc.nextLine();
            if(phoneNumber.charAt(0) != '0' || phoneNumber.length() != 10){
                valid = false;
            }
            for(int i = 0; i < phoneNumber.length(); i++){
                if(!Character.isDigit(phoneNumber.charAt(i))){
                    valid = false;
                }
            }
            if(!valid){
                System.out.println("Format invalid. Numarul de telefon trebuie sa contina 10 cifre si sa inceapsa cu cifra \"0\"");
            }
        }while(!valid);
        return phoneNumber;
    }
    public static void printMenu(){
        System.out.println("help - Afiseaza aceasta lista de comenzi\n" +
                "add - Adauga o noua persoana (inscriere)\n" +
                "check - Verifica daca o persoana este inscrisa la eveniment\n" +
                "remove - Sterge o persoana existenta din lista\n" +
                "update - Actualizeaza detaliile unei persoane\n" +
                "guests - Lista de persoane care participa la eveniment\n" +
                "waitlist - Persoanele din lista de asteptare\n" +
                "available - Numarul de locuri libere\n" +
                "guests_no - Numarul de persoane care participa la eveniment\n" +
                "waitlist_no - Numarul de persoane din lista de asteptare\n" +
                "subscribe_no - Numarul total de persoane inscrise\n" +
                "search - Cauta toti invitatii conform sirului de caractere introdus\n" +
                "quit - Inchide aplicatia");
    }

}

