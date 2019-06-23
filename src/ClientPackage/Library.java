package ClientPackage;

import java.util.ArrayList;
import Lists.*;
import Music.Song;
public class Library {
    private ArrayList<List> lists;
    private ArrayList<Song> songs;
    public Library(){
        lists=new ArrayList<>();
        songs=new ArrayList<>();
    }
    public void addSong(Song song){
        songs.add(song);
    }
    public void addList(List list){
        lists.add(list);
    }
}
