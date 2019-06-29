package Network.Client;
import Network.Info;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;

public class ClientReceiver implements Runnable{
    private InputStream input;
    ObjectInputStream reader;
    public ClientReceiver(InputStream inputStream){
        input=inputStream;
        try {
            reader = new ObjectInputStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Info info;
        Info myInfo;
        String curSongPath="2";
        while (true)
        {
            try {
                info = (Info)reader.readObject();
                switch (info.getType())
                {
                    case 0://
                        break;
                    case 1:
                        break;
                    case 2://U want current song.
                        byte [] receivedSongBytes = info.getSongFile();
                        //current song received!
                        break;
                    case 3://they want current song
                        myInfo = new Info(2);
                        File songFile = new File(curSongPath);
                        myInfo.setSongFile(Files.readAllBytes(songFile.toPath()));
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
