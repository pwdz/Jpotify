package GUI;

import Listeners.AlbumPanelListener;
import Lists.Album;
import Music.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class AlbumPanel extends JPanel {
    private Album album;
    private JLabel img;
    private AlbumPanelListener albumPanelListener;
    public AlbumPanel(Album album) {
        this.album = album;
        this.setBackground(Essentials.getColor("heavy grey"));
        this.setPreferredSize(new Dimension(1000,500));

        this.setLayout(new BoxLayout(this, 3));
        ImageIcon artworkImage ;
        if(album.getArtwork()!=null)
            artworkImage = new ImageIcon(album.getArtwork());
        else
            artworkImage = new ImageIcon(".\\pics\\Music2.JPG");
        artworkImage = new ImageIcon(artworkImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        img=new JLabel(artworkImage);
        img.setBackground(Essentials.getColor("heavy grey"));
        img.setPreferredSize(new Dimension(100,100));
        addAlbumClickListener();
        JLabel artworkLable = new JLabel(artworkImage, JLabel.CENTER);
        artworkLable.setPreferredSize(new Dimension(100, 100));
        JLabel nothing= Essentials.labelMaker("", "heavy grey", WIDTH, 50, new Font("Serif", Font.PLAIN, 20), "grey");

        artworkLable.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel name = Essentials.labelMaker("Title : "+album.getName(), "heavy grey", 70, 10, new Font("Serif", Font.PLAIN, 20), "grey");
        JLabel artist = null;
        try {
            System.out.println("kif+:"+album.getSongsPaths().size());
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
    private void addAlbumClickListener()
    {
        img.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                albumPanelListener.openAlbum(album.getName());
            }
        });
    }
    public void setAlbumPanelListener(AlbumPanelListener listener)
    {
        albumPanelListener = listener;
    }
//    public AlbumPanelListener getAlbumPanelListener()
//    {
//        return albumPanelListener;
//    }

}