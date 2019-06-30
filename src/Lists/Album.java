package Lists;

import Music.Song;

import java.io.FileNotFoundException;

public class Album extends List {
    public Album(String name, String description) {
        super(name, description);
    }

    public void setArtwork(Song song) {

        artwork = song.getArtwork();

    }

    @Override
    public void addSong(String path) {
        if (songPath.size() == 0) {
            Song song = null;
            try {
                song = new Song(path);
            } catch (FileNotFoundException e) {
            }
            setArtwork(song);
        }
        songPath.add(path);

        setTotalTime();
    }

}
