package ClientPackage;

import java.util.ArrayList;

import Listeners.AddPlaylistListener;
import Lists.*;
import Music.Song;
public class Library implements AddPlaylistListener {
    private ArrayList<List> lists;
//    private ArrayList<Song> songs;
    public Library(){
        lists=new ArrayList<>();
//        songs=new ArrayList<>();
    }

//    public void addSong(Song song){
//        songs.add(song);
//    }
    public void addList(List list){
        lists.add(list);
    }

    @Override
    public void makePlaylist(String name, String description, byte[] playlistArtwork) {
        Playlist playlist = new Playlist(name,description,playlistArtwork,true);
        lists.add(playlist);
//        System.out.println(name);
    }
}
