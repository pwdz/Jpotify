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
        } catch (IOException e) {
        }
    }
    public static ArrayList<List> readFromFile(String filePath){
        ArrayList<List> lists=null;

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            lists=(ArrayList<List>) objectInputStream.readObject();

            } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return lists;

    }
}
