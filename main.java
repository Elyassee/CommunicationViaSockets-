public class main {

    public static void main(String[] args) {

        City city1 = new City("Frankfurt");
        int totalOfUnmarried=0;

        city1.addInhabitant("Adam Smith", "12.05.1959", "Married");
        city1.addInhabitant("Karl Marx", "12.05.1955", "Married");
        city1.addInhabitant("Bill Gates", "12.05.1957", "Single");
        city1.addInhabitant("Steve Jobs", "12.05.1953", "Single");
        city1.addInhabitant("Elon Musk", "12.05.1957", "Married");
        city1.addInhabitant("Marie Curie", "12.05.1952", "Married");
        city1.addInhabitant("Ibn Khaldoun", "12.05.1951", "Single");

        System.out.println("City Name: " + city1.getName() + "\n");
        System.out.println("List of all inhabitants" + city1.searchForInhabitant("Karl Marx").getMaritalStatus()+"\n");
        city1.getInhabitants();
        for(Inhabitant inhabitant: city1.listOfInhabitants()){
            if(inhabitant.getMaritalStatus().equals("Single")){
                totalOfUnmarried++;
            }
        }
        System.out.println("The total of Unmarried inhabitants is: " + totalOfUnmarried);



    }
}
