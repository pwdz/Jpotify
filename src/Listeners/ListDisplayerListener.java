package Listeners;

import Lists.List;
import Lists.ListType;

public interface ListDisplayerListener {
    void changeSongInLibrary(String songName);
    void addSongToFavourites(String songName);
    void removeSongFromPlaylist(List list, String songName);
    void addSongToPlaylist(String playlistName,String songName);
}
