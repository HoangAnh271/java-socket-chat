package Ex1;

/* SERVER – may enhance to work for multiple clients */
import java.io.*;
import java.net.*;

public class Server {
  public static void main(String[] args) {
    try {
      // Create a server socket to listen for client requests
      int port = 1234;
      ServerSocket serverSocket = new ServerSocket(port);
      System.out.println("Server listening on port " + port + "..");

      while (true) {
        // Accept a client connection
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected from " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

        // Read client data
        while (true) {
          BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          String clientMessage = in.readLine();

          // Process client message and send corresponding response
          String serverResponse;
          PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
          if (clientMessage.startsWith("Hello, my name is")) {
            String name = clientMessage.substring(18);
            serverResponse = "Hello, " + name;
          } else if (clientMessage.equalsIgnoreCase("Goodbye")) {
            serverResponse = "Goodbye";
            out.println(serverResponse);
            clientSocket.close();
            break;
          } else {
            serverResponse = "I received this message from your part: " + clientMessage;
          }

          // Send response to client
          out.println(serverResponse);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
