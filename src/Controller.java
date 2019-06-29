import ClientPackage.User;
import GUI.MainFrame;
import Listeners.LoginFormListener;
import PlayerPackage.SongPlayer;

public class Controller implements LoginFormListener {
    private User user;
    private SongPlayer songPlayer;
    private MainFrame mainFrame;
    public Controller() {
        user = new User("ali");
        songPlayer = new SongPlayer();
        mainFrame = new MainFrame(user.getLibrary().getSongs(), user.getLibrary().getLists());
        setLinkers();
        user.getLibrary().setStartSong();
        songPlayer.pause();
        user.getLibrary().organizePlaylistPanelInStart();
        mainFrame.setListDisplayerChangeSongListener(user.getLibrary());
        mainFrame.setInitialListDisplayerChangeSongListener();
        songPlayer.setCurrentList(user.getLibrary().getSongs());
    }

    public void setLinkers() {
        songPlayer.setDestinationToTimeSlider(mainFrame.getTimeSlider());
        mainFrame.setPauseAndPlayDestination(songPlayer);
        mainFrame.getAddPlaylist().setAddPlaylistListener(user.getLibrary());
        mainFrame.getChooseSong().setChooseSongListener(user.getLibrary());
        user.getLibrary().setLibraryListenerToPlaylistBar(mainFrame.getPlayListPanel());
        mainFrame.setTimeSliderListener(songPlayer);
        mainFrame.setSoundSliderListener(songPlayer);
        mainFrame.setListGUIListener(user.getLibrary());
        user.getLibrary().setLibraryChangeListListener(mainFrame);
        mainFrame.setCloseWindowListener(user.getLibrary());
        user.getLibrary().setLibraryChangeSongListener(songPlayer);
        songPlayer.setSongPlayerChangeSongListener(mainFrame.getPlayerBar());
        mainFrame.getPlayList().setPlaylistRemoveListener(user.getLibrary());
    }

    public static void main(String[] args) {
        Controller controller= new Controller();
    }
    @Override
    public void startProgram(String username) {

    }
}
