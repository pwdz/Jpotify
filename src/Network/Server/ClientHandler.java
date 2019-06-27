package Network.Server;

import Lists.SharedPlaylist;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    InputStream input;
    OutputStream output;
    Socket clientSocket;
    ArrayList<String> friendsIPList;
    SharedPlaylist sharedPlaylist;
    public ClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
        try {
            this.output=clientSocket.getOutputStream();
            this.input=clientSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        }

    @Override
    public void run() {
        getFriendsIPList();

    }
    private void getFriendsIPList()
    {
        try {
            ObjectInputStream reader= new ObjectInputStream(input);
            friendsIPList = (ArrayList<String>) reader.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void getSharedPlaylist()//needs editing
    {
        try {
            ObjectInputStream reader = new ObjectInputStream(input);
            sharedPlaylist = (SharedPlaylist) reader.readObject();
            System.out.println("SharedPlaylist is received by server");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void sendSharedPlaylist()
    {
        try {
            ObjectOutputStream writer= new ObjectOutputStream(output);
//            writer.writeObject(sharedPlaylist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
