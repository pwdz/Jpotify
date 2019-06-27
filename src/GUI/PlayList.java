package GUI;

import Listeners.AlbumPanelListener;
import Listeners.LibraryListenerToPlaylistBar;
import Listeners.ListGUIListener;
import Lists.ListType;
import Lists.Playlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PlayList extends JPanel implements LibraryListenerToPlaylistBar , AlbumPanelListener {
    private GridBagConstraints gbc;
    private JPanel playlistPanel;
    private JLabel yourLibrary;
    private JLabel songs, albums, playlistLabel;
    private JLabel favouriteSongs, sharedPlaylist;
    private JLabel newPlaylist, newSong;
    private ArrayList<JLabel> playlistsNames;
    private AddPlaylist addPlaylist;
    private ChooseSong chooseSong;
    private static final int WIDTH = 250, HEIGHT = 30;
    private ListGUIListener listGUIListener;
    GridBagConstraints gb = new GridBagConstraints();

    public PlayList() {
        super();
        gb.anchor = GridBagConstraints.NORTHWEST;

        addPlaylist = new AddPlaylist();

        chooseSong = new ChooseSong();

        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Essentials.getColor("heavy grey"));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;

        yourLibrary = Essentials.labelMaker("YOUR LIBRARY", "grey", WIDTH, HEIGHT);
//        gbc.weightx=1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(yourLibrary, gbc);

        songs = Essentials.labelMaker("Songs", "heavy grey", WIDTH, HEIGHT);
        mouseEnterAndExit(songs, "black", "heavy grey");
        addListGUIListenerToComponents(songs);
        gbc.gridy++;
        add(songs, gbc);

        favouriteSongs = Essentials.labelMaker("Favourite song", "heavy grey", WIDTH, HEIGHT);
        mouseEnterAndExit(favouriteSongs, "black", "heavy grey");
        addListGUIListenerToComponents(favouriteSongs);
        gbc.gridy++;
        add(favouriteSongs, gbc);

        sharedPlaylist = Essentials.labelMaker("Shared playlist", "heavy grey", WIDTH, HEIGHT);
        mouseEnterAndExit(sharedPlaylist, "black", "heavy grey");
        addListGUIListenerToComponents(sharedPlaylist);
        gbc.gridy++;
        add(sharedPlaylist, gbc);

        albums = Essentials.labelMaker("Albums", "heavy grey", WIDTH, HEIGHT);
        mouseEnterAndExit(albums, "black", "heavy grey");
        addListGUIListenerToComponents(albums);
        gbc.gridy++;
        add(albums, gbc);

        playlistLabel = Essentials.labelMaker("PLAYLISTS", "grey", WIDTH, HEIGHT);
        gbc.gridy++;
        add(playlistLabel, gbc);

//        l = new DefaultListModel<>();
//        playlist = new JList<>(l);
        //strings will be in center
//        DefaultListCellRenderer renderer = (DefaultListCellRenderer) playlist.getCellRenderer();
//        renderer.setHorizontalAlignment(JLabel.CENTER);
/////////////////////////////////////////////////////////////////////
//        playlist.setPreferredSize(new Dimension(WIDTH, 0));
//        playlist.setBackground(Essentials.getColor("heavy grey"));
//        playlist.setForeground(Color.WHITE);
//        addListGUIListenerToComponents(null);//if the input be null then method will consider it the JList playlist.
//        l.addElement("mosa");
//
//        gbc.gridy++;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.weighty = 1;
//        JScrollPane jScrollPane = new JScrollPane(playlist, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        jScrollPane.setPreferredSize(new Dimension(WIDTH, 0));
//        jScrollPane.setVisible(true);
//        add(jScrollPane, gbc);
        playlistPanel = new JPanel(new GridBagLayout());
        playlistPanel.setBackground(Essentials.getColor("heavy grey"));
        playlistsNames = new ArrayList<>();
        arrangePlaylists();

        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        JScrollPane js = new JScrollPane(playlistPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setPreferredSize(new Dimension(WIDTH, 0));
        add(js, gbc);
//////////////////////////////////////////////////////////////////////
        newPlaylist = Essentials.labelMaker("[+]Add Playlist", "grey", WIDTH, HEIGHT);
        newSong = Essentials.labelMaker("[+]Add new song", "grey", WIDTH, HEIGHT);


        mouseEnterAndExit(newPlaylist, "black", "grey");
        this.setMouseClicked(newPlaylist);

        mouseEnterAndExit(newSong, "black", "grey");
        this.setMouseClicked(newSong);

        gbc.gridy++;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(newSong, gbc);

        gbc.gridy++;
//        gbc.weighty=0;
//        gbc.fill=GridBagConstraints.HORIZONTAL;
        add(newPlaylist, gbc);

    }

    private void mouseEnterAndExit(JLabel label, String enterColor, String exitColor) {
        MouseAdapter mouseEntered = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ((JLabel) e.getSource()).setBackground(Essentials.getColor(enterColor));
            }
        };
        MouseAdapter mouseExit = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ((JLabel) e.getSource()).setBackground(Essentials.getColor(exitColor));
            }
        };
        label.addMouseListener(mouseEntered);
        label.addMouseListener(mouseExit);
    }

    private void setMouseClicked(JLabel label) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (((JLabel) e.getSource()).equals(newPlaylist)) {
                    addPlaylist.setVisible(true);
                } else if (((JLabel) e.getSource()).equals(newSong)) {
                    chooseSong.setVisible(true);

                }
            }
        });
    }

    public AddPlaylist getAddPlaylist() {
        return addPlaylist;
    }

    public ChooseSong getChooseSong() {
        return chooseSong;
    }

    @Override
    public void addNewPlaylist(Playlist playlist) {
        playlistsNames.add(Essentials.labelMaker(playlist.getName(), "heavy grey", WIDTH, HEIGHT));
        arrangePlaylists();
    }

    public void setListGUIListener(ListGUIListener listener) {
        listGUIListener = listener;
    }


    private void addListGUIListenerToComponents(JLabel label) {
        //for labels
//        if (label != null) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (label.equals(songs)) {
                    listGUIListener.listClicked(ListType.LibrarySong, "");
//                    System.out.println("dafuk");
                } else if (label.equals(favouriteSongs)) {
                    listGUIListener.listClicked(ListType.FavouriteSong, "");
//                    System.out.println(ListType.FavouriteSong.toString());
                } else if (label.equals(sharedPlaylist))
                    listGUIListener.listClicked(ListType.SharedPlaylist, "");
                else if (label.equals(albums))
                    listGUIListener.listClicked(ListType.Album, "");
                else {
                    listGUIListener.listClicked(ListType.Playlist, label.getText());
//                    System.out.println("{}"+label.getName());
                }
            }
        });
    }

    private void arrangePlaylists() {
        playlistPanel.removeAll();
        playlistPanel.repaint();
        playlistPanel.revalidate();

        gb.gridx = 0;
        gb.gridy = 0;
        for (JLabel label : playlistsNames) {
            mouseEnterAndExit(label,"black","heavy grey");
            addListGUIListenerToComponents(label);
            playlistPanel.add(label,gb);
            gb.gridy++;
        }
        gb.weighty=1;
        gb.fill = GridBagConstraints.VERTICAL;
        playlistPanel.add(Essentials.labelMaker("","heavy grey",WIDTH,0),gb);
        gb.weighty=0;
        gb.fill=GridBagConstraints.HORIZONTAL;

    }

    @Override
    public void openAlbum(String name) {
        listGUIListener.listClicked(ListType.Album,name);
        System.out.println("name:"+name);
    }
}
