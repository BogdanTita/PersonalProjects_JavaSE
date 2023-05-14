import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GuestList implements Serializable {
    private final int maxGuests;
    private final ArrayList<Guest> guestsList;
    private final ArrayList<Guest> waitingList;

    private static final long serialVersionUID = 1L;

    public static void writeToBinaryFile(GuestList data) throws IOException{
        try(ObjectOutputStream binaryFileOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("guests.dat")))){
            binaryFileOut.writeObject(data);
        }
    }

    public static GuestList readFromBinaryFile() throws IOException{
        GuestList guests = null;

        try(ObjectInputStream binaryFileIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("guests.dat")))){
            guests = (GuestList) binaryFileIn.readObject();
        }catch(ClassNotFoundException e){
            System.out.println("A class not found exception: " + e.getMessage());
        }

        return guests;
    }

    public GuestList(int maxGuests){
        this.maxGuests = maxGuests;
        this.guestsList = new ArrayList<>();
        this.waitingList = new ArrayList<>();
    }
    public void directAdd(Guest newGuest){
        guestsList.add(newGuest);
    }
    public void directAddWaiting(Guest newGuest){
        waitingList.add(newGuest);
    }
    public int add(String lastName, String firstName, String email, String phoneNumber){
        if(checkGuest(email) != null || checkGuest("+", "4", phoneNumber) != null){
            return -1;
        }
        if(guestsList.size() == maxGuests){
            waitingList.add(new Guest(lastName, firstName, email, phoneNumber));
            return waitingList.size();
        }
        guestsList.add(new Guest(lastName, firstName, email, phoneNumber));
        return 0;
    }
    public void printAddResult(String lastName, String firstName, String email, String phoneNumber){
        int addResult = add(lastName, firstName, email, phoneNumber);
        if(addResult == -1){
            System.out.println("Persoana: [" + lastName + " " + firstName + "] este deja inscrisa la eveniment");
        }else if(addResult == 0){
            System.out.println("[" + lastName + " " + firstName + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
        }else{
            System.out.println("[" + lastName + " " + firstName + "] Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine \"" + addResult + "\". Te vom notifica daca un loc devine disponibil");
        }
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
    }
    public void update(String updateField, Guest guest){
        Scanner sc = new Scanner(System.in);
        switch (updateField){
            case "lastName":
                System.out.println("Introduceti noul nume de familie");
                String newLastName = sc.nextLine();
                guest.setLastName(newLastName);
                break;
            case "firstName":
                System.out.println("Introduceti noul prenume");
                String newFirstName = sc.nextLine();
                guest.setFirstName(newFirstName);
                break;
            case "email":
                System.out.println("Introduceti noul email");
                String newEmail = sc.nextLine();
                guest.setEmail(newEmail);
                break;
            default:
                System.out.println("Introduceti noul numar de telefon");
                String newPhoneNumber = sc.nextLine();
                guest.setPhoneNumber(newPhoneNumber);
                break;
        }
    }
    public void printUpdateResult(int searchCriteria){
        Scanner sc = new Scanner(System.in);
        if(searchCriteria == 1){
            System.out.println("Introduceti numele de familie");
            String lastName = sc.nextLine();
            System.out.println("Introduceti prenumele");
            String firstName = sc.nextLine();
            if(checkGuestList(lastName, firstName) != 0){
                update(AuxMethods.updateField(sc), checkGuest(lastName, firstName));
                System.out.println("Comanda a fost realizata cu succes");
            }else{
                System.out.println("Persoana nu este inscrisa la eveniment");
            }
        }else if(searchCriteria == 2){
            System.out.println("Introduceti emailul");
            String email = sc.nextLine();
            if(checkGuestList(email) != 0){
                update(AuxMethods.updateField(sc), checkGuest(email));
                System.out.println("Comanda a fost realizata cu succes");
            }else{
                System.out.println("Persoana nu este inscrisa la eveniment");
            }
        }else{
            System.out.println("Introduceti numarul de telefon");
            String phoneNumber = sc.nextLine();
            if(checkGuestList("+", "4", phoneNumber) != 0){
                update(AuxMethods.updateField(sc), checkGuest("+", "4", phoneNumber));
                System.out.println("Comanda a fost realizata cu succes");
            }else{
                System.out.println("Persoana nu este inscrisa la eveniment");
            }
        }
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
    }
    public boolean remove(String lastName, String firstName){
        if(checkGuestList(lastName, firstName) == 2){
            waitingList.remove(checkGuest(lastName, firstName));
            return true;
        }
        if(checkGuestList(lastName, firstName) == 1){
            guestsList.remove(checkGuest(lastName, firstName));
            if(guestsList.size() == maxGuests - 1 && waitingList.size() > 0){
                guestsList.add(waitingList.get(0));
                waitingList.remove(0);
            }
            return true;
        }
        return false;
    }
    public void printRemoveResult(int searchCriteria){
        Scanner sc = new Scanner(System.in);
        if(searchCriteria == 1){
            System.out.println("Introduceti numele de familie");
            String lastName = sc.nextLine();
            System.out.println("Introduceti prenumele");
            String firstName = sc.nextLine();
            if(checkGuestList(lastName, firstName) == 2 && remove(lastName, firstName)){
                System.out.println("Stergerea persoanei de pe lista de asteptare s-a realizat cu succes.");
            }else if(checkGuestList(lastName, firstName) == 1 && guestsList.size() < maxGuests && remove(lastName, firstName)){
                System.out.println("Stergerea persoanei de pe lilsta de participanti s-a realizat cu succes.");
            }else if(checkGuestList(lastName, firstName) == 1 && guestsList.size() == maxGuests && remove(lastName, firstName)){
                System.out.println("Stergerea persoanei de pe lilsta de participanti s-a realizat cu succes.");
                if(guestsList.size() == maxGuests){
                    System.out.println("Felicitari: [" + guestsList.get(maxGuests - 1).getLastName() + " " + guestsList.get(maxGuests - 1).getFirstName() + "]! Locul tau la eveniment este confirmat. Te asteptam!");
                }
            }else if(!remove(lastName, firstName)){
                System.out.println("Persoana cautata nu se afla pe liste");
            }
        }else if(searchCriteria == 2){
            System.out.println("Introduceti emailul");
            String email = sc.nextLine();
            if(checkGuestList(email) == 2 && remove(email)){
                System.out.println("Stergerea persoanei de pe lista de asteptare s-a realizat cu succes.");
            }else if(checkGuestList(email) == 1 && guestsList.size() < maxGuests && remove(email)){
                System.out.println("Stergerea persoanei de pe lilsta de participanti s-a realizat cu succes.");
            }else if(checkGuestList(email) == 1 && guestsList.size() == maxGuests && remove(email)){
                System.out.println("Stergerea persoanei de pe lilsta de participanti s-a realizat cu succes.");
                if(guestsList.size() == maxGuests){
                    System.out.println("Felicitari: [" + guestsList.get(maxGuests - 1).getLastName() + " " + guestsList.get(maxGuests - 1).getFirstName() + "]! Locul tau la eveniment este confirmat. Te asteptam!");
                }
            }else if(!remove(email)){
                System.out.println("Persoana cautata nu se afla pe liste");
            }
        }else{
            System.out.println("Introduceti numarul de telefon");
            String phoneNumber = sc.nextLine();
            if(checkGuestList("+", "4", phoneNumber) == 2 && remove("+", "4", phoneNumber)){
                System.out.println("Stergerea persoanei de pe lista de asteptare s-a realizat cu succes.");
            }else if(checkGuestList("+", "4", phoneNumber) == 1 && guestsList.size() < maxGuests && remove("+", "4", phoneNumber)){
                System.out.println("Stergerea persoanei de pe lilsta de participanti s-a realizat cu succes.");
            }else if(checkGuestList("+", "4", phoneNumber) == 1 && guestsList.size() == maxGuests && remove("+", "4", phoneNumber)){
                System.out.println("Stergerea persoanei de pe lilsta de participanti s-a realizat cu succes.");
                if(guestsList.size() == maxGuests){
                    System.out.println("Felicitari: [" + guestsList.get(maxGuests - 1).getLastName() + " " + guestsList.get(maxGuests - 1).getFirstName() + "]! Locul tau la eveniment este confirmat. Te asteptam!");
                }
            }else if(!remove("+", "4", phoneNumber)){
                System.out.println("Persoana cautata nu se afla pe liste");
            }
        }
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
    }
    public boolean remove(String email){
        if(checkGuestList(email) == 2){
            waitingList.remove(checkGuest(email));
            return true;
        }
        if(checkGuestList(email) == 1){
            guestsList.remove(checkGuest(email));
            if(guestsList.size() == maxGuests - 1 && waitingList.size() > 0){
                guestsList.add(waitingList.get(0));
                waitingList.remove(0);
            }
            return true;
        }
        return false;
    }
    public boolean remove(String plus, String countryCode, String phoneNumber){
        if(checkGuestList("+", "4", phoneNumber) == 2){
            waitingList.remove(checkGuest("+", "4", phoneNumber));
            return true;
        }
        if(checkGuestList("+", "4", phoneNumber) == 1){
            guestsList.remove(checkGuest("+", "4", phoneNumber));
            if(guestsList.size() == maxGuests - 1 && waitingList.size() > 0){
                guestsList.add(waitingList.get(0));
                waitingList.remove(0);
            }
            return true;
        }
        return false;
    }
    public int checkGuestList(String lastName, String firstName){
        for(Guest item : guestsList){
            if(item.getLastName().equals(lastName) && item.getFirstName().equals(firstName)){
                return 1;
            }
        }
        for(Guest item : waitingList){
            if(item.getLastName().equals(lastName) && item.getFirstName().equals(firstName)){
                return 2;
            }
        }
        return 0;
    }
    public int checkGuestList(String email){
        for(Guest item : guestsList){
            if(item.getEmail().equals(email)){
                return 1;
            }
        }
        for(Guest item : waitingList){
            if(item.getEmail().equals(email)){
                return 2;
            }
        }
        return 0;
    }
    public int checkGuestList(String plus, String countryCode, String phoneNumber){
        for(Guest item : guestsList){
            if(item.getPhoneNumber().equals(phoneNumber)){
                return 1;
            }
        }
        for(Guest item : waitingList){
            if(item.getPhoneNumber().equals(phoneNumber)){
                return 2;
            }
        }
        return 0;
    }
    public Guest checkGuest(String lastName, String firstName){
        for(Guest item : guestsList){
            if(item.getLastName().equals(lastName) && item.getFirstName().equals(firstName)){
                return item;
            }
        }
        for(Guest item : waitingList){
            if(item.getLastName().equals(lastName) && item.getFirstName().equals(firstName)){
                return item;
            }
        }
        return null;
    }
    public Guest checkGuest(String email){
        for(Guest item : guestsList){
            if(item.getEmail().equals(email)){
                return item;
            }
        }
        for(Guest item : waitingList){
            if(item.getEmail().equals(email)){
                return item;
            }
        }
        return null;
    }
    public Guest checkGuest(String plus, String countryCode, String phoneNumber){
        for(Guest item : guestsList){
            if(item.getPhoneNumber().equals(phoneNumber)){
                return item;
            }
        }
        for(Guest item : waitingList){
            if(item.getPhoneNumber().equals(phoneNumber)){
                return item;
            }
        }
        return null;
    }
    public void printCheckResult(int searchCriteria){
        Scanner sc = new Scanner(System.in);
        if(searchCriteria == 1){
            System.out.println("Introduceti numele de familie");
            String nume = sc.nextLine();
            System.out.println("Introduceti prenumele");
            String prenume = sc.nextLine();
            if(checkGuest(nume, prenume) == null){
                System.out.println("Persoana nu este inscris/a la acest eveniment!");
            }else{
                System.out.println("Persoana: " + checkGuest(nume, prenume) + " este inscrisa la eveniment");
            }
        }else if(searchCriteria == 2){
            System.out.println("Introduceti emailul");
            String email = sc.nextLine();
            if(checkGuest(email) == null){
                System.out.println("Persoana nu este inscris/a la acest eveniment!");
            }else{
                System.out.println("Persoana: " + checkGuest(email) + " este inscrisa la eveniment");
            }
        }else{
            System.out.println("Introduceti numarul de telefon");
            String telefon = sc.nextLine();
            if(checkGuest("+", "4", telefon) == null){
                System.out.println("Persoana nu este inscris/a la acest eveniment!");
            }else{
                System.out.println("Persoana: " + checkGuest("+", "4", telefon) + " este inscrisa la eveniment");
            }
        }
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
    }
    public void printGuestList(){
        if(guestsList.size() == 0){
            System.out.println("Lista de participanti este goala…");
            System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
            return;
        }
        for(Guest item : guestsList){
            System.out.println(guestsList.indexOf(item) + 1 + ". " + item);
        }
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
    }
    public void printWaitList(){
        if(waitingList.size() == 0){
            System.out.println("Lista de asteptare este goala…");
            System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
            return;
        }
        for(Guest item : waitingList){
            System.out.println(waitingList.indexOf(item) + 1 + ". " + item);
        }
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
    }
    public void available(){
        System.out.println("Numarul de locuri ramase: " + (maxGuests + -guestsList.size()));
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
    }
    public void guestsNo(){
        System.out.println("Numarul de participanti: " + guestsList.size());
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
    }
    public void waitListNo(){
        System.out.println("Dimensiunea listei de asteptare: " + waitingList.size());
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
    }
    public void subscribedGuests(){
        System.out.println("Numarul total de persoane: " + (guestsList.size() + waitingList.size()));
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
    }
    public void searchResult(Scanner sc){
        System.out.println("Introdu sirul de caracter pentru cautare");
        String keyword = sc.nextLine().toLowerCase();
        ArrayList<Guest> result = new ArrayList<>();
        for(Guest item : guestsList){
            String lowCaseLastName = item.getLastName().toLowerCase();
            String lowCaseFirstName = item.getFirstName().toLowerCase();
            String lowCaseEmail = item.getEmail().toLowerCase();
            if(lowCaseEmail.contains(keyword) || lowCaseLastName.contains(keyword) || lowCaseFirstName.contains(keyword)){
                result.add(item);
            }
        }
        for(Guest item : waitingList){
            String lowCaseLastName = item.getLastName().toLowerCase();
            String lowCaseFirstName = item.getFirstName().toLowerCase();
            String lowCaseEmail = item.getEmail().toLowerCase();
            if(lowCaseEmail.contains(keyword) || lowCaseLastName.contains(keyword) || lowCaseFirstName.contains(keyword)){
                result.add(item);
            }
        }
        for(Guest item : result){
            System.out.println(item);
        }
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
    }
}

