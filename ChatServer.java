import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<ClientHandler> clientHandlers = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Chat Server started...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Broadcast message to all clients
    public static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler != sender) {
                clientHandler.sendMessage(message);
            }
        }
    }

    // Remove a client handler when a client disconnects
    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }

    // ClientHandler class to handle each client
    private static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                // Get client's name
                out.println("Enter your name:");
                clientName = in.readLine();
                System.out.println(clientName + " joined the chat.");
                broadcast(clientName + " has joined the chat.", this);

                String message;
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("/sendfile")) {
                        receiveFile();
                    } else if (message.equals("/exit")) {
                        break;
                    } else {
                        broadcast(clientName + ": " + message, this);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Clean up
                try {
                    socket.close();
                    removeClient(this);
                    broadcast(clientName + " has left the chat.", this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Send message to client
        public void sendMessage(String message) {
            out.println(message);
        }

        // Handle file transfer
        private void receiveFile() {
            try {
                out.println("Sending file...");
                // Receive file (simple demo, not secure or robust)
                String fileName = in.readLine();
                long fileSize = Long.parseLong(in.readLine());
                byte[] buffer = new byte[4096];
                FileOutputStream fileOut = new FileOutputStream("received_" + fileName);
                InputStream inputStream = socket.getInputStream();
                int bytesRead;
                while (fileSize > 0 && (bytesRead = inputStream.read(buffer, 0, Math.min(buffer.length, (int) fileSize))) != -1) {
                    fileOut.write(buffer, 0, bytesRead);
                    fileSize -= bytesRead;
                }
                fileOut.close();
                System.out.println("File received: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}