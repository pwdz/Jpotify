package GUI;

import Listeners.SongPlayerAndGUIListener;
import Listeners.TimeProgressBarListener;
import Music.Song;
import PlayerPackage.PlayerStatus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    public void setTime(int count) {
        songPlayer.setTimeProgressValue(count);
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

            ImageIcon imageIcon = Essentials.imageProvider(".\\pics\\EmptyHeart.png", 25, 25);
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
                ImageIcon img = Essentials.imageProvider(".\\pics\\EmptyHeart.png", 25, 25);
                heartLabel.setIcon(img);
            } else {
                isLiked = true;
                ImageIcon img = Essentials.imageProvider(".\\pics\\FilledHeart.png", 25, 25);
                heartLabel.setIcon(img);
            }
        }
    }

    private class SongPlayer extends JPanel implements SongPlayerAndGUIListener {
        private JSlider  soundSlider;
        private JProgressBar timeProgress;
        private JLabel pauseAndPlay;
        private JLabel next, previous;
        private JLabel repeat;
        private JLabel shuffle;
        private PlayerStatus playerStatus = PlayerStatus.PAUSED;
        private SongPlayerAndGUIListener songPlayerAndGUIListener = null;
        private final int timeProgressMax = 5000;
        private TimeProgressBarListener timeProgressListener;

        public SongPlayer() {
            super();
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(10, 10));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setOpaque(true);
            setBackground(Essentials.getColor("heavy grey"));

            timeProgress = new JProgressBar(0,0,timeProgressMax);
            timeProgress.setPreferredSize(new Dimension(900, 15));
            timeProgress.setBackground(Essentials.getColor("heavy grey"));
            timeProgress.setValue(0);
            addTimeProgressListener();


            soundSlider = new JSlider(0, 100, 50);
            soundSlider.setBackground(Essentials.getColor("heavy grey"));
            soundSlider.setPreferredSize(new Dimension(100, 15));

            pauseAndPlay = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon pauseImg = Essentials.imageProvider(".\\pics\\Play.png", 40, 40);
            pauseAndPlay.setIcon(pauseImg);
            pauseAndPlay.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    switch (playerStatus) {
                        case PAUSED:
                            pauseAndPlay.setIcon(Essentials.imageProvider(".\\pics\\Pause.png", 40, 40));
                            playerStatus = PlayerStatus.PLAYING;
                            songPlayerAndGUIListener.sinkPauseAndPlay(playerStatus);
                            break;
                        case PLAYING:
                            pauseAndPlay.setIcon(Essentials.imageProvider(".\\pics\\Play.png", 40, 40));
                            playerStatus = PlayerStatus.PAUSED;
                            songPlayerAndGUIListener.sinkPauseAndPlay(playerStatus);
                            break;
                    }
                }
            });

            next = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon nextImg = Essentials.imageProvider(".\\pics\\Next.png", 25, 25);
            next.setIcon(nextImg);

            previous = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon prevImg = Essentials.imageProvider(".\\pics\\Previous.png", 25, 25);
            previous.setIcon(prevImg);

            shuffle = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon shuffleImg = Essentials.imageProvider(".\\pics\\Shuffle.png", 25, 25);
            shuffle.setIcon(shuffleImg);

            repeat = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon repeatImg = Essentials.imageProvider(".\\pics\\Repeat.png", 25, 25);
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
            JLabel timeProgressTmp = Essentials.labelMaker("", "heavy grey", 1, 30);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(2, 2, 2, 2);
            timeProgressTmp.setLayout(new GridBagLayout());
            timeProgressTmp.add(timeProgress, gbc);

//down: a small JSlider for sound in a JLabel

            JLabel soundSlideTmp = Essentials.labelMaker("", "yellow", 150, 45);
//            soundSlideTmp.addMouseListener();

            JLabel soundIconLabel = Essentials.labelMaker("", "heavy grey", 45, 45);
            ImageIcon soundImg = Essentials.imageProvider(".\\pics\\FilledSound.png", 30, 25);
            soundIconLabel.setIcon(soundImg);

            soundSlideTmp.setLayout(new BorderLayout());
            soundSlideTmp.add(soundIconLabel, BorderLayout.WEST);
            soundSlideTmp.add(soundSlider, BorderLayout.CENTER);

//adding all 3 together.
            add(temp, BorderLayout.CENTER);
            add(timeProgressTmp, BorderLayout.SOUTH);
            add(soundSlideTmp, BorderLayout.EAST);

        }
//        public void setPauseAndPlayDestination(SongPlayerAndGUIListener destination)
//        {
//            songPlayerAndGUIListener=destination;
//        }

        public void setTimeProgressValue(int value) {
//            System.out.println("PER*100:"+value);
            timeProgress.setValue(value);
        }

        @Override
        public void sinkSongWithGUI(double percentage) {
            setTimeProgressValue((int) (percentage * timeProgressMax));
//            System.out.println("PER:"+percentage);
        }

        //Down: It must stay empty. doesn't have any use.
        @Override
        public void sinkPauseAndPlay(PlayerStatus playerStatus) {
        }

        private void addTimeProgressListener() {
//            timeProgress.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    timeProgressListener.seekToFrame(((double) timeProgress.getValue()/timeProgressMax));
//                    System.out.println(timeProgress.getValue());
//                }
//            });
            timeProgress.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseReleased(e);
                    int v = timeProgress.getValue();
//                    jlabel.setText("----"+v);

                    //Retrieves the mouse position relative to the component origin.
                    int mouseX = e.getX();

                    //Computes how far along the mouse is relative to the component width then multiply it by the progress bar's maximum value.
                    int progressBarVal = (int)Math.round(((double)mouseX / (double)timeProgress.getWidth()) * timeProgress.getMaximum());

                    timeProgress.setValue(progressBarVal);
                    System.out.println("ASDLASD:"+(double) progressBarVal/timeProgressMax);
                    timeProgressListener.seekToFrame((double)progressBarVal/timeProgressMax);

                }
            });
//            timeProgress.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    timeProgressListener.seekToFrame((double) timeProgress.getValue() / timeProgressMax);
//                    System.out.println((double) timeProgress.getValue() / timeProgressMax);
//                }
//            });
        }
    }

    ////////////////////////////////////////////////////////
    public void setPauseAndPlayLabel(SongPlayerAndGUIListener destination) {
        songPlayer.songPlayerAndGUIListener = destination;
    }

    public SongPlayerAndGUIListener getSongPlayerTypeListener() {
        return songPlayer;
    }

    public JPanel getSongPlayer() {
        return songPlayer;
    }

    public void setTimeSliderListener(TimeProgressBarListener listener) {
        songPlayer.timeProgressListener = listener;
    }
    ///////////////////////////////PlayerBar class
//    public void setPauseAndPlayDestination(SongPlayerAndGUIListener destination)
//    {
//        songPlayer.setPauseAndPlayDestination(destination);
//    }

}

