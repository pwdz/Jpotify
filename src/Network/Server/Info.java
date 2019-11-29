package Network.Server;

import java.io.Serializable;
import java.security.PublicKey;

public class Info implements Serializable {
    private String targetUserName;
    private String sourceUserName;
    private byte[] fileByteCode;

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

    public String getTargetUserId()
    {
        return targetUserName;
    }
}
