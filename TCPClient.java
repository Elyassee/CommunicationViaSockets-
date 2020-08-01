import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class TCPClient {

	public static void main(String args[]) {
        // args[0]: Message
        // args[1]: Server
        try {
            int serverPort = 7896;
			Socket socket = new Socket("localhost", serverPort);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
				System.out.println("**** For adding Inhabitant press '1' ****\n**** For getting names of Inhabitants " +
						"press '2' ****\n**** For exiting press '0' ****");
				Scanner scanner = new Scanner(System.in);
				int input = scanner.nextInt();
				if (input == 0) {
					break;
				}
				out.writeObject(input);

				handleServerResponse(in, out, scanner, input);
			}

            socket.close();
        } catch (UnknownHostException e) {
            System.out.println(" UH:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println(" EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println(" IO:" + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(" ITO:" + e.getMessage());
        } catch (ClassNotFoundException e) {
			System.out.println(" CNF:" + e.getMessage());
		}
    }// main

	private static void handleServerResponse(ObjectInputStream in, ObjectOutputStream out, Scanner scanner, int input) throws IOException, InterruptedException, ClassNotFoundException {
		if (input == 1) {
			handleInhabitantAddition(in, out, scanner);

		} else if (input == 2) {
			handleNamesOfInhabitants(in);
			System.out.println("\n");
		}
	}

	private static void handleNamesOfInhabitants(ObjectInputStream in) throws ClassNotFoundException, IOException {
		List<Inhabitant> inhabitants = readListOfInhabitants(in);
		System.out.println("\n*** The following is the list of inhabitants ***");
		for (Inhabitant inhabitant: inhabitants) {
			System.out.println("Name: " + inhabitant.getName());
		}
	}

	private static void handleInhabitantAddition(ObjectInputStream in, ObjectOutputStream out, Scanner scanner) throws IOException, InterruptedException, ClassNotFoundException {
		System.out.println("Name: ");
		String name = scanner.next();
		System.out.println("Date of birth: ");
		String date = scanner.next();
		System.out.println("MaritalStatus: ");
		String maritalStatus = scanner.next();

		sendDataToServer(out, name , date, maritalStatus);
		boolean acknowledgment = (boolean) in.readObject();
		System.out.println("Client Received Response: " + acknowledgment);
	}

	@SuppressWarnings("unchecked")
	public static List<Inhabitant> readListOfInhabitants(ObjectInputStream dataInputStream) throws ClassNotFoundException, IOException {
    	return (List<Inhabitant>) dataInputStream.readObject();
	}

	public static void sendDataToServer(ObjectOutputStream out, String name, String date, String maritalStatus) throws UnknownHostException, IOException, InterruptedException {
        Message message = new Message(name, date, maritalStatus);
        System.out.println("**** Client Sent Message to Server ****\n" + message.toString());
        out.writeObject(message);
    }
}// class
