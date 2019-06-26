import ClientPackage.Client;
import GUI.MainFrame;
import Lists.LibrarySong;
import PlayerPackage.SongPlayer;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("ali");
        MainFrame mainFrame = new MainFrame(client.getLibrary().getSongs());
        SongPlayer songPlayer = new SongPlayer("C:\\Users\\acer\\Music\\01 Honey.mp3");
        songPlayer.setDestinationToTimeSlider(mainFrame.getTimeSlider());
        mainFrame.setPauseAndPlayDestination(songPlayer);
        songPlayer.playTheSong();
        songPlayer.pause();
        mainFrame.getAddPlaylist().setAddPlaylistListener(client.getLibrary());
        mainFrame.getChooseSong().setChooseSongListener(client.getLibrary());
        client.getLibrary().setLibraryListenerToPlaylistBar(mainFrame.getPlayListPanel());
        mainFrame.setTimeSliderListener(songPlayer);
        mainFrame.setSoundSliderListener(songPlayer);
        mainFrame.setListGUIListener(client.getLibrary());
        client.getLibrary().setLibraryChangeListListener(mainFrame);

    }
}
