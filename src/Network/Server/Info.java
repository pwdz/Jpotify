package Network.Server;

import java.io.Serializable;
import java.security.PublicKey;

public class Info implements Serializable {
    private String targetUserName=null;
    private String sourceUserName=null;
    private byte[] fileByteCode=null;

    public byte[] getFileByteCode() {
        return fileByteCode;
    }

    public void setFileByteCode(byte[] fileByteCode) {
        this.fileByteCode = fileByteCode;
    }

    public String getSourceUserName() {
        return sourceUserName;
    }

    public void setSourceUserName(String sourceUserName) {
        this.sourceUserName = sourceUserName;
    }

    public void setTargetUserName(String targetUserName) {
        this.targetUserName = targetUserName;
    }

    public String getTargetUserName()
    {
        return targetUserName;
    }

    @Override
    public String toString()
    {
        if (getFileByteCode()!=null)
            return "targetUserName:"+getTargetUserName()+"\n"+"sourceUserName"+getSourceUserName()+"\nfileByteCode:"+"true";

        return "targetUserName:"+getTargetUserName()+"\n"+"sourceUserName"+getSourceUserName()+"\nfileByteCode:"+"false";
    }
}
