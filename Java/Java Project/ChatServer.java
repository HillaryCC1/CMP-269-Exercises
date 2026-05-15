import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    private static final int PORT = 59001;
    private static Set<PrintWriter> clients = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Chat server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected.");

                ClientHandler handler = new ClientHandler(socket);
                new Thread(handler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {

        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("SERVER: Enter your name:");

                username = in.readLine();

                synchronized (clients) {
                    clients.add(out);
                }

                broadcast(username + " has joined the chat.");

                String message;

                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("QUIT")) {
                        break;
                    }

                    broadcast(username + ": " + message);
                }

            } catch (IOException e) {
                System.out.println("Client disconnected.");
            } finally {
                if (out != null) {
                    clients.remove(out);
                }

                if (username != null) {
                    broadcast(username + " left the chat.");
                }

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message) {
            synchronized (clients) {
                for (PrintWriter client : clients) {
                    client.println(message);
                }
            }

            System.out.println(message);
        }
    }
}