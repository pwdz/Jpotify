package Network;

import sun.plugin2.message.Serializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class Info implements Serializable {
    private static HashMap<String, InputStream> clientInputs;
    private static HashMap<String, OutputStream> clientOutputs;
    private int type;
    private byte[] songFile;
    private String from;
    private String to;

    public Info(int type) {
        this.type = type;
    }

    public void setSongFile(byte[] songFile) {
        this.songFile = songFile;
    }

    public void setFrom(String from)
    {
        this.from= from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getType()
    {
        return type;
    }

    public byte[] getSongFile() {
        return songFile;
    }
}
