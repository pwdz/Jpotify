package Network.Client;

import java.io.OutputStream;

public class ClientSender {
    private OutputStream output;
    public ClientSender(OutputStream outputStream) {
        this.output = outputStream;
    }


}