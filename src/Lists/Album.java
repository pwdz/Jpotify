package Lists;

import Music.Song;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Album extends List {
    public Album(String name, String description, byte[] artwork) {
        super(name, description, artwork);
    }

    public void setArtwork() {
        Song song = null;
        try {
            song = new Song(songPath.get(0));
            artwork = song.getArtwork();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
