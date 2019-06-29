package Network.Client;

import Lists.SharedPlaylist;
import Network.Server.Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
    private String IP;
    private String message;
    private final int PORT = 1235;
    private ArrayList<String> friendsIpAddresses;
    private ArrayList<String> sharedPlaylist;
    private Socket clientSocket;
    private String currentSongName;
    private byte[] mp3;
    private InputStream input;
    private OutputStream output;
    private ArrayList<String> sharedPlaylistOfFriend;
    private String currentSongNameOfFriend;
    private ArrayList<String> friendsIpAddressesOfFriend;
    private byte[] mp3OfFriend;
    boolean flag=false;
//    Timer timer;
//    TimerTask timerTask;
    public Client(String IP, ArrayList<String> friendsIpAddresses) {
        this.IP = IP;
   //     timer=new Timer();
        System.out.println("gf");
        this.friendsIpAddresses = friendsIpAddresses;
        try {
            clientSocket = new Socket(IP, PORT);
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();

            Thread clientSender =new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {

                            while(flag) {
                                ObjectOutputStream objectOutputStream=new ObjectOutputStream(output);
                                objectOutputStream.writeObject(friendsIpAddresses);
                                objectOutputStream.writeObject(sharedPlaylist);
                                objectOutputStream.writeObject(currentSongName);
                               objectOutputStream.writeObject(mp3);
                                objectOutputStream.writeObject(message);
                                flag=false;
                            }
                        //    System.out.println("hhhhh");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            Thread clientReceiver=new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            ObjectInputStream objectInputStream=new ObjectInputStream(input);
                            int tag=(int)objectInputStream.readObject();
                            switch(tag){
                                case 1:
                                    sharedPlaylistOfFriend=(ArrayList<String>) objectInputStream.readObject();
                                    break;
                                case 2:
                                    currentSongNameOfFriend=(String) objectInputStream.readObject();
                                    break;
                                case 3:
                                    mp3OfFriend=(byte[]) objectInputStream.readObject();
                                    break;
                                case 4:
                                    friendsIpAddressesOfFriend=(ArrayList<String>) objectInputStream.readObject();
                                    System.out.println("receiver : "+ friendsIpAddressesOfFriend.get(0));

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            clientSender.start();
            clientReceiver.start();








//            timerTask=new TimerTask() {
//                @Override
//                public void run() {
//                    clientSender.sendCurrentSong();
//                }
//            };

        } catch (IOException e) {

        }

    }

    public void setMessage(String message){
        this.message=message;
        flag=true;
    }


    public String getIP() {
        return IP;
    }

    public ArrayList<String> getFriendsIpAddresses()
    {
        return friendsIpAddresses;
    }

//    public static void main(String[] args) {
//        ArrayList<String> iplist1=new ArrayList<>();
//        iplist1.add("127.0.0.1");
//        ArrayList<String> iplist2=new ArrayList<>();
//        iplist2.add("sjhggv");
////        ArrayList<String> iplist3=new ArrayList<>();
////        iplist3.add("1270866");
//        Client client1=new Client("127.0.0.1",iplist1);
//        Client client2=new Client("127.0.0.1",iplist2);
//        client1.setMessage("FriendIPList/user1");
//        client2.setMessage("FriendIPList/user0");
//        //Client client3=new Client("127.0.0.1",iplist3);
//
//    }

}
