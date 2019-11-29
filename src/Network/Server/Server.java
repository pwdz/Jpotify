package Network.Server;

import ClientPackage.User;
import com.sun.prism.shader.Solid_RadialGradient_REFLECT_AlphaTest_Loader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    private final int PORT=1235;
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
        while(true)
        {
            try {
                Socket clientSocket  = serverSocket.accept();
                ClientHandler clientHandler =new ClientHandler(clientSocket);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
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
                    Info info=(Info)reader.readObject();
                    inputType(arg,info);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        private void inputType(int arg,Info info)
        {
            switch (arg){
                case 0://new client has joined
                    System.out.println("[In Server]:: #USER#"+info.getSourceUserName()+"#JOINED#");
                    addNewUser(info);
                    alertOtherUsers(info,0);
                    break;
                case 1://request for a file
                    try {
                        sendRequestForFile(info,1);
                        sendTheFile(receiveTheFile());
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2://request for a shared playlist
                    try {
                        sendRequestForFile(info,2);
                        sendTheFile(receiveTheFile());
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
                    if(!objectOutputStreams.get(i).equals(output)) {
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
            int index = searchUser(info.getSourceUserName());
            if(index!=-1) {
                try {
                    outputStreams.get(index).write(arg);
                    outputStreams.get(index).flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("[In Server.java]:: dude what the fuck?");
        }
        private Info receiveTheFile()
        {
            try {
                Info info = (Info) reader.readObject();
                return info;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
        private void sendTheFile(Info info)
        {
            try {
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
        new Server();
    }
}



