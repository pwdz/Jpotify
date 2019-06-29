package Network.Server;

import Network.Linker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server implements Linker,Runnable{
    private static Server ourInstance =null;
    private final int PORT=1235;
    private ServerSocket serverSocket;
    private ClientHandler clientHandler;
    private ArrayList<Socket> sockets;
    private ArrayList<ClientHandler> clientHandlers;
    private HashMap<String,ClientHandler> usernamesAndClientHandlers;
    private int handlerCounter;
    private Server() {
        sockets=new ArrayList<>();
        clientHandlers=new ArrayList<>();
        usernamesAndClientHandlers=new HashMap<>();
        handlerCounter=0;
        try {
            serverSocket=new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("server created");
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
                usernamesAndClientHandlers.put("user"+handlerCounter,clientHandler);
                handlerCounter++;
                Thread thread=new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



    public static Server getInstance() {
        if(ourInstance==null) {
            ourInstance = new Server();
            System.out.println("singletone created");
        }

        return ourInstance;
    }


    @Override
    public HashMap<String, ClientHandler> getUsernamesAndClientHandlers() {
        return usernamesAndClientHandlers;
    }

//     public static void main(String[] args) {
//         Server server=  getInstance();
//         Thread thread=new Thread(server);
//         thread.start();
//     }
}



