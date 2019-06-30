package PlayerPackage;

import Listeners.*;
import Lists.List;
import Music.Song;
import Network.GetCurrentSongFromSongPlayerForClient;
import Network.GiveCurrentSongToClient;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SongPlayer implements SongPlayerAndGUIListener, GetCurrentSongFromSongPlayerForClient,TimeProgressBarListener, SoundSliderListener, LibraryChangeSongListener, PlayBarNxtAndPreListener {
    private AdvancedPlayer player;
    private String path;
    private Object playerLock;
    private PlayerStatus playerStatus;
    private FileInputStream fileInputStream;
    private SongPlayerAndGUIListener listener = null;
    private Song song;
    private List currentList;
    private int index;
    private int frameNumber = 0;
    private GiveCurrentSongToClient giveCurrentSongToClient;
    private SongPlayerChangeSongListener songPlayerChangeSongListener;

    public SongPlayer() {
        playerLock = new Object();
        playerStatus = PlayerStatus.NOTSTARTED;
        index = 0;
    }

    public void setCurrentList(List curList) {
        currentList = curList;
    }

    public void setPath(String path) {
        this.path = path;
        try {
            fileInputStream = new FileInputStream(path);
            player = new AdvancedPlayer(fileInputStream);
            player.setVol(6f);
            song = new Song(path);

        } catch (FileNotFoundException e) {
        } catch (JavaLayerException e) {
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
//                if (frameNumber % 100 == 0)
                listener.syncSongWithGUI((double) frameNumber / song.getNumberOfFrames());
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
        listener.syncSongWithGUI(1);
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
        }
    }

    public void seekTo(int frame) {
        synchronized (playerLock) {
            player.close();
            try {
                fileInputStream = new FileInputStream(path);
                player = new AdvancedPlayer(fileInputStream);
                player.play(frame, frame + 1);

            } catch (JavaLayerException ex) {

            } catch (FileNotFoundException e) {
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
//            (playerStatus);
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
    public void syncSongWithGUI(double percentage) {
    }

    @Override
    public void syncPauseAndPlay(PlayerStatus playerStatus) {
        if (playerStatus.equals(PlayerStatus.PAUSED))
            pause();
        else
            resume();
    }

    @Override
    public void seekToFrame(double percentage) {
        seek(percentage);
    }

    @Override
    public void setVolume(float percentage) {
        player.setVol(percentage * 86 - 80);

    }

    @Override
    public void changeSong(String newPath, int tag) {

        try {
            setPath(newPath);

            songPlayerChangeSongListener.changeArtworkAndStuff(new Song(newPath), tag);
            listener.syncSongWithGUI(0);
            frameNumber = 0;
            playTheSong();
        } catch (FileNotFoundException e) {
        }

    }

    public void setSongPlayerChangeSongListener(SongPlayerChangeSongListener listener) {
        songPlayerChangeSongListener = listener;
    }

    @Override
    public void goNxtOrPre(int tag) {
        frameNumber = 0;
        if (tag == 1) {
            if (index > 0) {
                index--;
                setPath(currentList.getSongsPaths().get(index));
            } else {
                index = currentList.getSongsPaths().size() - 1;
                setPath(currentList.getSongsPaths().get(index));
            }
        } else {//2
            if (index < currentList.getSongsPaths().size() - 1) {
                index++;
                setPath(currentList.getSongsPaths().get(index));
            } else {
                index = 0;
                setPath(currentList.getSongsPaths().get(index));
            }
        }
    }

    @Override
    public void giveSongToClient() {
        giveCurrentSongToClient.currentSongPath(path);
    }
    public void setGiveCurrentSongToClient(GiveCurrentSongToClient linker)
    {
        giveCurrentSongToClient = linker;
    }
}


