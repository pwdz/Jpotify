package Listeners;

import PlayerPackage.PlayerStatus;

public interface SongPlayerAndGUIListener {
    public void sinkSongWithGUI(int count);
    public void sinkPauseAndPlay(PlayerStatus playerStatus);
}
