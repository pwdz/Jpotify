package Lists;

import Music.Song;

import java.util.ArrayList;

public class Playlist extends List {
    protected boolean isRemovable;
    public Playlist(String name){
        super(name);
    }

    public boolean isRemovable() {
        return isRemovable;
    }

    public void addSong(Song song){
        songs.add(song);
    }
    public void removeSong(Song song){
        songs.remove(song);
    }
    public void changeOrder(Song song ,int newPlace){
        int index=songs.indexOf(song);
        Song arrayOfSongs [] = new Song[songs.size()];
        for (int i = 0; i <songs.size() ; i++) {
            arrayOfSongs[i]=songs.get(i);
        }
        if (newPlace<index) {
            for (int i = index; i>newPlace; i--) {
                arrayOfSongs[i]=arrayOfSongs[i-1];
            }
        }
        if(newPlace>index){
            for (int i = index; i<newPlace ; i++) {
                arrayOfSongs[i]=arrayOfSongs[i+1];
            }
        }
        arrayOfSongs[newPlace]=song;
        songs=new ArrayList<>();
        for (int i = 0; i <arrayOfSongs.length ; i++) {
            songs.add(arrayOfSongs[i]);
        }
    }


//    public void changeOrderTest(int intChange ,int newPlace){
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

//    }

//    public static void main(String[] args) {
//        Playlist playlist=new Playlist("myPlaylist");
//        playlist.changeOrderTest(4,7);
//
//    }
}

