package Listeners;

import PlayerPackage.PlayerStatus;

public interface SongPlayerAndGUIListener {
        void sinkSongWithGUI(int count);
        void sinkPauseAndPlay(PlayerStatus playerStatus);
}
