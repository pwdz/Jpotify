package Listeners;

import PlayerPackage.PlayerStatus;

public interface SongPlayerAndGUIListener {
    public void syncSongWithGUI(double percentage);
    public void syncPauseAndPlay(PlayerStatus playerStatus);
}
