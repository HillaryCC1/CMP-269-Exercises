import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatApp extends Application {

    private TextArea chatArea;
    private TextField inputField;
    private Button sendButton;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    @Override
    public void start(Stage primaryStage) {
        TextInputDialog ipDialog = new TextInputDialog("localhost");
        ipDialog.setTitle("Server Connection");
        ipDialog.setHeaderText("Enter the server IP address");
        ipDialog.setContentText("IP:");

        String host = ipDialog.showAndWait().orElse("localhost");

        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("User Name");
        nameDialog.setHeaderText("Enter your user name");
        nameDialog.setContentText("Name:");

        String username = nameDialog.showAndWait().orElse("Guest");

        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        inputField = new TextField();
        inputField.setPromptText("Type your message here...");

        sendButton = new Button("Send");

        HBox bottomBox = new HBox(10, inputField, sendButton);
        bottomBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(chatArea);
        root.setBottom(bottomBox);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Lehman Chat Client");
        primaryStage.setScene(scene);
        primaryStage.show();

        connectToServer(host, 59001, username);

        sendButton.setOnAction(e -> sendMessage());
        inputField.setOnAction(e -> sendMessage());

        primaryStage.setOnCloseRequest(event -> {
            try {
                if (out != null) {
                    out.println("QUIT");
                }
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void connectToServer(String host, int port, String username) {
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String serverPrompt = in.readLine();
            if (serverPrompt != null) {
                appendMessage(serverPrompt);
            }

            out.println(username);
            appendMessage("Connected as " + username);

            Thread listenerThread = new Thread(() -> {
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        String message = line;
                        Platform.runLater(() -> chatArea.appendText(message + "\n"));
                    }
                } catch (IOException e) {
                    Platform.runLater(() -> chatArea.appendText("Disconnected from server.\n"));
                }
            });

            listenerThread.setDaemon(true);
            listenerThread.start();

        } catch (IOException e) {
            appendMessage("Failed to connect to server.");
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = inputField.getText().trim();

        if (message.isEmpty()) {
            return;
        }

        if (out != null) {
            out.println(message);
        }

        inputField.clear();
    }

    private void appendMessage(String message) {
        chatArea.appendText(message + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}