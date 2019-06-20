package PlayerPackage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import Music.Song;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SongPlayer {
    private Player player;
    private Song song;
    private Object playerLock;
    private PlayerStatus playerStatus;
    private FileInputStream fileInputStream;
    public SongPlayer(Song song){
        this.song=song;
        fileInputStream= null;
        try {
            fileInputStream = new FileInputStream(song.getPath());
            player=new Player(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (JavaLayerException e) {
            e.printStackTrace();
        }
        playerLock=new Object();
        playerStatus=PlayerStatus.NOTSTARTED;
    }

    public void setSong(Song song) {
        this.song = song;
        try {
            fileInputStream=new FileInputStream(song.getPath());
            player=new Player(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

    }

    public void playTheSong(){
        synchronized (playerLock){
            switch (playerStatus){
                case NOTSTARTED:
                    Runnable playerRunnable =new Runnable() {
                        @Override
                        public void run() {
                                myPlay();
                        }
                    };
                    Thread playerThread=new Thread(playerRunnable);
                    //playerThread.setDaemon(true);
                    playerThread.setPriority(Thread.MAX_PRIORITY);
                    playerStatus=PlayerStatus.PLAYING;
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
          //  System.out.println(playerStatus);
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

    public void stop() {
        synchronized (playerLock) {
            playerStatus = PlayerStatus.FINISHED;
            playerLock.notifyAll();
        }
    }

    public void myClose(){
        synchronized (playerLock){
            playerStatus=PlayerStatus.FINISHED;
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

//    public static void main(String[] args) {
//        try {
//            Song song = new Song("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//            SongPlayer songPlayer=new SongPlayer(song);
//            songPlayer.playTheSong();
//            Thread.sleep(10000);
//            songPlayer.pause();
//            Thread.sleep(5000);
//            songPlayer.resume();
//            Thread.sleep(5000);
//            songPlayer.pause();
//            Thread.sleep(5000);
//            songPlayer.resume();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch (InterruptedException e){
//            e.printStackTrace();
//        }
//
//    }
}



