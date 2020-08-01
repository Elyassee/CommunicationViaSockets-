import java.util.ArrayList;
import java.util.List;

public class City {

    public void setName(String name) {
        this.name = name;
    }

    public void setInhabitants(List<Inhabitant> inhabitants) {
        this.inhabitants = inhabitants;
    }

    private String name;
    private List<Inhabitant> inhabitants = new ArrayList<Inhabitant>();


    public City(String name) {
        this.name = name;
    }

    public void getInhabitants() {
        for (Inhabitant inhabitant: inhabitants) {
            System.out.println(inhabitant.getName());
            System.out.println(inhabitant.getDateOfBirth());
            System.out.println(inhabitant.getMaritalStatus()+"\n");
        }

    }

    public String getName() {
        return name;
    }

    public Inhabitant searchForInhabitant(String name) {
        for (Inhabitant inhabitant: inhabitants) {
            if (inhabitant.getName().equals(name)) {
                return inhabitant;
            }
        }
        return null;
    }

    public boolean addInhabitant(String name, String dateOfBirth, String maritalStatus) {
        Inhabitant inhabitant = new Inhabitant(name, dateOfBirth, maritalStatus);
        return inhabitants.add(inhabitant);
    }

    public List<Inhabitant> listOfInhabitants(){
        return inhabitants;
    }

}