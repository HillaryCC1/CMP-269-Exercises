import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {
    public static void main(String[] args) {
        final String host = "localhost";
        final int port = 59001;

        try (
                Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected to chat server.");

            String serverPrompt = in.readLine();
            if (serverPrompt != null) {
                System.out.println(serverPrompt);
            }

            System.out.print("Enter your name: ");
            String username = scanner.nextLine();
            out.println(username);

            Thread listenerThread = new Thread(() -> {
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });

            listenerThread.setDaemon(true);
            listenerThread.start();

            while (true) {
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("QUIT")) {
                    out.println("QUIT");
                    break;
                }

                out.println(message);
            }

            System.out.println("Closing client...");

        } catch (IOException e) {
            System.out.println("Could not connect to server on localhost:59001");
            e.printStackTrace();
        }
    }
}