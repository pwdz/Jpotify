package GUI;

import Lists.Album;
import Music.Song;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class AlbumPanel extends JPanel {
    private Album album;

    public AlbumPanel(Album album) {
        this.album = album;
        this.setBackground(Essentials.getColor("heavy grey"));
        this.setPreferredSize(new Dimension(1000,500));

        this.setLayout(new BoxLayout(this, 3));
        ImageIcon artworkImage = new ImageIcon(album.getArtwork());
        artworkImage = new ImageIcon(artworkImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        JButton img=new JButton(artworkImage);
        JLabel artworkLable = new JLabel(artworkImage, JLabel.CENTER);
        artworkLable.setPreferredSize(new Dimension(150, 150));
        JLabel nothing= Essentials.labelMaker("", "heavy grey", WIDTH, 50, new Font("Serif", Font.PLAIN, 20), "grey");

        artworkLable.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel name = Essentials.labelMaker("Title : "+album.getName(), "heavy grey", 70, 10, new Font("Serif", Font.PLAIN, 20), "grey");
        JLabel artist = null;
        try {
            Song song = new Song(album.getSongsPaths().get(0));
            artist = Essentials.labelMaker("Artist : "+song.getArtist(), "heavy grey", 70, 10, new Font("Serif", Font.PLAIN, 20), "grey");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.add(img);
        this.add(nothing);
        this.add(name);
        this.add(artist);

    }

}