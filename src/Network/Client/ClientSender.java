package Network.Client;

import Lists.SharedPlaylist;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ClientSender implements Runnable{
    private OutputStream output;
    private ArrayList<String>friendsIPList;
    private Timer timer;
    public ClientSender(OutputStream outputStream, ArrayList<String>friendsIPList) {
        this.output=outputStream;
        this.friendsIPList = friendsIPList;
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    @Override
    public void run() {
        sendFriendsIPList();
        while (true)
        {

        }
    }
    public void sendFriendsIPList()
    {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(output);
            writer.writeObject(friendsIPList);
            writer.flush();
            System.out.println("fiends IPList sent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendSharedPlaylist(SharedPlaylist sharedPlaylist)
    {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(output);
            writer.writeObject(sharedPlaylist);
            writer.flush();
            System.out.println("SharedPlaylist is sent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
