package Network.Client;
import java.io.InputStream;
public class ClientReceiver implements Runnable{
    private InputStream input;
    public ClientReceiver(InputStream inputStream){
        input=inputStream;
    }

    @Override
    public void run() {

    }
}
