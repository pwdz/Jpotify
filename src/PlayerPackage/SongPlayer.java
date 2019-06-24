package PlayerPackage;

import Listeners.TimeProgressBarListener;
import Music.Song;
import javazoom.jl.decoder.JavaLayerException;

import javazoom.jl.player.advanced.AdvancedPlayer;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Listeners.SongPlayerAndGUIListener;

public class SongPlayer implements SongPlayerAndGUIListener, TimeProgressBarListener {
    private AdvancedPlayer player;
    private String path;
    private Object playerLock;
    private PlayerStatus playerStatus;
    private FileInputStream fileInputStream;
    private SongPlayerAndGUIListener listener = null;
    private Song song;
    private int frameNumber = 0;

    public SongPlayer(String path) {
        setPath(path);
        try {
            song = new Song(path);
            System.out.println(song.getNumberOfFrames());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        playerLock = new Object();
        playerStatus = PlayerStatus.NOTSTARTED;
    }

    public void setPath(String path) {
        this.path = path;
        try {
            fileInputStream = new FileInputStream(path);
            player = new AdvancedPlayer(fileInputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

    }

    public void playTheSong() {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTSTARTED:

                    Runnable playerRunnable = new Runnable() {
                        @Override
                        public void run() {
                            myPlay();
                        }
                    };
                    Thread playerThread = new Thread(playerRunnable);
                    //playerThread.setDaemon(true);
                    playerThread.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = PlayerStatus.PLAYING;
                    playerThread.start();
                    break;
                case PAUSED:
                    resume();
                    break;

            }
        }

    }

    private void myPlay() {

        while (playerStatus != PlayerStatus.FINISHED) {
            try {
                frameNumber++;
                listener.sinkSongWithGUI((double) frameNumber / song.getNumberOfFrames());
                if (!player.play(1)) {
                    break;
                }
            } catch (JavaLayerException e) {
                break;
            }
            synchronized (playerLock) {
                while (playerStatus == PlayerStatus.PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }
        myClose();
    }

    public void resume() {
        synchronized (playerLock) {
            if (playerStatus == PlayerStatus.PAUSED) {
                playerStatus = PlayerStatus.PLAYING;
                playerLock.notifyAll();
            }
        }
    }

    public void pause() {
        synchronized (playerLock) {
            if (playerStatus == PlayerStatus.PLAYING) {
                playerStatus = PlayerStatus.PAUSED;
//                playerLock.notifyAll();
            }
        }
    }

    public void seek(double percentage) {
        try {
            Song song = new Song(path);
            frameNumber = (int) (percentage * song.getNumberOfFrames());
            seekTo((int) ((percentage) * song.getNumberOfFrames()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void seekTo(int frame) {
        synchronized (playerLock) {
//            System.out.println(playerStatus);
            player.close();
            try {
                fileInputStream = new FileInputStream(path);
                player = new AdvancedPlayer(fileInputStream);
                player.play(frame, frame + 1);

            } catch (JavaLayerException ex) {
                ex.printStackTrace();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void stop() {
        synchronized (playerLock) {
            playerStatus = PlayerStatus.FINISHED;
            playerLock.notifyAll();
        }
    }

    public void myClose() {
        synchronized (playerLock) {
            playerStatus = PlayerStatus.FINISHED;
//            System.out.println(playerStatus);
        }
        try {
            player.close();
            fileInputStream.close();
        } catch (Exception e) {
        }

    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setDestinationToTimeSlider(SongPlayerAndGUIListener destination) {
        this.listener = destination;
    }

    //Down: it has no use.
    @Override
    public void sinkSongWithGUI(double percentage) {
    }

    @Override
    public void sinkPauseAndPlay(PlayerStatus playerStatus) {
        if (playerStatus.equals(PlayerStatus.PAUSED))
            pause();
        else
            resume();
    }

    @Override
    public void seekToFrame(double percentage) {
        seek(percentage);
    }
//    public static void main(String[] args) {
//        try {
//
//            SongPlayer songPlayer=new SongPlayer("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//            songPlayer.playTheSong();
//            Thread.sleep(2000);
//            songPlayer.seekTo(3000 );
//            Thread.sleep(60000);
//
//        }
//        catch (InterruptedException e){
//            e.printStackTrace();
//        }
//
//    }
}


