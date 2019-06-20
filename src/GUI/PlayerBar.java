package GUI;

import Music.Song;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;

public class PlayerBar extends JPanel {
    private SongInfo songInfo;
    private static final int WIDTH = 0,HEIGHT=120;
    public PlayerBar() {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Colors.getColor("heavy grey"));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        songInfo = new SongInfo();
        add(songInfo,BorderLayout.WEST);

        //
        try {
            Song song = new Song("C:\\Users\\acer\\Music\\01 Honey.mp3");
            songInfo.setArtwork(song.getArtwork());
            songInfo.setArtwork(song.getArtwork());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //
    }
}
class SongInfo extends JPanel{
    private ImageIcon artworkImage;
    private JLabel artworkLabel;

    public SongInfo() {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250,100));
        setOpaque(true);
        setBackground(Color.BLUE);

        artworkLabel=new JLabel();
        artworkLabel.setPreferredSize(new Dimension(90,90));

        add(artworkLabel, BorderLayout.WEST);

    }

    /**+
     * gets bytes and sets image of the label
     * @param artworkByteCode
     */
    public void setArtwork(byte[] artworkByteCode)
    {
        try {
            artworkImage = new ImageIcon(ImageIO.read(new ByteArrayInputStream(artworkByteCode)));
            artworkImage = new ImageIcon(artworkImage.getImage().getScaledInstance(90,90,Image.SCALE_SMOOTH));
            artworkLabel.setIcon(artworkImage);
            remove(artworkLabel);
            add(artworkLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
