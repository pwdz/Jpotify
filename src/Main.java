import ClientPackage.Client;
import GUI.MainFrame;
import PlayerPackage.SongPlayer;

public class Main {
    private Client client;
    private SongPlayer songPlayer;
    private MainFrame mainFrame;

    public Main() {
        client = new Client("ali");
        songPlayer = new SongPlayer();
        mainFrame = new MainFrame(client.getLibrary().getSongs());
        setLinkers();
        client.getLibrary().setStartSong();
        songPlayer.pause();
        client.getLibrary().organizePlaylistPanelInStart();
        mainFrame.setListDisplayerChangeSongListener(client.getLibrary());
        mainFrame.setInitialListDisplayerChangeSongListener();
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
        client.getLibrary().setLibraryChangeSongListener(songPlayer);
        songPlayer.setSongPlayerChangeSongListener(mainFrame.getPlayerBar());
    }

    public static void main(String[] args) {
        new Main();
    }
}
