import ClientPackage.Client;
import GUI.MainFrame;
import PlayerPackage.SongPlayer;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Client client = new Client("ali");
        SongPlayer songPlayer = new SongPlayer("C:\\Users\\acer\\Music\\01 Honey.mp3");
        songPlayer.setDestinationToTimeSlider(mainFrame.getTimeSlider());
        mainFrame.setPauseAndPlayDestination(songPlayer);
        songPlayer.playTheSong();
        songPlayer.pause();
        mainFrame.getAddPlaylist().setAddPlaylistListener(client.getLibrary());
        mainFrame.getChooseSong().setChooseSongListener(client.getLibrary());
        client.getLibrary().setLibraryListenerToPlaylistBar(mainFrame.getPlayListPanel());
    }
}
