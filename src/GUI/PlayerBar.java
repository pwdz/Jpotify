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
            songInfo.setArtistName(song.getArtist());
            songInfo.setSongTitle(song.getTitle());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //
    }
}
class SongInfo extends JPanel{
    private ImageIcon artworkImage;
    private JLabel artworkLabel;
    private JLabel artistLabel;
    private JLabel titleLabel;
    private JLabel heartLabel;
    private GridBagConstraints gbc;
    public SongInfo() {
        super();
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(250,100));
        setOpaque(true);
        setBackground(Colors.getColor("grey"));

        gbc = new GridBagConstraints();

        artworkLabel = LabelMaker.labelMaker("","heavy grey",90,90);
        artistLabel= LabelMaker.labelMaker("","heavy grey",90,40);
        titleLabel = LabelMaker.labelMaker("","heavy grey",90,40);
        heartLabel = LabelMaker.labelMaker("","heavy grey",45,45);
        setHeartLabel();

        gbc.insets=new Insets(1,1,1,1);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=2;
        gbc.fill= GridBagConstraints.VERTICAL;
        add(artworkLabel,gbc);
        gbc.gridheight=1;

        gbc.gridx=1;
        add(titleLabel,gbc);

        gbc.gridx=2;
        add(heartLabel,gbc);

        gbc.gridy=1;
        gbc.gridx=1;
        add(artistLabel,gbc);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setSongTitle(String title)
    {
        titleLabel.setText(title);
    }
    public void setArtistName(String artist)
    {
        artistLabel.setText(artist);
    }
    public void setHeartLabel()
    {
        ImageIcon img= new ImageIcon("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\heart.png");
        heartLabel.setIcon(new ImageIcon(img.getImage().getScaledInstance(45,45,Image.SCALE_SMOOTH)));
    }

}
