package Serialization;

import Lists.Album;
import Lists.List;

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
//        ArrayList<List> lists=new ArrayList<>();
//        List list=new List("fh","");
//        List list1=new List("fhf","");
//        lists.add(list);
//        lists.add(list1);
//        Serializer.writeToFile(lists,"./testFile.bin");
//        ArrayList lists1=readFromFile("./testFile.bin");
//        List listRead=(List)lists1.get(0);
//        System.out.println(listRead.getName());
//
//    }
}
