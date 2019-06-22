package Lists;

import Music.Song;

import java.util.ArrayList;

public class Album extends List {
    public Album(String name){
        super(name);
        setArtwork();
    }
    @Override
    public ArrayList<Song> getSongs(){
        return songs;
    }
    @Override
    public String getName() {
        return super.getName();
    }
    public void setArtwork(){
        artwork=songs.get(0).getArtwork();
    }

}

