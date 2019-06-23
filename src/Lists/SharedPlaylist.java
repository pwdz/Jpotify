package Lists;

public class SharedPlaylist extends Playlist {
    public SharedPlaylist(String name, String description, byte[] artwork, boolean isRemovable) {
        super(name, description, artwork, isRemovable);
        isRemovable=false;
    }
}