package Listeners;

import PlayerPackage.PlayerStatus;

public interface SongPlayerAndGUIListener {
    public void sinkSongWithGUI(double percentage);
    public void sinkPauseAndPlay(PlayerStatus playerStatus);
}
