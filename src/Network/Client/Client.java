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

public class Client{
    private Socket socket;
    private  static final int  PORT = 1235;
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
            output = new ObjectOutputStream(output);
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
                Info info = (Info) reader.readObject();
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
                    Info myInfo = new Info();
                    myInfo.setFileByteCode(Files.readAllBytes(new File("D:/Music/The Neighbourhood - Sadderdaze.mp3").toPath()));
                    writer.writeObject(myInfo);
                    writer.flush();
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


}
