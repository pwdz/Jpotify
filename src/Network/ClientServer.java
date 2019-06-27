package Network;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientServer {
    private String IP;
    private final int PORT = 1234;
    private ArrayList<String> friendsIpAddresses;
    private Socket clientSocket;
    private ClientSender clientSender;
    private ClientReceiver clientReceiver;
    InputStream input;
    OutputStream output;

    public ClientServer(String IP,ArrayList<String> friendsIpAddresses ) {
        this.IP = IP;
        this.friendsIpAddresses = friendsIpAddresses;

            try {
                clientSocket = new Socket(IP, PORT);
              input = new DataInputStream(clientSocket.getInputStream());
              output = new DataOutputStream(clientSocket.getOutputStream());
                clientSender=new ClientSender(output);
                clientReceiver=new ClientReceiver(input);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

}