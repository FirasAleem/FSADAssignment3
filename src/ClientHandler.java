import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    //declare variables
    //Constructor
    Socket clientSocket;
    int clientId;
    Database db;

    public ClientHandler (Socket socket, int clientId, Database db) {
        clientSocket = socket;
        this.clientId = clientId;
        this.db = db;
    }

    public void run() {
        try {
//              Create I/O streams to read/write data, PrintWriter and BufferedReader
            System.out.println("ClientHandler started...");
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
            String clientMessage;
//              Receive messages from the client and send replies, until the user types "stop"
            while(!(clientMessage = inFromClient.readLine()).equals("stop")) {
                System.out.println("Client sent the artist name " + clientMessage);
//                  Request the number of titles from the db
                int titlesNum = db.getTitles(clientMessage);
//                  Send reply to Client:
                outToClient.println("Number of titles: " + titlesNum + " records found");
//
            }
            System.out.println("Client " + clientId + " has disconnected");
            outToClient.println("Connection closed, Goodbye!");
//              Close I/O streams and socket*/
            inFromClient.close();
            outToClient.close();
            clientSocket.close();
        }catch (Exception e){}
    }
}
