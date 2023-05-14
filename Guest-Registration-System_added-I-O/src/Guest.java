import java.io.Serializable;

public class Guest implements Serializable {
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Guest(String lastName, String firstName, String email, String phoneNumber){
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString(){
        return "Nume: " + this.lastName + " " + this.firstName + ", Email: " + this.email + ", Telefon: " + this.phoneNumber;
    }

    public void lastNameLower(){
        this.lastName.toLowerCase();
    }
    public void firstNameLower(){
        this.firstName.toLowerCase();
    }
    public void emailLower(){
        this.email.toLowerCase();
    }

}
//