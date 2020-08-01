import java.io.Serializable;

public class Message implements Serializable {

    private String name;
    private String dateOfBirth;
    private String maritalStatus;

    public Message(String name, String dateOfBirth, String maritalStatus) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nDateOfBirth: " + dateOfBirth + "\nMarital Status: " + maritalStatus;
    }
}
