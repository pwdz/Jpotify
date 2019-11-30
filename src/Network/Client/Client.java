package Network.Client;

import ClientPackage.User;
import Network.GetCurrentSongFromSongPlayerForClient;
import Network.GiveCurrentSongToClient;
import Network.Server.Info;
import PlayerPackage.SongPlayer;
import jdk.jfr.events.SocketReadEvent;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Client{
    private Socket socket;
    private  static final int  PORT = 1500;
    private static final String IP = "127.0.0.1";
    private InputStream input;
    private OutputStream output;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private User user;
    private ArrayList<String> friendsUsernames;
    public Client(User user)
    {
        this.user = user;
        try {
            // client must be connected to the server: IP and PORT of the server are required
            socket = new Socket(IP,PORT);
            input = socket.getInputStream();
            output = socket.getOutputStream();
            reader = new ObjectInputStream(input);
            writer = new ObjectOutputStream(output);
            friendsUsernames = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("Duplicates")
    public void clientReceiver()
    {
        while (true)
        {
            try {
                int arg = input.read();
                System.out.println("input.read()::received arg is:"+arg);
                Info info = (Info) reader.readObject();
                System.out.println("Info received!");
                receiverInputType(arg,info);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void receiverInputType(int arg,Info info)
    {
        switch (arg)
        {
            case 0://new user joined
                friendsUsernames.add(info.getSourceUserName());
                break;
            case 1://request has come for the current song!
                try {
                    System.out.println("request for song is here!");
                    Info myInfo = new Info();

                    System.out.println("HDD1");
                    myInfo.setFileByteCode(Files.readAllBytes(new File("D:/Music/The Neighbourhood - Sadderdaze.mp3").toPath()));

                    System.out.println("HDD2");
                    System.out.println(myInfo);
                    output.write(11);
                    writer.writeObject(myInfo);

                    System.out.println("HDD3");
                    writer.flush();
                    System.out.println("response is sent!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 11:
//                Info infoReceived = (Info) reader.readObject();
                System.out.println("response for song in here");
                FileOutputStream fileOutput = null;
                try {
                    fileOutput = new FileOutputStream("C:\\Users\\acer\\Desktop\\Jpotify\\Files\\" + "goody.mp3");

                    fileOutput.write(info.getFileByteCode());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                break;
            case 3:
                //current song must be updated!
                break;
        }
    }
    public void sendRequest(int arg,String targetUsername)
    {
        switch (arg)
        {
            case 0:
                try {
                    output.write(0);
                    output.flush();
                    Info info = new Info();
                    info.setSourceUserName(user.getUsername());
                    writer.writeObject(info);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    output.write(1);
                    output.flush();
                    Info info = new Info();
                    info.setTargetUserName(targetUsername);
                    info.setSourceUserName(user.getUsername());
//                    System.out.println(targetUsername);
//                    System.out.println(info.getTargetUserName());
                    writer.writeObject(info);
                    System.out.println("request for song in sent");

                    //u must receive song here!
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                //u will send a request and get it back here
                break;
            case 3:
                //u must update the current song of the specific user that is being played
                break;
        }
    }

}
