package Lists;

public class SharedPlaylist extends Playlist {
    public SharedPlaylist(String name){
        super(name);
        isRemovable=false;
    }
}
