package Network;

import java.io.OutputStream;

public class ClientSender implements Runnable{
    private OutputStream outputStream;

    public ClientSender(OutputStream outputStream) {
        this.outputStream=outputStream;
    }

    @Override
    public void run() {

    }
}
