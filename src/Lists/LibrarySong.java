package Lists;

import Music.Song;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LibrarySong extends Playlist {
    public LibrarySong(String name, String description, byte[] artwork) {
        super(name, description, artwork);
    }
    public ArrayList<Album> buildAlbums(){
        ArrayList<Album> albums=new ArrayList<>();
        Song song;
        boolean found;
        for (String path: this.songPath) {
            try {
                song=new Song(path);
                found=false;
                for (int i = 0; i <albums.size() && !found; i++) {
                    if(song.getAlbum().equals(albums.get(i).getName())){
                        albums.get(i).addSong(song.getPath());
                        found=true;
                    }
                }
                if(!found){
                    albums.add(new Album(song.getAlbum(),""));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        return albums;
    }



}

