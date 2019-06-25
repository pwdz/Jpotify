package GUI;

import Lists.Album;
import Lists.FavouriteSongs;
import Lists.List;
import Music.Song;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;

public class ListDisplayer extends JPanel {
   private final int WIDTH=300, HEIGHT=20;
    private List list;
    private ImageIcon artworkImage;
    private Object objectArray[];
    private JLabel titleOfTheList;
    private JLabel artist;
    private JLabel numberAndTime;
    private JLabel creator;
    private JPanel panel1=new JPanel();
    private DefaultTableModel defaultTableModel;
    public ListDisplayer(List list){

        this.setVisible(true);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.list=list;
        if(list instanceof Album){
            albumDisplaySetups();
        }
        else {
            playListDisplaySetups();
        }
        this.setBackground(Essentials.getColor("heavy grey"));
        artworkImage = new ImageIcon(list.getArtwork());
        artworkImage = new ImageIcon(artworkImage.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
        JLabel artworkLable = new JLabel(artworkImage,JLabel.RIGHT);
        artworkLable.setPreferredSize(new Dimension(300,200));
        artworkLable.setHorizontalAlignment(SwingConstants.CENTER);
        artworkLable.setVerticalAlignment(SwingConstants.TOP);



        JPanel panel2=new JPanel();
        JPanel panel3=new JPanel();

        JTable jTable=new JTable(defaultTableModel);
//        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 //      jTable.getColumnModel().getColumn(0).setPreferredWidth(10);
//        jTable.getColumnModel().getColumn(1).setPreferredWidth(10);
//        jTable.getColumnModel().getColumn(2).setPreferredWidth(10);
//        jTable.getColumnModel().getColumn(3).setPreferredWidth(90);
//
//        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        jTable.setEnabled(false);
//        jTable.setRowSelectionAllowed(true);
//        jTable.setShowHorizontalLines(true);
//        GridBagLayout gridBagLayout=new GridBagLayout();
//        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        jTable.setPreferredSize(new Dimension(100,600));
       // jTable.editingStopped(null);
        panel2.setLayout(new BorderLayout());
        panel3.setLayout(new BorderLayout());
        panel1.setBackground(Essentials.getColor("heavy grey"));
        panel2.setBackground(Essentials.getColor("heavy grey"));
        panel3.setBackground(Essentials.getColor("heavy grey"));
        artworkLable.setVisible(true);
        panel3.setBackground(Essentials.getColor("heavy grey"));
        artworkLable.setForeground(Essentials.getColor("heavy grey"));
        jTable.setBackground(Essentials.getColor("heavy grey"));
        jTable.setForeground(Essentials.getColor("grey"));
        jTable.setGridColor(Essentials.getColor("heavy grey"));
        JScrollPane js=new JScrollPane(jTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setVisible(true);

//        jTable.add(js);
        js.setBackground(Essentials.getColor("near black"));

        JLabel nothing= Essentials.labelMaker("", "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 20), "grey");
        jTable.setDragEnabled(true);
        jTable.setRowHeight(20);
        jTable.setTableHeader(null);

        panel2.add(js,BorderLayout.CENTER);
        panel1.setBackground(Essentials.getColor("heavy grey"));
        panel3.add(nothing,BorderLayout.NORTH);
        panel3.add(panel1,BorderLayout.CENTER);
        panel3.add(artworkLable,BorderLayout.WEST);
        this.setLayout(new BorderLayout());
//        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
//        this.setLayout(gridBagLayout);
//        Essentials.gridBagSetups(gridBagConstraints,0,0,4,4);
//        this.add(panel1,gridBagConstraints);
//        Essentials.gridBagSetups(gridBagConstraints,5,0,3,3);
//        this.add(panel3,gridBagConstraints);
//        Essentials.gridBagSetups(gridBagConstraints,0,5,15,15);
//        this.add(panel2,gridBagConstraints);
       // this.add(panel1,BorderLayout.NORTH);
       this.add(panel3,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.CENTER);
//        list.addSong("C:\\Users\\acer\\Music\\01 Honey.mp3");
    }

    public void albumDisplaySetups(){
        objectArray=new Object[]{""," #","Title","Duration"};
        defaultTableModel=new DefaultTableModel(objectArray,0){
            @Override
            public Class<?> getColumnClass(int column) {
                switch(column) {
                    case 0:return ImageIcon.class;
                    case 1: return String.class;
                    case 2: return String.class;
                    case 3:return String.class;
                    default: return Object.class;
                }
            }
        };


        titleOfTheList= null;
        artist=null;
        numberAndTime=null;
        try {
            Song firstSong=new Song(list.getSongsPaths().get(0));
            titleOfTheList=Essentials.labelMaker(firstSong.getAlbum(), "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 60), "grey");
            artist = Essentials.labelMaker(firstSong.getArtist(), "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 20), "grey");
            numberAndTime=  Essentials.labelMaker(list.getSongsPaths().size()+" songs, "+list.totalTimeToString(), "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 20), "grey");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Song song;
        String titleString;
        String timeString;
        String number;
        defaultTableModel.addRow(objectArray);
        defaultTableModel.addRow(new String[]{});
        ImageIcon heartImg = Essentials.imageProvider("./pics/EmptyHeart.png", 10, 10);
        JLabel heartLable= Essentials.labelMaker("", "heavy grey", 10, 10);
        heartLable.setIcon(heartImg);
        for (String path:list.getSongsPaths()) {
            try {
                song=new Song(path);
                titleString=song.getTitle();
                timeString=song.songDurationToString();
                number=String.valueOf(list.getSongsPaths().indexOf(path)+1);
                defaultTableModel.addRow(new Object[]{heartImg,number, titleString, timeString});
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        panel1.setLayout(new BoxLayout(panel1,3));
        panel1.add(titleOfTheList);
        panel1.add(artist);
        panel1.add(numberAndTime);

    }
    public void playListDisplaySetups(){
        if(list instanceof FavouriteSongs){
            objectArray=new Object[]{"   #","Title","Artist","Duration",""};
            defaultTableModel=new DefaultTableModel(objectArray,0){
                @Override
                public Class<?> getColumnClass(int column) {
                    switch(column) {
                        case 0: return String.class;
                        case 1: return String.class;
                        case 2:return String.class;
                        case 3: return String.class;
                        case 4: return ImageIcon.class;
                        default: return Object.class;
                    }
                }
            };
            titleOfTheList = Essentials.labelMaker("Favourite Songs", "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 60), "grey");
        }
        else {
            objectArray = new Object[]{"", " #", "Title", "Artist", "Duration", ""};
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
                        default:
                            return Object.class;
                    }
                }
            };


            titleOfTheList = Essentials.labelMaker(list.getName(), "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 60), "grey");
        }
        artist=null;
        numberAndTime= Essentials.labelMaker(list.getSongsPaths().size()+" songs, "+list.totalTimeToString(), "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 20), "grey");

        Song song;
        String titleString;
        String timeString;
        String artistString;
        String number;
        defaultTableModel.addRow(objectArray);
        defaultTableModel.addRow(new String[]{});
        ImageIcon heartImg = Essentials.imageProvider("./pics/EmptyHeart.png", 10, 10);
        ImageIcon removeImg = Essentials.imageProvider("./pics/icons8-minus-50.png", 10, 10);
        //ImageIcon changeImg = Essentials.imageProvider("./pics/convert-a-png-to-vector-2.png", 10, 10);
        if(list instanceof FavouriteSongs){
            for (String path : list.getSongsPaths()) {
                try {
                    song = new Song(path);
                    titleString = song.getTitle();
                    timeString = song.songDurationToString();
                    artistString = song.getArtist();
                    number = String.valueOf(list.getSongsPaths().indexOf(path) + 1);
                    defaultTableModel.addRow(new Object[]{ "   "+number, titleString, artistString, timeString, removeImg});
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            for (String path : list.getSongsPaths()) {
                try {
                    song = new Song(path);
                    titleString = song.getTitle();
                    timeString = song.songDurationToString();
                    artistString = song.getArtist();
                    number = String.valueOf(list.getSongsPaths().indexOf(path) + 1);
                    defaultTableModel.addRow(new Object[]{heartImg, number, titleString, artistString, timeString, removeImg});
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        panel1.setLayout(new BoxLayout(panel1,3));
        panel1.add(titleOfTheList);
        JLabel nothing= Essentials.labelMaker("", "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 20), "grey");
        panel1.add(nothing);
        panel1.add(numberAndTime);
        //button

    }

/*    public static void main(String[] args) {
        Song song = null;
        try {
            song = new Song("C:\\Users\\acer\\Music\\01 Honey.mp3");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FavouriteSongs list=new FavouriteSongs("the thrill of it all","",song.getArtwork());
        list.addSong("C:\\Users\\acer\\Music\\01 Honey.mp3");
//        list.addSong("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//        list.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");
//        list.addSong("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//        list.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");
//        list.addSong("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//        list.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");
//        list.addSong("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//        list.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");
//        list.addSong("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//        list.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");
//        list.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");
//        list.addSong("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//        list.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");
//        list.addSong("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//        list.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");
//        list.addSong("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//        list.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");
//        list.addSong("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//        list.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");
//        list.addSong("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//        list.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");


        JFrame jFrame=new JFrame();
        jFrame.setSize(new Dimension(1000, 500));
        jFrame.setVisible(true);
        ListDisplayer listDisplayer=new ListDisplayer(list);
        listDisplayer.setVisible(true);
        jFrame.add(listDisplayer,BorderLayout.CENTER);



    }*/
}
