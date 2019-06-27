package Network.Client;
import java.io.InputStream;
public class ClientReceiver implements Runnable{
    private InputStream inputStream;
    public ClientReceiver(InputStream inputStream){
        this.inputStream=inputStream;
    }

    @Override
    public void run() {

    }
}
