package Serialization;

import Lists.*;

import java.io.*;
import java.util.ArrayList;

public class Serializer {

    public static void writeToFile(ArrayList<List> lists,String filePath){
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
           objectOutputStream.writeObject(lists);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<List> readFromFile(String filePath){
        ArrayList<List> lists=null;

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            lists=(ArrayList<List>) objectInputStream.readObject();

            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lists;

    }

//    public static void main(String[] args) {
//        ArrayList<List>lists=new ArrayList<>();
//         LibrarySong songs = new LibrarySong("Songs", "All songs", imageConverterToByteCode(".\\pics\\Music.png"));
//           FavouriteSongs favouriteSongs = new FavouriteSongs("Favourite Songs", "", imageConverterToByteCode(".\\pics\\Favourite2.png"));
//            SharedPlaylist sharedPlaylist = new SharedPlaylist("Shared playlist", "", imageConverterToByteCode(".\\pics\\Shared.png"));
//        lists.add(new LibrarySong("Songs",""));
//    }
}
