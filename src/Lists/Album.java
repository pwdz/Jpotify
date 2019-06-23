package Lists;

        import Music.Song;

        import java.io.FileNotFoundException;
        import java.util.ArrayList;

public class  Album extends List {
    byte artwork[];
    public Album(String name){
        super(name);
        setArtwork();
    }
    public void setArtwork(){
        Song song= null;
        try {
            song = new Song(songPath.get(0));
            artwork=song.getArtwork();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
