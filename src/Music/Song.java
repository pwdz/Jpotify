package Music;
import java.io.*;
import Mp3agic.ID3v2;
import Mp3agic.InvalidDataException;
import Mp3agic.Mp3File;
import Mp3agic.UnsupportedTagException;
public class Song {
    private String artist;
    private String title;
    private String album;
    private String path;
    private File file;
    private byte artwork[];

    public Song(String path) throws FileNotFoundException {
        this.path = path;
        file = new File(path);
        setMetaData();
        try {
            setArtwork();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        }
    }

    public void setMetaData() throws FileNotFoundException {
        long lengthOfFile = file.length();
        byte tagBytes[] = new byte[(int) lengthOfFile];
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            fileInputStream.skip(lengthOfFile - 128);
            fileInputStream.read(tagBytes);
            String metadata = new String(tagBytes);
            title = metadata.substring(3, 32).trim();
            artist = metadata.substring(33, 62).trim();
            album = metadata.substring(63, 92).trim();
          // System.out.println(title+"      "+artist+"      "+album);
            fileInputStream.close();
        } catch (IOException e) {
        }
    }

    public void setArtwork() throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3File=new Mp3File(path);
        ID3v2 id3v2=mp3File.getId3v2Tag();
        artwork=id3v2.getAlbumImage();

    }

    public byte[] getArtwork() {
        int a=1;
        return artwork;
    }

    public File getFile() {
        return file;
    }

    public String getPath() {
        return path;
    }
}
