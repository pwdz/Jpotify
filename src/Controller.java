import ClientPackage.User;
import GUI.MainFrame;
import Listeners.LoginFormListener;
import Network.Client.Client;
import PlayerPackage.SongPlayer;

import java.util.ArrayList;

public class Controller implements LoginFormListener {
//    private final Client client;
    private User user;
    private SongPlayer songPlayer;
    private MainFrame mainFrame;

    public Controller() {
//        ArrayList<String> ip=new ArrayList<>();
//        String ip2="fgh";
//        ip.add(ip2);
//        client = new Client("127.0.0.1", ip);
        user = new User("ali");
        songPlayer = new SongPlayer();
//        mainFrame = new MainFrame(client.getUser().getLibrary().getSongs(), client.getUser().getLibrary().getLists());
        mainFrame = new MainFrame(user.getLibrary().getSongs(), user.getLibrary().getLists());

        setLinkers();
//        client.setMessage("currentSong/user1");
//        client.startSendingAndReceiving();

//        client.getUser().getLibrary().setStartSong();
        user.getLibrary().setStartSong();

        songPlayer.pause();
//        client.getUser().getLibrary().organizePlaylistPanelInStart();
        user.getLibrary().organizePlaylistPanelInStart();

//        mainFrame.setListDisplayerChangeSongListener(client.getUser().getLibrary());
        mainFrame.setListDisplayerChangeSongListener(user.getLibrary());

        mainFrame.setInitialListDisplayerChangeSongListener();
//        songPlayer.setCurrentList(client.getUser().getLibrary().getSongs());
        songPlayer.setCurrentList(user.getLibrary().getSongs());

//        client.setMessage("FriendIPList/user1");
    }

    public void setLinkers() {
        songPlayer.setDestinationToTimeSlider(mainFrame.getTimeSlider());
        mainFrame.setPauseAndPlayDestination(songPlayer);
//        mainFrame.getAddPlaylist().setAddPlaylistListener(client.getUser().getLibrary());
//        mainFrame.getChooseSong().setChooseSongListener(client.getUser().getLibrary());
//        client.getUser().getLibrary().setLibraryListenerToPlaylistBar(mainFrame.getPlayListPanel());
        mainFrame.getAddPlaylist().setAddPlaylistListener(user.getLibrary());
        mainFrame.getChooseSong().setChooseSongListener(user.getLibrary());
        user.getLibrary().setLibraryListenerToPlaylistBar(mainFrame.getPlayListPanel());

        mainFrame.setTimeSliderListener(songPlayer);
        mainFrame.setSoundSliderListener(songPlayer);
//        mainFrame.setListGUIListener(client.getUser().getLibrary());
//        client.getUser().getLibrary().setLibraryChangeListListener(mainFrame);
//        mainFrame.setCloseWindowListener(client.getUser().getLibrary());
//        client.getUser().getLibrary().setLibraryChangeSongListener(songPlayer);
        mainFrame.setListGUIListener(user.getLibrary());
        user.getLibrary().setLibraryChangeListListener(mainFrame);
        mainFrame.setCloseWindowListener(user.getLibrary());
        user.getLibrary().setLibraryChangeSongListener(songPlayer);
        songPlayer.setSongPlayerChangeSongListener(mainFrame.getPlayerBar());
//        mainFrame.getPlayList().setPlaylistRemoveListener(client.getUser().getLibrary());
        mainFrame.getPlayList().setPlaylistRemoveListener(user.getLibrary());
        mainFrame.getPlayerBarPanel().setPlayBarNxtAndPreListener(songPlayer);
//        client.setGetCurrentSongFromSongPlayerForClient(songPlayer);
//        songPlayer.setGiveCurrentSongToClient(client);
    }

//    public static void main(String[] args) {
//        Controller controller = new Controller();


    //Client client3=new Client("127.0.0.1",iplist3);

    //    }
    public static void main(String[] args) {
//        client.setMessage("FriendIPList/user1");
        Controller c = new Controller();
    }

    @Override
    public void startProgram(String name) {

    }

    public SongPlayer getSongPlayer() {
        return songPlayer;
    }
}
