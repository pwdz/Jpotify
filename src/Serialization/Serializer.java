package Serialization;

import Lists.Album;
import Lists.List;

import java.io.*;
import java.util.ArrayList;

public class Serializer {

    public static void writeToFile(ArrayList<List> lists,String filePath){
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(new File(filePath));
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            for (List list :lists) {
               objectOutputStream.writeObject(list);
            }

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<List> readFromFile(String filePath){
        ArrayList<List> lists=new ArrayList<>();
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = new FileInputStream(new File(filePath));
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            boolean continues=true;
            while(continues){
                List list=(List)objectInputStream.readObject();
                if(list!=null){
                    lists.add(list);
                }
                else
                {
                    continues=false;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lists;

    }
}
