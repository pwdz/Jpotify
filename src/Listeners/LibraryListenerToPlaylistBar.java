package Listeners;

import Lists.Playlist;

public interface LibraryListenerToPlaylistBar {
    void addNewPlaylist(Playlist playlist);//after Library checks that the playlist isn't repeated then let's GUI know there is a new Playlist
    //Src:Library Des:PlayList
    void removePlaylist(Playlist playlist);
}
