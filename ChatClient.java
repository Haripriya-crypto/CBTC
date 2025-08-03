import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("🟢 Connected to Chat Server");

        BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        // Thread to receive messages
        new Thread(() -> {
            String msg;
            try {
                while ((msg = serverIn.readLine()) != null) {
                    System.out.println("💬 " + msg);
                }
            } catch (IOException e) {
                System.out.println("❌ Disconnected from server.");
            }
        }).start();

        // Main thread to send messages
        System.out.print("Enter your name: ");
        String name = userInput.readLine();

        String msg;
        while ((msg = userInput.readLine()) != null) {
            serverOut.println(name + ": " + msg);
        }

        socket.close();
    }
}