import ClientPackage.Client;
import GUI.MainFrame;
import Lists.LibrarySong;
import Lists.List;
import PlayerPackage.SongPlayer;
import Serialization.Serializer;

import java.util.ArrayList;

public class Main {
    private Client client;
    private SongPlayer songPlayer;
    private MainFrame mainFrame;

    public Main() {
        client = new Client("ali");
        mainFrame = new MainFrame(client.getLibrary().getSongs());
        songPlayer = new SongPlayer("C:\\Users\\acer\\Music\\01 Honey.mp3");
        songPlayer.playTheSong();
        songPlayer.pause();
        setLinkers();
        client.getLibrary().organizePlaylistPanelInStart();
    }

    public void setLinkers() {
        songPlayer.setDestinationToTimeSlider(mainFrame.getTimeSlider());
        mainFrame.setPauseAndPlayDestination(songPlayer);
        mainFrame.getAddPlaylist().setAddPlaylistListener(client.getLibrary());
        mainFrame.getChooseSong().setChooseSongListener(client.getLibrary());
        client.getLibrary().setLibraryListenerToPlaylistBar(mainFrame.getPlayListPanel());
        mainFrame.setTimeSliderListener(songPlayer);
        mainFrame.setSoundSliderListener(songPlayer);
        mainFrame.setListGUIListener(client.getLibrary());
        client.getLibrary().setLibraryChangeListListener(mainFrame);
        mainFrame.setCloseWindowListener(client.getLibrary());

    }

    public static void main(String[] args) {
        new Main();
    }
}
