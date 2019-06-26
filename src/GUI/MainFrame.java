package GUI;

import Listeners.*;
import Lists.*;
import Lists.List;
import Music.Song;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class MainFrame implements LibraryChangeListListener {

    private JFrame mainFrame;
    private JPanel panel, panel2;
    private PlayList playListPanel;
    private FriendsActivity friendsActivityPanel;
    private PlayerBar playerBar;
    private HeaderGUI headerPanel;
    private static final int HEIGHT = 920, WIDTH = 1500;
    private GridBagConstraints gbc;
    private ListDisplayer listDisplayer;

    public MainFrame() {
//        Song song = null;
//        try {
//            song = new Song("C:\\Users\\acer\\Music\\01 Honey.mp3");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        SharedPlaylist list = new SharedPlaylist("the thrill of it all", "", song.getArtwork());
//        list.addSong("C:\\Users\\acer\\Music\\01 Honey.mp3");
//        list.addSong("C:\\Users\\acer\\Music\\01 Honey.mp3");
//        list.addSong("C:\\Users\\acer\\Music\\01 Honey.mp3");
//        list.addSong("C:\\Users\\acer\\Music\\01 Honey.mp3");
//        list.addSong("C:\\Users\\acer\\Music\\01 Honey.mp3");
        listDisplayer = new ListDisplayer();
        //set JFrame's default properties
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 100);
        this.mainFrame = new JFrame("Jpotify");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(WIDTH, HEIGHT);

        gbc = new GridBagConstraints();
        //set Main Panel default properties
        panel = new JPanel();
//        panel.setLayout(new BorderLayout(5, 5));
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(true);
        panel.setBackground(Color.BLACK);

        panel2 = new JPanel();

        playListPanel = new PlayList();

        headerPanel = new HeaderGUI();

//        panel2.setOpaque(true);
//        panel2.setPreferredSize(new Dimension(1000,1000));
//        panel2.setBackground(new Color(30, 30, 30));

        friendsActivityPanel = new FriendsActivity();

        playerBar = new PlayerBar();

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(playListPanel, gbc);

        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(headerPanel, gbc);
        gbc.weightx = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        panel.add(friendsActivityPanel, gbc);
//
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(listDisplayer, gbc);

        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(playerBar, gbc);
        mainFrame.add(panel);

//        gbc.gridy=1;
//        gbc.gridx=1;
//        gbc.weighty=1;
//        gbc.weightx=0;
//        gbc.fill =  GridBagConstraints.BOTH;
//        panel.remove(listDisplayer);
//        panel.revalidate();
//        panel.repaint();
//        mainFrame.repaint(10,10,1000,1000);
//        mainFrame.setView//
//        panel.add(listDisplayer,gbc);
//        panel.revalidate();
//        panel.repaint();


        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;

        mainFrame.setVisible(true);
    }

    public void setPauseAndPlayDestination(SongPlayerAndGUIListener destination) {
        playerBar.setPauseAndPlayLabel(destination);
    }

    public SongPlayerAndGUIListener getTimeSlider() {
        return playerBar.getSongPlayerTypeListener();
    }

    public AddPlaylist getAddPlaylist() {
        return playListPanel.getAddPlaylist();
    }

    public ChooseSong getChooseSong() {
        return playListPanel.getChooseSong();
    }

    //
//     public static void main(String[] args) {
//        MainFrame mainFrame = new MainFrame();
//    }
    public LibraryListenerToPlaylistBar getPlayListPanel() {
        return playListPanel;
    }

    public void setTimeSliderListener(TimeProgressBarListener listener) {
        playerBar.setTimeSliderListener(listener);
    }

    public void setSoundSliderListener(SoundSliderListener listener) {
        playerBar.setSoundSliderListener(listener);
    }

    public void setListGUIListener(ListGUIListener listener) {
        playListPanel.setListGUIListener(listener);
    }


    @Override
    public void updateCenter(List list) {
        System.out.println("pppp:"+list.getName());
//        listDisplayer = new ListDisplayer(list);
        panel.remove(listDisplayer);
        panel.revalidate();
        panel.repaint();
        listDisplayer = new ListDisplayer(list);
        panel.add(listDisplayer, gbc);
    }
}
