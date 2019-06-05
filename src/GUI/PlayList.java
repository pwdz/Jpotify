package GUI;

import org.w3c.dom.html.HTMLLabelElement;
import sun.applet.Main;

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
    public PlayList() {
        super();
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Colors.getColor("heavy grey"));
        setPreferredSize(new Dimension(100, 0));
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;

        yourLibrary = makeLabelReady("YOUR LIBRARY", "grey");
//        gbc.weightx=1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(yourLibrary, gbc);

        songs = makeLabelReady("Songs", "heavy grey");
        gbc.gridy = 1;
        add(songs, gbc);

        albums = makeLabelReady("Albums", "heavy grey");
        gbc.gridy = 2;
        add(albums, gbc);

        playlistLabel = makeLabelReady("PLAYLISTS", "grey");
        gbc.gridy = 3;
        add(playlistLabel, gbc);

        l = new DefaultListModel<>();
        playlist = new JList<>(l);
        //strings will be in center
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) playlist.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        playlist.setPreferredSize(new Dimension(100, 0));
        playlist.setBackground(Colors.getColor("heavy grey"));
        playlist.setForeground(Color.WHITE);
        l.addElement("mosa");
        l.addElement("asdad");

        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty=1;
        add(playlist, gbc);

        /*newPlayListButton = new JButton("[+]New Playlist");
        newPlayListButton.setBackground(Colors.getColor("heavy grey"));
        newPlayListButton.setForeground(Color.WHITE);
        newPlayListButton.setPreferredSize(new Dimension(100,20));
        gbc.gridy++;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weighty=1;
        add(newPlayListButton,gbc);*/
        newPlaylist=makeLabelReady("[+]Add Playlist","grey");
        newPlaylist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                newPlaylist.setBackground(Colors.getColor("1"));
            }
        });
        newPlaylist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                newPlaylist.setBackground(Colors.getColor("grey"));
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
        private JLabel makeLabelReady(String labelName, String colorName) {
            JLabel label = new JLabel(labelName, SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBackground(Colors.getColor(colorName));
            label.setForeground(Color.WHITE);
            label.setPreferredSize(new Dimension(100, 30));
            return label;
        }
    }
