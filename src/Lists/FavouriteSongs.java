package Lists;

public class FavouriteSongs extends Playlist {
    public FavouriteSongs(String name,String description,byte[] artwork){
        super(name,description,artwork);
        isRemovable=false;
    }
}
