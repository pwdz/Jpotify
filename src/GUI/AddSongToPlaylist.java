package GUI;

import Listeners.AddSongToPlaylistListener;
import Lists.FavouriteSongs;
import Lists.List;
import Lists.Playlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class AddSongToPlaylist extends JFrame {
    JComboBox<String> comboBox;
    ArrayList<List> lists;
    JButton addButton;
    String songName;
    AddSongToPlaylistListener addSongToPlaylistListener;
    public AddSongToPlaylist(ArrayList<List> lists,String songName) {
        super();
        this.songName=songName;
        this.lists = lists;
        setSize(new Dimension(500, 500));
        setResizable(false);
        setLayout(new BorderLayout());
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 100);

        JLabel title = Essentials.labelMaker("Add song to playlist", "heavy grey", 500, 30);

        JLabel centerLabel = Essentials.labelMaker("", "heavy grey", 300, 300);
        centerLabel.setLayout(new BorderLayout());
        comboBox = new JComboBox<>();
        comboBox.setBackground(Essentials.getColor("grey"));
        comboBox.addItem("Choose Playlist");
        centerLabel.add(comboBox, BorderLayout.NORTH);

        addButton = new JButton("Add");
        buttonClickListener();

        add(title, BorderLayout.NORTH);
        add(centerLabel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        setComboBoxValue();
        setVisible(true);
    }

    private void setComboBoxValue() {
        for (List l : lists) {
            if (l instanceof Playlist)
                comboBox.addItem(l.getName());
        }
    }

    private void buttonClickListener() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!comboBox.getSelectedItem().equals("Choose Playlist")) {
                    addSongToPlaylistListener.addToPlaylist((String) comboBox.getSelectedItem(),songName);
                    dispose();
                }
            }
        });
    }
    public void setAddSongToPlaylistListener(AddSongToPlaylistListener listener)
    {
        addSongToPlaylistListener = listener;
    }

}
