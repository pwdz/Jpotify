package Lists;

        import Music.Song;

        import java.io.FileNotFoundException;
        import java.util.ArrayList;

public class  Album extends List {
    public Album(String name){
        super(name);
    }
    public void setArtwork(Song song){
      
            artwork=song.getArtwork();
       
    }
    public void addSong(String path){
        if(songPath.size()==0){
            Song song = null;
            try {
                song = new Song(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            setArtwork(song);
        }
        songPath.add(path);
    }

}
