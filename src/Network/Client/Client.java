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
    private ArrayList<String> friendsIPAdresses;
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


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clientReceiver()
    {
        while (true)
        {
            try {
                int arg = input.read();
                inputType(arg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void inputType(int arg)
    {
        switch (arg)
        {
            case 1:
                break;
            case 2:
//                Info info = (Info) reader.readObject();
                break;
            case 3:
                break;
        }
    }

}
