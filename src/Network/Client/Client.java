package Network.Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private String IP;
    private final int PORT = 1234;
    private Socket clientSocket;
    private ClientSender clientSender;
    private ClientReceiver clientReceiver;
    InputStream input;
    OutputStream output;

    public Client(String IP) {
        this.IP = IP;
        try {
            clientSocket = new Socket(IP, PORT);
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();

            clientSender = new ClientSender(output);
            clientReceiver = new ClientReceiver(input);

            new Thread(clientReceiver).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
