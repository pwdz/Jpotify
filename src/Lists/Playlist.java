package Lists;
import java.util.ArrayList;

public class Playlist extends List {
    protected boolean isRemovable;
    public Playlist(String name,String description,byte[] artwork){
        super(name,description);
        this.artwork=artwork;
        isRemovable=true;
    }

    public boolean isRemovable() {
        return isRemovable;
    }


    public void removeSong(String path){
        songPath.remove(path);
        setTotalTime();
    }
    public void changeOrder(String path ,int newPlace){
        newPlace--;
        int index=songPath.indexOf(path);
        String arrayOfPaths [] = new String[songPath.size()];
        for (int i = 0; i <songPath.size() ; i++) {
            arrayOfPaths[i]=songPath.get(i);
        }
        if (newPlace<index) {
            for (int i = index; i>newPlace; i--) {
                arrayOfPaths[i]=arrayOfPaths[i-1];
            }
        }
        if(newPlace>index){
            for (int i = index; i<newPlace ; i++) {
                arrayOfPaths[i]=arrayOfPaths[i+1];
            }
        }
        arrayOfPaths[newPlace]=path;
        songPath=new ArrayList<>();
        for (int i = 0; i <arrayOfPaths.length ; i++) {
            songPath.add(arrayOfPaths[i]);
        }
    }

}

