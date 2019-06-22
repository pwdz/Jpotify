import GUI.MainFrame;
import Music.Song;
import PlayerPackage.SongPlayer;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        try {
            Song song = new Song("C:\\Users\\acer\\Music\\01 Honey.mp3");
            SongPlayer songPlayer = new SongPlayer(song);
            songPlayer.setListener(mainFrame);
            mainFrame.setPauseAndPlayDestination(songPlayer);
            songPlayer.playTheSong();
            songPlayer.pause();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
