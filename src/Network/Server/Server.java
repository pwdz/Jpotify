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

    public Server()
    {
        try {
            serverSocket = new ServerSocket(PORT);
            inputStreams = new ArrayList<>();
            outputStreams = new ArrayList<>();
            objectInputStreams = new ArrayList<>();
            objectOutputStreams = new ArrayList<>();
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
//        private static
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
                    inputType(arg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        private void inputType(int arg)
        {
            switch (arg){
                case 0://new client has joined
                    inputStreams.add(input);
                    outputStreams.add(output);
                    objectInputStreams.add(reader);
                    objectOutputStreams.add(writer);
                    try {
                        Info info = (Info) reader.readObject();
                        alertOtherUsers(info);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2://request for a file
                    sendRequestForFile();

                    break;
                case 3:
                    break;
            }
        }
        private void alertOtherUsers(Info newInfo)
        {
            for(int i=0;i<outputStreams.size();i++)
            {
                try {
                    if(!objectOutputStreams.get(i).equals(output)) {
                        outputStreams.get(i).write(0);
                        objectOutputStreams.get(i).writeObject(newInfo);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        private void sendRequestForFile()
        {

        }
    }
//////////////////////////////////////////////////
    public static void main(String[] args) {
        new Server();
    }
}



