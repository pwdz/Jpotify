package Network;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private ClientReceiver receiver;
    private ClientSender sender;
    InputStream inputStream;
    OutputStream outputStream;
    Socket clientSocket;
    private Linker linker;
    public ClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
        try {
            this.outputStream=clientSocket.getOutputStream();
            this.inputStream=clientSocket.getInputStream();
            sender=new ClientSender(outputStream);
            receiver=new ClientReceiver(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        }

    @Override
    public void run() {

    }
}
