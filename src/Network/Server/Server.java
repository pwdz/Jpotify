package Network.Server;

import ClientPackage.User;
import com.sun.prism.shader.Solid_RadialGradient_REFLECT_AlphaTest_Loader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    private final int PORT=1500;
    private ServerSocket serverSocket;
    private ClientHandler clientHandler;
    private static ArrayList<InputStream> inputStreams; //Users inputStreams
    private static ArrayList<OutputStream> outputStreams;//Users outputStreams
    private static ArrayList<ObjectInputStream> objectInputStreams;
    private static ArrayList<ObjectOutputStream> objectOutputStreams;
    private static ArrayList<String> usernames;
    public Server()
    {
        try {
            serverSocket = new ServerSocket(PORT);
            inputStreams = new ArrayList<>();
            outputStreams = new ArrayList<>();
            objectInputStreams = new ArrayList<>();
            objectOutputStreams = new ArrayList<>();
            usernames = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        int count =0;
        while(true)
        {
            try {
                System.out.println("Listening...");
                Socket clientSocket  = serverSocket.accept();
                System.out.println("client"+count+" came!");
                count++;
                ClientHandler clientHandler =new ClientHandler(clientSocket);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }
    }
    private class ClientHandler extends Thread
    {
        private InputStream input;
        private OutputStream output;
        private ObjectOutputStream writer;
        private ObjectInputStream reader;


        public ClientHandler(Socket clientSocket) {
            System.out.println("created");
            try {
                input = clientSocket.getInputStream();
                output = clientSocket.getOutputStream();
                writer = new ObjectOutputStream(output);
                reader = new ObjectInputStream(input);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            serverReceiver();
        }
        private void serverReceiver()
        {
            while (true)
            {
                try {
                    int arg = input.read();
                    System.out.println("dafaq mard?!");
                    Info info=(Info)reader.readObject();
                    inputType(arg,info);
                    System.out.println("fuck:|");
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        private void inputType(int arg,Info info)
        {
            switch (arg){
                case 0://new client has joined
//                    System.out.println("[In Server]:: #USER#"+info.getSourceUserName()+"#JOINED#");
                    addNewUser(info);
                    alertOtherUsers(info,0);
                    break;
                case 1://request for a file[needs target username]
//                    try {
                        System.out.println("In Server:: a request from #"+info.getSourceUserName()+"# for a song from"+ info.getTargetUserName()+"is here");
                        sendRequestForFile(info,1);

                        System.out.println("fuck2:|");
//                        sendTheFile(receiveTheFile());
//                        writer.flush();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    break;
                case 11://song is sent from target user
                    sendTheFile(info,11);
                    try {
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2://request for a shared playlist
//                    try {
                        sendRequestForFile(info,2);
//                        sendTheFile(info);
//                        writer.flush();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    break;
                case 22://response from target user for shared playlist
                    sendTheFile(info,22);
                    try {
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3://current song that is being played
                    alertOtherUsers(info,3);
                    break;
            }
        }
        private void addNewUser(Info info)
        {
            inputStreams.add(input);
            outputStreams.add(output);
            objectInputStreams.add(reader);
            objectOutputStreams.add(writer);
            usernames.add(info.getSourceUserName());
        }
        private void alertOtherUsers(Info newInfo,int arg)
        {
            for(int i=0;i<outputStreams.size();i++)
            {
                try {
                    if(!usernames.get(i).equals(newInfo.getSourceUserName())) {
                        outputStreams.get(i).write(arg);
                        outputStreams.get(i).flush();
                        objectOutputStreams.get(i).writeObject(newInfo);
                        objectOutputStreams.get(i).flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        private void sendRequestForFile(Info info,int arg)//file can be a song or shared playlist
        {
            System.out.println("bitch1");
            int index = searchUser(info.getTargetUserName());
            if(index!=-1) {
                try {
                    System.out.println("bitch2 arg is:"+arg);
                    outputStreams.get(index).write(arg);
                    outputStreams.get(index).flush();
                    objectOutputStreams.get(index).writeObject(info);
                    System.out.println("bitch3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("[In Server.java]:: no such a user!");
        }
//        private Info receiveTheFile()
//        {
//            try {
//                System.out.println("akhe gozo :|");
//                Info info = (Info) reader.readObject();
//                System.out.println("file is in server from target user");
//                return info;
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
        private void sendTheFile(Info info,int arg)
        {
            try {
                output.write(arg);
                writer.writeObject(info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**
         * input username(String)
         * return index(int)
         */
        private int searchUser(String username)
        {
            for(int i=0;i<usernames.size();i++)
                if(usernames.get(i).equals(username))
                    return i;
            return -1;
        }
    }
//////////////////////////////////////////////////
    public static void main(String[] args) {
        Server s = new Server();
        new Thread(s).start();
    }
}



