package Ex1;
/* CLIENT */

import java.io.*;
import java.net.*;

public class Client {
  public static void main(String[] args) {

    try {
      // Create a socket to connect to the server
      int port = 1234;
      String host = "localhost";
      Socket socket = new Socket(host, port);

      // Display information about the socket
      System.out.println("Connected to server at " + socket.getInetAddress() + ":" + socket.getPort());
      while (true) {
        // Ask the user to type a string to send to the server
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a message to send to the server: ");
        String message = userInput.readLine();
        // Send the message to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(message);
        // Receive the response from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String serverResponse = in.readLine();
        System.out.println("Server: " + serverResponse);
        if (serverResponse.equals("Goodbye")) {
          System.out.println("Closing the connection...");
          socket.close();
          break;
        }
      }
      // Close the socket
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
