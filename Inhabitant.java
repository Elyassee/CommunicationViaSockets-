import java.io.Serializable;

public class Inhabitant implements Serializable {


    private String name;
    private String dateOfBirth;
    private String maritalStatus;

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMaritalStatus(String maritalStatus) {
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

    public Inhabitant(String name, String dateOfBirth, String maritalStatus){

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
    }
}
