package Lists;
import Music.Song;

import java.io.FileNotFoundException;
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

//
//    public void changeOrderTest(int intChange ,int newPlace){
//        newPlace--;
//        ArrayList<Integer> integers=new ArrayList<>();
//        for (Integer i = 0; i <11 ; i++) {
//            integers.add(i);
//        }
//
//        int index=integers.indexOf(intChange);
//        Integer inNewIndex=integers.get(newPlace);
//        Integer arrayOfSongs [] = new Integer[integers.size()];
//        for (int i = 0; i <integers.size() ; i++) {
//            arrayOfSongs[i]=integers.get(i);
//        }
//        if (newPlace<index) {
//            for (int i = index; i>newPlace; i--) {
//                arrayOfSongs[i]=arrayOfSongs[i-1];
//            }
//        }
//
//        if(newPlace>index){
//            for (int i = index; i<newPlace ; i++) {
//                arrayOfSongs[i]=arrayOfSongs[i+1];
//            }
//        }
//        arrayOfSongs[newPlace]=intChange;
//        integers=new ArrayList<>();
//        for (int i = 0; i <arrayOfSongs.length ; i++) {
//            integers.add(arrayOfSongs[i]);
//        }
//        for (int i = 0; i <integers.size() ; i++) {
//            System.out.println(integers.get(i));
//        }
//
//
//    }
//
//    public static void main(String[] args) {
//        try {
//            Song song = new Song("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//            Playlist playlist=new Playlist("myPlaylist"," ",song.getArtwork());
//            playlist.changeOrderTest(3,1);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//
//    }
}

