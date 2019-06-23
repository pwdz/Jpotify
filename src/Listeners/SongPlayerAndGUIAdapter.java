package Listeners;

import PlayerPackage.PlayerStatus;

public abstract class SongPlayerAndGUIAdapter implements SongPlayerAndGUIListener{
    public void sinkSongWithGUI(int count){}

    public void sinkPauseAndPlay(PlayerStatus playerStatus){}
}
