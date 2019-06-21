package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayList extends JPanel {
    private GridBagConstraints gbc;
    private JLabel yourLibrary;
    private JLabel songs, albums, playlistLabel;
    private JLabel newPlaylist;
    private JList<String> playlist;
    private DefaultListModel<String> l;
    private JButton newPlayListButton;
    private static final int WIDTH = 250,HEIGHT=30;
    public PlayList() {
        super();
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Essentials.getColor("heavy grey"));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;

        yourLibrary = Essentials.labelMaker("YOUR LIBRARY", "grey",WIDTH,HEIGHT);
//        gbc.weightx=1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(yourLibrary, gbc);

        songs = Essentials.labelMaker("Songs", "heavy grey",WIDTH,HEIGHT);
        gbc.gridy = 1;
        add(songs, gbc);

        albums = Essentials.labelMaker("Albums", "heavy grey",WIDTH,HEIGHT);
        gbc.gridy = 2;
        add(albums, gbc);

        playlistLabel = Essentials.labelMaker("PLAYLISTS", "grey",WIDTH,HEIGHT);
        gbc.gridy = 3;
        add(playlistLabel, gbc);

        l = new DefaultListModel<>();
        playlist = new JList<>(l);
        //strings will be in center
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) playlist.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        playlist.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        playlist.setBackground(Essentials.getColor("heavy grey"));
        playlist.setForeground(Color.WHITE);
        l.addElement("mosa");
        l.addElement("asdad");

        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty=1;
        add(playlist, gbc);

        /*newPlayListButton = new JButton("[+]New Playlist");
        newPlayListButton.setBackground(Essentials.getColor("heavy grey"));
        newPlayListButton.setForeground(Color.WHITE);
        newPlayListButton.setPreferredSize(new Dimension(100,20));
        gbc.gridy++;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weighty=1;
        add(newPlayListButton,gbc);*/
        newPlaylist=Essentials.labelMaker("[+]Add Playlist","grey",WIDTH,HEIGHT);
        newPlaylist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                newPlaylist.setBackground(Essentials.getColor("1"));
            }
        });
        newPlaylist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                newPlaylist.setBackground(Essentials.getColor("grey"));
            }
        });
        gbc.gridy++;
        gbc.weighty=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        add(newPlaylist,gbc);

    }

    /*
        @param: takes a JLabel and it's name and color and produces it.
    */
//        private JLabel makeLabelReady(String labelName, String colorName) {
//            JLabel label = new JLabel(labelName, SwingConstants.CENTER);
//            label.setOpaque(true);
//            label.setBackground(Essentials.getColor(colorName));
//            label.setForeground(Color.WHITE);
//            label.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//            return label;
//        }
    }
