package Lists;

public class OrdinaryPlaylist extends Playlist {
    private boolean isRemoved;

    public OrdinaryPlaylist(String name, String description, byte[] artwork, boolean isRemovable, boolean isRemoved) {
        super(name, description, artwork, isRemovable);
        this.isRemoved = isRemoved;
    }

    public void rename(String newName){
        name=newName;
    }
    public boolean isRemoved() {
        return isRemoved;
    }
    public void removePlaylist(){
        isRemoved=true;
    }
}
