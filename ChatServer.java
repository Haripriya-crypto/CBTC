import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<PrintWriter> clientWriters = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("💬 Chat Server started on port 5000...");

        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("✅ Client connected: " + client.getInetAddress());
            new Thread(new ClientHandler(client)).start();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientWriters.add(out);
        }

        public void run() {
            String msg;
            try {
                while ((msg = in.readLine()) != null) {
                    System.out.println("📨 Received: " + msg);
                    for (PrintWriter writer : clientWriters) {
                        writer.println(msg);
                    }
                }
            } catch (IOException e) {
                System.out.println("❌ Connection lost.");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {}
                clientWriters.remove(out);
            }
        }
    }
}
