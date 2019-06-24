package Lists;

public class OrdinaryPlaylist extends Playlist {
    private boolean isRemoved;
    public OrdinaryPlaylist (String name, String description,byte artwork[]){
        super(name,description,artwork);
        isRemovable=true;
        isRemoved=false;
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
