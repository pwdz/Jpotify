import ClientPackage.User;
import Network.Client.Client;

public class Test {
    public static void main(String[] args) {
        User user2 = new User("jafar");
        Client c2 = new Client(user2);
        Thread t = new Thread(){
            public void run(){

                c2.clientReceiver();
            }
        };
        t.start();
        c2.sendRequest(0,"");
        c2.sendRequest(1,"ali");
//        c2.sendRequest(1,"ali");

    }
}
