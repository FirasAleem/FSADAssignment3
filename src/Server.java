import java.io.*;
import java.net.*;

public class Server {

    public static void main(String args[]) throws IOException {
//      Open the server socket
        ServerSocket serverSocket = new ServerSocket(Credentials.PORT);
        System.out.println("Server is running" );

//          Create a Database object and check the connection with establishDBConnection():
        Database db = new Database();
//          If the db connection fails, print:
//                        else, print:
        if (db.establishDBConnection()){
            System.out.println("Server is now connected to DB");
            int clientID = 0;
            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientID++;
                System.out.println("Client " + clientID + " connected with IP " + clientSocket.getInetAddress().getHostAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket, clientID, db);
                new Thread(clientHandler).start();
            }
        }else {
            System.out.println("DB connection fail, stopping.");
        }
//
//
//              Continuously listen for client requests
//                  Accept new connection and create the client socket
//                  Increment clientId. The clientId is not reassigned once used.
//                  Display clientId and IP address:
//                  System.out.println("Client " + clientId + " connected with IP " + clientSocket.getInetAddress().getHostAddress());
//                  Create a new client handler object and start the thread*/
    }
}

