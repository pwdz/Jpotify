package Network.Client;

import ClientPackage.User;
import Lists.SharedPlaylist;
import Network.GetCurrentSongFromSongPlayerForClient;
import Network.GiveCurrentSongToClient;
import Network.Server.Server;
import PlayerPackage.SongPlayer;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Client implements GiveCurrentSongToClient {
    private User user;
    private String IP;
    private String message;
    private final int PORT = 1235;
    private ArrayList<String> friendsIpAddresses = null;
    private ArrayList<String> sharedPlaylist;
    private Socket clientSocket;
    private String currentSongName;
    private String currentSongPath;
    private byte[] mp3;
    private InputStream input;
    private OutputStream output;
    private ArrayList<String> sharedPlaylistOfFriend;
    private String currentSongNameOfFriend;
    private ArrayList<String> friendsIpAddressesOfFriend;
    private byte[] mp3OfFriend;
    boolean flag = false;
    GetCurrentSongFromSongPlayerForClient getCurrentSongFromSongPlayerForClient;


    public Client(String IP, ArrayList<String> friendsIpAddresses) {
        this.IP = IP;
        user = new User("sbdols");


        this.friendsIpAddresses = friendsIpAddresses;
        try {
            clientSocket = new Socket(IP, PORT);
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();


//            timerTask=new TimerTask() {
//                @Override
//                public void run() {
//                    clientSender.sendCurrentSong();
//                }
//            };

        } catch (IOException e) {

        }

    }

    public void setMessage(String message) {
        this.message = message;
        System.out.println("hii");
        flag = true;
    }


    public String getIP() {
        return IP;
    }

    public ArrayList<String> getFriendsIpAddresses() {
        return friendsIpAddresses;
    }

//    public static void main(String[] args) {
//        ArrayList<String> iplist1 = new ArrayList<>();
//        iplist1.add("127.0.0.1");
//        ArrayList<String> iplist2 = new ArrayList<>();
//        iplist2.add("sjhggv");
//        ArrayList<String> iplist3 = new ArrayList<>();
//        iplist3.add("1270866");
//        Client client1 = new Client("127.0.0.1", iplist1);
//        Client client2 = new Client("127.0.0.1", iplist2);
//        client1.setMessage("FriendIPList/user1");
//        client2.setMessage("FriendIPList/user0");
//        //Client client3=new Client("127.0.0.1",iplist3);
//
//    }

    public User getUser() {
        return user;
    }

    public void setGetCurrentSongFromSongPlayerForClient(GetCurrentSongFromSongPlayerForClient linker) {
        System.out.println("deallllllllllllllllllllllllllllllllllllllllll");
        if (linker instanceof SongPlayer)
            System.out.println(":||||||||||||||||||||||");
        getCurrentSongFromSongPlayerForClient = linker;
    }

    @Override
    public void currentSongPath(String path) {
        currentSongPath = path;
        System.out.println("residddddddddddddddddddddddddd");
    }

    public void startSendingAndReceiving() {
        Thread clientSender = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hhhh");
                while (true) {
                    try {

                        while (flag) {
                            user.getLibrary().setSharedPlaylistSongs();
                            sharedPlaylist = user.getLibrary().getSharedPlaylistSongs();
                            getCurrentSongFromSongPlayerForClient.giveSongToClient();
                            File file = new File("C:\\Users\\acer\\Music\\01 Honey.mp3");
                            mp3 =  Files.readAllBytes(file.toPath());
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
                            objectOutputStream.writeObject(friendsIpAddresses);
                            objectOutputStream.writeObject(sharedPlaylist);
                            objectOutputStream.writeObject(currentSongName);
                            objectOutputStream.writeObject(mp3);
                            System.out.println(message);
                            objectOutputStream.writeObject(message);
                            flag = false;
                        }
                    } catch (IOException e) {

                    }
                }

            }
        });
        Thread clientReceiver = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(input);
                        int tag = (int) objectInputStream.readObject();
                        System.out.println("-_________________________________-");
                        switch (tag) {
                            case 1:
                                sharedPlaylistOfFriend = (ArrayList<String>) objectInputStream.readObject();
                                break;
                            case 2:
                                currentSongNameOfFriend = (String) objectInputStream.readObject();
                                break;
                            case 3:
                                mp3OfFriend = (byte[]) objectInputStream.readObject();
                                try(FileOutputStream fileOutputStream= new FileOutputStream("MP3.mp3")) {
                                    fileOutputStream.write(mp3OfFriend);
                                }
                                SongPlayer songPlayer=new SongPlayer();
                                songPlayer.setPath("./Mp3.mp3");
                                songPlayer.playTheSong();
                                break;
                            case 4:
                                friendsIpAddressesOfFriend = (ArrayList<String>) objectInputStream.readObject();
                                System.out.println("receiver : " + friendsIpAddressesOfFriend.get(0));

                        }
                    } catch (IOException e) {

                    } catch (ClassNotFoundException e) {

                    }

                }
            }
        });
        clientSender.start();
        clientReceiver.start();
    }
}
