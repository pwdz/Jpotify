package GUI;

import Music.Song;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;

public class PlayerBar extends JPanel {
    private SongInfo songInfo;
    private SongPlayer songPlayer;
    private static final int WIDTH = 0, HEIGHT = 120;
    private boolean isLiked = false;
    public PlayerBar() {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Colors.getColor("heavy grey"));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        songInfo = new SongInfo();
        add(songInfo, BorderLayout.WEST);

        songPlayer=new SongPlayer();
        add(songPlayer);

        try {
            Song song = new Song("C:\\Users\\acer\\Music\\01 Honey.mp3");
            songInfo.setArtwork(song.getArtwork());
            songInfo.setArtistName(song.getArtist());
            songInfo.setSongTitle(song.getTitle());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private class SongInfo extends JPanel {
        private ImageIcon artworkImage;
        private JLabel artworkLabel;
        private JLabel artistLabel;
        private JLabel titleLabel;
        private JLabel heartLabel;
        private GridBagConstraints gbc;
        private MouseListener myHeartListener;

        public SongInfo() {
            super();
            setLayout(new GridBagLayout());
            setPreferredSize(new Dimension(250, 100));
            setOpaque(true);
            setBackground(Colors.getColor("near black"));

            gbc = new GridBagConstraints();

            artworkLabel = LabelMaker.labelMaker("", "heavy grey", 90, 90);
            artistLabel = LabelMaker.labelMaker("", "heavy grey", 90, 40);
            titleLabel = LabelMaker.labelMaker("", "heavy grey", 90, 40);
            heartLabel = LabelMaker.labelMaker("", "heavy grey", 45, 45);
            ImageIcon imageIcon=new ImageIcon("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\EmptyHeart.png");
            heartLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH)));
            heartLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    setHeartLabel();
                }
            });

            gbc.insets = new Insets(1, 1, 1, 1);
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.VERTICAL;
            add(artworkLabel, gbc);
            gbc.gridheight = 1;

            gbc.gridx = 1;
            add(titleLabel, gbc);

            gbc.gridx = 2;
            add(heartLabel, gbc);

            gbc.gridy = 1;
            gbc.gridx = 1;
            add(artistLabel, gbc);
        }

        /**
         * +
         * gets bytes and sets image of the label
         *
         * @param artworkByteCode
         */
        public void setArtwork(byte[] artworkByteCode) {
            try {
                artworkImage = new ImageIcon(ImageIO.read(new ByteArrayInputStream(artworkByteCode)));
                artworkImage = new ImageIcon(artworkImage.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
                artworkLabel.setIcon(artworkImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void setSongTitle(String title) {
            titleLabel.setText(title);
        }

        public void setArtistName(String artist) {
            artistLabel.setText(artist);
        }

        public void setHeartLabel() {

            if (isLiked) {
                isLiked = false;
                ImageIcon img = new ImageIcon("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\EmptyHeart.png");
                heartLabel.setIcon(new ImageIcon(img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
            } else {
                isLiked = true;
                ImageIcon img = new ImageIcon("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\FilledHeart.png");
                heartLabel.setIcon(new ImageIcon(img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
            }
        }
    }
    private class SongPlayer extends JPanel{
        private JSlider timeSlider,soundSlider;
        private JLabel pauseAndPlay;
        private JLabel next,previous;
        public SongPlayer() {
            super();
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(10,10));
            setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            setOpaque(true);
            setBackground(Colors.getColor("heavy grey"));

            timeSlider = new JSlider(0,80,0);
//            timeSlider.setBackground(Colors.getColor("grey"));
//            Icon icon = new ImageIcon("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\Circle.png");
//            UIDefaults defaults = UIManager.getDefaults();
//            defaults.put("1",icon);
            timeSlider.setPreferredSize(new Dimension(900,15));
            timeSlider.setBackground(Colors.getColor("heavy grey"));
//            timeSlider.setForeground(Colors.getColor("blue"));
//            timeSlider.setInverted(false);
//            timeSlider.setPaintTicks(true);
//            timeSlider.setMajorTickSpacing(2);
//            timeSlider.setPaintTicks(true);
//            timeSlider.setPaintLabels(true);
            soundSlider= new JSlider(0,100,50);
            soundSlider.setBackground(Colors.getColor("heavy grey"));
            soundSlider.setPreferredSize(new Dimension(100,15));

            pauseAndPlay = LabelMaker.labelMaker("","heavy grey",45,45);
            ImageIcon pauseImg=new ImageIcon("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\Play.png");
            pauseAndPlay.setIcon(new ImageIcon(pauseImg.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)));

            next= LabelMaker.labelMaker("","heavy grey",45,45);
            ImageIcon nextImg= new ImageIcon("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\Next.png");
            next.setIcon(new ImageIcon(nextImg.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH)));

            previous = LabelMaker.labelMaker("","heavy grey",45,45);
            ImageIcon prevImg= new ImageIcon("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\Previous.png");
            previous.setIcon(new ImageIcon(prevImg.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH)));
//down: pause and next and previous are in a JLabel
            JLabel temp = LabelMaker.labelMaker("","heavy grey",0,45);
            temp.setLayout(new GridBagLayout());
            GridBagConstraints gbc=new GridBagConstraints();
            gbc.anchor=GridBagConstraints.NORTH;
            gbc.gridx=0;
            gbc.gridy=0;
            temp.add(previous,gbc);
            gbc.gridx=1;
            temp.add(pauseAndPlay,gbc);
            gbc.gridx=2;
            temp.add(next,gbc);
//down: JSlider is in a JLabel
            JLabel timeSlideTmp=LabelMaker.labelMaker("","heavy grey",1,30);
            gbc.gridx=0;
            gbc.gridy=0;
            gbc.weightx=1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(2,2,2,2);
            timeSlideTmp.setLayout(new GridBagLayout());
            timeSlideTmp.add(timeSlider,gbc);

//down: a small JSlider for sound in a JLabel

            JLabel soundSlideTmp = LabelMaker.labelMaker("","yellow",150,45);

            JLabel soundIconLabel = LabelMaker.labelMaker("","heavy grey",45,45);
            ImageIcon soundImg = new ImageIcon("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\FilledSound.png");
            soundIconLabel.setIcon(new ImageIcon(soundImg.getImage().getScaledInstance(30,25,Image.SCALE_SMOOTH)));

            soundSlideTmp.setLayout(new BorderLayout());
            soundSlideTmp.add(soundIconLabel,BorderLayout.WEST);
            soundSlideTmp.add(soundSlider,BorderLayout.CENTER);

//adding all 3 together.
            add(temp,BorderLayout.CENTER);
            add(timeSlideTmp,BorderLayout.SOUTH);
            add(soundSlideTmp,BorderLayout.EAST);
        }
        public void setTimeSliderValue(int value)
        {
            timeSlider.setValue(value);
        }

    }

}

