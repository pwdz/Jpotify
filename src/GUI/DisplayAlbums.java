package GUI;

import Lists.Album;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayAlbums extends JPanel {
    ArrayList<Album> albums = new ArrayList<>();

    public DisplayAlbums(ArrayList<Album> albums) {
        this.setVisible(true);
        ImageIcon artworkImage;
        artworkImage = new ImageIcon("./pics/Music.png");
        artworkImage = new ImageIcon(artworkImage.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
        JLabel artworkLable = new JLabel(artworkImage,JLabel.RIGHT);
        artworkLable.setPreferredSize(new Dimension(300,200));
        artworkLable.setHorizontalAlignment(SwingConstants.CENTER);
        artworkLable.setVerticalAlignment(SwingConstants.TOP);
        artworkLable.setVisible(true);
        JLabel title=Essentials.labelMaker("Albums", "heavy grey", 100, 30, new Font("Serif", Font.PLAIN, 60), "grey");
         JLabel nothing= Essentials.labelMaker("", "heavy grey", WIDTH, 50, new Font("Serif", Font.PLAIN, 20), "grey");
        this.albums = albums;
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        JPanel panel3=new JPanel();
        this.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());
        panel2.add(title,BorderLayout.CENTER);
        panel2.add(artworkLable,BorderLayout.WEST);
        panel2.setPreferredSize(new Dimension(500,500));
        panel1.setPreferredSize(new Dimension(500,500));
        panel2.add(nothing,BorderLayout.NORTH);
        panel2.add(nothing,BorderLayout.SOUTH);
        panel3.setLayout(new BorderLayout());
        panel3.add(panel2,BorderLayout.WEST);

        panel1.setLayout(new GridLayout(0, 3,10,10));
        AlbumPanel albumPanel=null;
        for (Album album : albums) {
            albumPanel = new AlbumPanel(album);
            panel1.add(albumPanel);
        }
        this.add(panel1);
        panel1.setBackground(Essentials.getColor("heavy grey"));
        panel2.setBackground(Essentials.getColor("heavy grey"));
        panel3.setBackground(Essentials.getColor("heavy grey"));
        this.setBackground(Essentials.getColor("heavy grey"));
        JScrollPane js=new JScrollPane(panel1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setVisible(true);
        
        js.setBackground(Essentials.getColor("near black"));
        this.add(panel3,BorderLayout.CENTER);
        this.add(js,BorderLayout.SOUTH);




    }

    public static void main(String[] args) {
        Album album1 = new Album("album1", "");
        Album album2 = new Album("album2", "");
        album1.addSong("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
        album2.addSong("/Users/taratt/Downloads/2019-06-21 12.13.43.mp3");
        ArrayList<Album> albums=new ArrayList<>();
        albums.add(album1);
        albums.add(album2);
        DisplayAlbums displayAlbums=new DisplayAlbums(albums);
        JFrame jFrame=new JFrame();
        jFrame.setSize(new Dimension(1000, 500));
        jFrame.setVisible(true);

        jFrame.add(displayAlbums,BorderLayout.CENTER);
        displayAlbums.setVisible(true);
    }
}