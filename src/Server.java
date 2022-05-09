import java.io.*;
import java.net.*;

public class Server {

    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Credentials.PORT);
        System.out.println("Server is running");
        Database db = new Database();

        if (db.establishDBConnection()) {
            System.out.println("Server is now connected to DB");
            int clientID = 0;
            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientID++;
                System.out.println("Client " + clientID + " connected with IP " + clientSocket.getInetAddress().getHostAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket, clientID, db);
                new Thread(clientHandler).start();
            }
        } else {
            System.out.println("DB connection fail, stopping.");
        }
    }
}

