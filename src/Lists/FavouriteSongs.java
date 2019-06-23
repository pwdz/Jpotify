package Lists;

public class FavouriteSongs extends Playlist {
    public FavouriteSongs(String name, String description, byte[] artwork, boolean isRemovable) {
        super(name, description, artwork, isRemovable);
        isRemovable = false;
    }
}
