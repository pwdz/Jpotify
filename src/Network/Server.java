package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Linker,Runnable {
    private final int PORT=1234;
    private ServerSocket serverSocket;
    private ClientHandler clientHandler;
    private ArrayList<Socket> sockets;
    private ArrayList<ClientHandler> clientHandlers;
    public Server(){
        sockets=new ArrayList<>();
        clientHandlers=new ArrayList<>();
        try {
            serverSocket=new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void run() {
        while (true){
            Socket clientSocket=null;
            try {
                clientSocket=serverSocket.accept();
                sockets.add(clientSocket);
                clientHandler=new ClientHandler(clientSocket);
                clientHandlers.add(clientHandler);
                Thread thread=new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
