package Lists;

public class SharedPlaylist extends Playlist {
    public SharedPlaylist(String name,String description ,byte artwork[]){
        super(name,description,artwork);
        isRemovable=false;
        this.artwork=artwork;
    }
}
