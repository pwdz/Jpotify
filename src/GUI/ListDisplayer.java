package GUI;

import Listeners.AddSongToPlaylistListener;
import Listeners.ListDisplayerListener;
import Lists.*;
import Lists.List;
import Music.Song;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ListDisplayer extends JPanel implements AddSongToPlaylistListener {
    private final int WIDTH = 300, HEIGHT = 20;
    private List list;
    private ImageIcon artworkImage;
    private Object objectArray[];
    private JLabel titleOfTheList;
    private JLabel artist;
    private JLabel numberAndTime;
    private JLabel creator;
    private JPanel panel1 = new JPanel();
    private DefaultTableModel defaultTableModel;
    private JTable jTable;
    private ListDisplayerListener listDisplayerListener;
    Song song;
    String titleString;
    String timeString;
    String number;
    ImageIcon addToPlaylist, heartImg, removeImg, playAndPause;
    ArrayList<List> lists;

    public ListDisplayer(List list, ArrayList<List> lists) {
        this.lists = lists;
        addToPlaylist = Essentials.imageProvider("./pics/Add2.png", 17, 17);
        playAndPause = Essentials.imageProvider("./pics/Play.png", 25, 25);
        heartImg = Essentials.imageProvider("./pics/EmptyHeart.png", 17, 17);
        removeImg = Essentials.imageProvider("./pics/icons8-minus-50.png", 17, 17);

        this.setVisible(true);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.list = list;
        if (list instanceof Album) {
            albumDisplaySetups();
        } else {
            playListDisplaySetups();
        }
        this.setBackground(Essentials.getColor("heavy grey"));
        try {
            artworkImage = new ImageIcon(list.getArtwork());
        } catch (Exception e) {
            artworkImage = new ImageIcon("./pics/Music2.JPG");
        }
        artworkImage = new ImageIcon(artworkImage.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
        JLabel artworkLable = new JLabel(artworkImage, JLabel.RIGHT);
        artworkLable.setPreferredSize(new Dimension(300, 200));
        artworkLable.setHorizontalAlignment(SwingConstants.CENTER);
        artworkLable.setVerticalAlignment(SwingConstants.TOP);


        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        jTable = new JTable(defaultTableModel);
//        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //      jTable.getColumnModel().getColumn(0).setPreferheavy greyWidth(10);
//        jTable.getColumnModel().getColumn(1).setPreferheavy greyWidth(10);
//        jTable.getColumnModel().getColumn(2).setPreferheavy greyWidth(10);
//        jTable.getColumnModel().getColumn(3).setPreferheavy greyWidth(90);
//
//        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        jTable.setEnabled(false);
//        jTable.setRowSelectionAllowed(true);
//        jTable.setShowHorizontalLines(true);
//        GridBagLayout gridBagLayout=new GridBagLayout();
//        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        jTable.setPreferredSize(new Dimension(100, 600));
        // jTable.editingStopped(null);
        panel2.setLayout(new BorderLayout());
        panel3.setLayout(new BorderLayout());
        panel1.setBackground(Essentials.getColor("heavy grey"));
        panel2.setBackground(Essentials.getColor("heavy grey"));
        panel3.setBackground(Essentials.getColor("heavy grey"));
        artworkLable.setVisible(true);
//        panel3.setBackground(Essentials.getColor("heavy grey"));
        artworkLable.setForeground(Essentials.getColor("heavy grey"));
        artworkLable.setBackground(Essentials.getColor("heavy grey"));
        jTable.setBackground(Essentials.getColor("heavy grey"));
        jTable.setForeground(Essentials.getColor("grey"));
        jTable.setGridColor(Essentials.getColor("heavy grey"));
        JScrollPane js = new JScrollPane(jTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setVisible(true);

//        jTable.add(js);
        js.setBackground(Essentials.getColor("near black"));

        JLabel nothing = Essentials.labelMaker("", "heavy grey", WIDTH, 50, new Font("Serif", Font.PLAIN, 20), "grey");
        jTable.setDragEnabled(true);
        jTable.setRowHeight(40);
        jTable.setTableHeader(null);
        jTable.setFont(new Font("Serif", Font.PLAIN, 15));
        panel2.add(js, BorderLayout.CENTER);
        panel1.setBackground(Essentials.getColor("heavy grey"));
        panel3.add(nothing, BorderLayout.NORTH);
        panel3.add(panel1, BorderLayout.CENTER);
        panel3.add(artworkLable, BorderLayout.WEST);
        this.setLayout(new BorderLayout());
        this.add(panel3, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.SOUTH);
//        list.addSong("C:\\Users\\acer\\Music\\01 Honey.mp3");
        setListeners(list);
    }

    public void albumDisplaySetups() {
        objectArray = new Object[]{"play", "like", " #", "Title", "Duration", "addToPlaylist"};
        defaultTableModel = new DefaultTableModel(objectArray, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return ImageIcon.class;
                    case 1:
                        return ImageIcon.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    case 5:
                        return ImageIcon.class;
                    default:
                        return Object.class;
                }
            }
        };
        titleOfTheList = null;
        artist = null;
        numberAndTime = null;
        try {
            Song firstSong = new Song(list.getSongsPaths().get(0));
            titleOfTheList = Essentials.labelMaker(firstSong.getAlbum(), "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 60), "grey");
            artist = Essentials.labelMaker(firstSong.getArtist(), "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 20), "grey");
            numberAndTime = Essentials.labelMaker(list.getSongsPaths().size() + " songs, " + list.totalTimeToString(), "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 20), "grey");
        } catch (FileNotFoundException e) {
        }


        defaultTableModel.addRow(objectArray);
        defaultTableModel.addRow(new String[]{});
        for (String path : list.getSongsPaths()) {
            try {
                song = new Song(path);
                titleString = song.getTitle();
                timeString = song.songDurationToString();
                number = String.valueOf(list.getSongsPaths().indexOf(path) + 1);
                defaultTableModel.addRow(new Object[]{playAndPause, heartImg, number, titleString, timeString, addToPlaylist});
            } catch (FileNotFoundException e) {
            }
        }
        //Album:

        panel1.setLayout(new BoxLayout(panel1, 3));
        panel1.add(titleOfTheList);
        panel1.add(artist);
        panel1.add(numberAndTime);

    }

    public void playListDisplaySetups() {
        if (list instanceof FavouriteSongs) {
            objectArray = new Object[]{"play", "   #", "Title", "Artist", "Duration", "Remove", "addToPlaylist"};
            defaultTableModel = new DefaultTableModel(objectArray, 0) {
                @Override
                public Class<?> getColumnClass(int column) {
                    switch (column) {
                        case 0:
                            return ImageIcon.class;
                        case 1:
                            return String.class;
                        case 2:
                            return String.class;
                        case 3:
                            return String.class;
                        case 4:
                            return String.class;
                        case 5:
                            return ImageIcon.class;
                        case 6:
                            return ImageIcon.class;
                        default:
                            return Object.class;
                    }
                }
            };
            titleOfTheList = Essentials.labelMaker("Favourite Songs", "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 60), "grey");
        } else if (list instanceof LibrarySong) {
            objectArray = new Object[]{"play", "like", " #", "Title", "Artist", "Duration", "addToPlaylist"};
            defaultTableModel = new DefaultTableModel(objectArray, 0) {
                @Override
                public Class<?> getColumnClass(int column) {
                    switch (column) {
                        case 0:
                            return ImageIcon.class;
                        case 1:
                            return ImageIcon.class;
                        case 2:
                            return String.class;
                        case 3:
                            return String.class;
                        case 4:
                            return String.class;
                        case 5:
                            return String.class;
                        case 6:
                            return ImageIcon.class;
                        default:
                            return Object.class;
                    }
                }
            };
            titleOfTheList = Essentials.labelMaker(list.getName(), "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 60), "grey");
        } else {
            objectArray = new Object[]{"play", "like", " #", "Title", "Artist", "Duration", "addToPlaylist", "remove"};
            defaultTableModel = new DefaultTableModel(objectArray, 0) {
                @Override
                public Class<?> getColumnClass(int column) {
                    switch (column) {
                        case 0:
                            return ImageIcon.class;
                        case 1:
                            return ImageIcon.class;
                        case 2:
                            return String.class;
                        case 3:
                            return String.class;
                        case 4:
                            return String.class;
                        case 5:
                            return String.class;
                        case 6:
                            return ImageIcon.class;
                        case 7:
                            return ImageIcon.class;
                        default:
                            return Object.class;
                    }
                }
            };
            titleOfTheList = Essentials.labelMaker(list.getName(), "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 60), "grey");
        }
        artist = null;
        numberAndTime = Essentials.labelMaker(list.getSongsPaths().size() + " songs, " + list.totalTimeToString(), "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 20), "grey");

        String artistString;
        defaultTableModel.addRow(objectArray);
        defaultTableModel.addRow(new String[]{});

        for (String path : list.getSongsPaths()) {
            try {
                song = new Song(path);
                titleString = song.getTitle();
                timeString = song.songDurationToString();
                artistString = song.getArtist();
                number = String.valueOf(list.getSongsPaths().indexOf(path) + 1);
                if (list instanceof FavouriteSongs)
                    defaultTableModel.addRow(new Object[]{playAndPause, "   " + number, titleString, artistString, timeString, removeImg, addToPlaylist});
                else if (list instanceof LibrarySong)
                    defaultTableModel.addRow(new Object[]{playAndPause, heartImg, number, titleString, artistString, timeString, addToPlaylist});
                else
                    defaultTableModel.addRow(new Object[]{playAndPause, heartImg, number, titleString, artistString, timeString, addToPlaylist, removeImg});
            } catch (FileNotFoundException e) {
            }
        }
        panel1.setLayout(new BoxLayout(panel1, 3));
        panel1.add(titleOfTheList);
        JLabel nothing = Essentials.labelMaker("", "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 20), "grey");
        panel1.add(nothing);
        panel1.add(numberAndTime);


    }

    private void setListeners(List list) {
        int songColumn;
        if (list instanceof FavouriteSongs)
            songColumn = 2;
        else
            songColumn = 3;
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int column = ((JTable) e.getSource()).columnAtPoint(new Point(e.getX(), e.getY()));
                int row = ((JTable) e.getSource()).rowAtPoint(new Point(e.getX(), e.getY()));
                String songName = (String) jTable.getValueAt(row, songColumn);
                switch (column) {
                    case 0://play
                        listDisplayerListener.changeSongInLibrary(songName);
                        break;
                    case 1://like
                        if (!(list instanceof FavouriteSongs)) {
                            listDisplayerListener.addSongToFavourites(songName);
                            jTable.setValueAt(Essentials.imageProvider("./pics/FilledHeart.png", 20, 20), row, 1);
                        }
                        break;
                    case 5:
                        if (list instanceof FavouriteSongs)//remove from favourites
                        {
                            listDisplayerListener.removeSongFromPlaylist(list, songName);
//                            ("baghali?????????????????");
                        } else if (list instanceof Album) {
                            AddSongToPlaylist addSongToPlaylist = new AddSongToPlaylist(lists, songName);
                            addSongToPlaylist.setAddSongToPlaylistListener(ListDisplayer.this::addToPlaylist);
                        }
                        break;
                    case 6:
                        AddSongToPlaylist addSongToPlaylist = new AddSongToPlaylist(lists, songName);
                        addSongToPlaylist.setAddSongToPlaylistListener(ListDisplayer.this::addToPlaylist);
                        break;
                    case 7:
                        if (list instanceof Playlist)//remove from SharedPlaylist and normal playlists
                        {
                            if (!(list instanceof FavouriteSongs) && !(list instanceof LibrarySong)) {
                                listDisplayerListener.removeSongFromPlaylist(list, songName);
                            }
                        }
                        break;
                }
            }
        });
    }

    public void setListDisplayerChangeSongListener(ListDisplayerListener listener) {
        listDisplayerListener = listener;
    }


    @Override
    public void addToPlaylist(String playlistName, String songName) {
        listDisplayerListener.addSongToPlaylist(playlistName, songName);
    }
}
