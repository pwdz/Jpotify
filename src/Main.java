import ClientPackage.User;
import Network.Client.Client;

import javax.jws.soap.SOAPBinding;

public class Main {
    public static void main(String[] args) {
        User user = new User("ali");
//        User user2 = new User("jafar");

        Client c1=new Client(user);
        c1.sendRequest(0,"");
        c1.clientReceiver();

    }
}
