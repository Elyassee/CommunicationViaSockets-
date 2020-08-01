import java.net.*;
import java.io.*;

public class TCPServer {

    public static void main(String args[]) {
        try {
            System.out.println("The Server is running");
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
                System.out.println("New Connection");
            }
        } catch (IOException e) {
            System.out.println(" Listen :" + e.getMessage());
        }
    }
}


class Connection extends Thread {
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket clientSocket;

    Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            this.start();
        } catch (IOException e) {
            System.out.println(" Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            City city = new City("Frankfurt");

            while (true) {
                int input = (int) in.readObject();
                if (input == 0) {
                    break;
                }

                if (input == 1) {
                    addInhabitant(city);
                } else if (input == 2) {
                    sendInhabitantNames(city);
                }
            }
            clientSocket.close();
        } catch (EOFException e) {
            System.out.println(" EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println(" IO:" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(" CNF:" + e.getMessage());
        }
    }

    private void sendInhabitantNames(City city) throws IOException {
        out.writeObject(city.listOfInhabitants());
    }

    private void addInhabitant(City city) throws ClassNotFoundException, IOException {
        Message message = readMessage();
        String name = message.getName();
        String dateOfBirth = message.getDateOfBirth();
        String maritalStatus = message.getMaritalStatus();
        System.out.println("Server Received Message: " + message.toString());
        boolean result = city.addInhabitant(name, dateOfBirth, maritalStatus);
        System.out.println("Server Sent Response: " + result);
        out.writeObject(result);
    }

    private Message readMessage() throws ClassNotFoundException, IOException {
        return (Message) in.readObject();
    }
}