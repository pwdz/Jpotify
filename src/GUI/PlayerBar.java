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
        setBackground(Essentials.getColor("heavy grey"));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        songInfo = new SongInfo();
        add(songInfo, BorderLayout.WEST);

        songPlayer = new SongPlayer();
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
            setBackground(Essentials.getColor("near black"));

            gbc = new GridBagConstraints();

            artworkLabel = Essentials.labelMaker("", "heavy grey", 90, 90);
            artistLabel = Essentials.labelMaker("", "heavy grey", 90, 40);
            titleLabel = Essentials.labelMaker("", "heavy grey", 90, 40);
            heartLabel = Essentials.labelMaker("", "heavy grey", 45, 45);

            ImageIcon imageIcon = Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\EmptyHeart.png", 25, 25);
            heartLabel.setIcon(imageIcon);
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
                ImageIcon img = Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\EmptyHeart.png", 25, 25);
                heartLabel.setIcon(img);
            } else {
                isLiked = true;
                ImageIcon img = Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\FilledHeart.png", 25, 25);
                heartLabel.setIcon(img);
            }
        }
    }

    private class SongPlayer extends JPanel {
        private JSlider timeSlider, soundSlider;
        private JLabel pauseAndPlay;
        private JLabel next, previous;
        private JLabel repeat;
        private JLabel shuffle;

        public SongPlayer() {
            super();
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(10, 10));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setOpaque(true);
            setBackground(Essentials.getColor("heavy grey"));

            timeSlider = new JSlider(0, 80, 0);
            timeSlider.setPreferredSize(new Dimension(900, 15));
            timeSlider.setBackground(Essentials.getColor("heavy grey"));

            soundSlider = new JSlider(0, 100, 50);
            soundSlider.setBackground(Essentials.getColor("heavy grey"));
            soundSlider.setPreferredSize(new Dimension(100, 15));

            pauseAndPlay = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon pauseImg = Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\Play.png",40,40);
            pauseAndPlay.setIcon(pauseImg);

            next = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon nextImg = Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\Next.png",25,25);
            next.setIcon(nextImg);

            previous = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon prevImg = Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\Previous.png",25,25);
            previous.setIcon(prevImg);

            shuffle = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon shuffleImg = Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\Shuffle.png",25,25);
            shuffle.setIcon(shuffleImg);

            repeat = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon repeatImg = Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\Repeat.png",25,25);
            repeat.setIcon(repeatImg);
//down: pause and next and previous are in a JLabel
            JLabel temp = Essentials.labelMaker("", "heavy grey", 0, 45);
            temp.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.gridx = 0;
            gbc.gridy = 0;
            temp.add(shuffle, gbc);

            gbc.gridx = 1;
            temp.add(previous, gbc);

            gbc.gridx = 2;
            temp.add(pauseAndPlay, gbc);

            gbc.gridx = 3;
            temp.add(next, gbc);

            gbc.gridx = 4;
            temp.add(repeat, gbc);
//down: JSlider is in a JLabel
            JLabel timeSlideTmp = Essentials.labelMaker("", "heavy grey", 1, 30);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(2, 2, 2, 2);
            timeSlideTmp.setLayout(new GridBagLayout());
            timeSlideTmp.add(timeSlider, gbc);

//down: a small JSlider for sound in a JLabel

            JLabel soundSlideTmp = Essentials.labelMaker("", "yellow", 150, 45);

            JLabel soundIconLabel = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon soundImg =  Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\FilledSound.png",30,25);
            soundIconLabel.setIcon(soundImg);

            soundSlideTmp.setLayout(new BorderLayout());
            soundSlideTmp.add(soundIconLabel, BorderLayout.WEST);
            soundSlideTmp.add(soundSlider, BorderLayout.CENTER);

//adding all 3 together.
            add(temp, BorderLayout.CENTER);
            add(timeSlideTmp, BorderLayout.SOUTH);
            add(soundSlideTmp, BorderLayout.EAST);
        }

        public void setTimeSliderValue(int value) {
            timeSlider.setValue(value);
        }

    }

}

