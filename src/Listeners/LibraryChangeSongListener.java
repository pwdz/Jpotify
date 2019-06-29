package Listeners;

import Music.Song;

public interface LibraryChangeSongListener {
    void changeSong(String newPath,int tag);//tag is for play and pause in start
}
