package Network.Server;

import Lists.SharedPlaylist;
import Network.Linker;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler implements Runnable {
    private InputStream input;
    private OutputStream output;
    private Socket clientSocket;
    private ArrayList<String> friendsIPList;
    private ArrayList<String> sharedPlaylist;
    private String currentSongName;
    private String message;
    private String request;
    private String usernameOfFriend;
    private Linker linker;
    private ClientHandler clientHandlerOfFriend;
    private byte[] mp3;
    public ClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
        linker=Server.getInstance();
        try {
            this.output=clientSocket.getOutputStream();
            this.input=clientSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        }

    @Override
    public void run() {
        while(true) {
            try {
                getFriendsIPList();
                getSharedPlaylist();
                getCurrentSongName();
                getMP3();
                ObjectInputStream reader = new ObjectInputStream(input);
                friendsIPList = (ArrayList<String>) reader.readObject();
                sharedPlaylist = (ArrayList<String>) reader.readObject();
                currentSongName = (String) reader.readObject();
                mp3=(byte[]) reader.readObject();
                message = (String) reader.readObject();
           //     System.out.println("jjjjj");
                StringTokenizer stringTokenizer=new StringTokenizer(message,"/");
                request=stringTokenizer.nextToken();
                usernameOfFriend=stringTokenizer.nextToken();
                for (String username: linker.getUsernamesAndClientHandlers().keySet()) {
                    if(username.equals(usernameOfFriend)){
                        clientHandlerOfFriend=linker.getUsernamesAndClientHandlers().get(username);
                        break;
                    }
                }
                switch (request) {
                    case "sharedPlaylist":
                        sendSharedPlaylist();
                        break;
                    case "currentSong-name":
                        sendCurrentSongName();
                        break;
                    case "currentSong":
                        sendMP3();
                        break;
                    case "FriendIPList":
                       // System.out.println("hiiiii");
                            sendFriendIPList();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }



    private ArrayList<String> getFriendsIPList()
    {

        return friendsIPList;
    }
    public ArrayList<String> getSharedPlaylist()
    {

        return sharedPlaylist;
    }
    public String getCurrentSongName()
    {

        return currentSongName;
    }
    public byte[] getMP3(){

        return mp3;
    }
    public void sendSharedPlaylist()
    {
        try {
            ObjectOutputStream writer= new ObjectOutputStream(output);
            int tag=1;
            writer.writeObject(tag);
           writer.writeObject(clientHandlerOfFriend.getSharedPlaylist());
           writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCurrentSongName(){
        try {
            ObjectOutputStream writer =new ObjectOutputStream(output);
            int tag=2;
            writer.writeObject(tag);
            writer.writeObject(clientHandlerOfFriend.getCurrentSongName());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMP3() {
        try {
            ObjectOutputStream writer=new ObjectOutputStream(output);
            int tag=3;
            writer.writeObject(tag);
            writer.writeObject(clientHandlerOfFriend.getMP3());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendFriendIPList(){
        try {
            ObjectOutputStream writer=new ObjectOutputStream(output);
            int tag=4;
            writer.writeObject(tag);
            writer.writeObject(clientHandlerOfFriend.getFriendsIPList());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public OutputStream getOutput() {
        return output;
    }

    public InputStream getInput() {
        return input;
    }
}
