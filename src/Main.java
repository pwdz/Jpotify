import ClientPackage.Client;
import GUI.MainFrame;
import Music.Song;
import PlayerPackage.SongPlayer;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Client client = new Client("ali");
        try {
            Song song = new Song("C:\\Users\\acer\\Music\\01 Honey.mp3");
            SongPlayer songPlayer = new SongPlayer(song);
            songPlayer.setDestinationToTimeSlider(mainFrame.getTimeSlider());
            mainFrame.setPauseAndPlayDestination(songPlayer);
            songPlayer.playTheSong();
            songPlayer.pause();
            mainFrame.getAddPlaylist().setAddPlaylistListener(client.getLibrary());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
