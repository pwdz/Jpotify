package Music;
import java.io.*;
import Mp3agic.ID3v2;
import Mp3agic.InvalidDataException;
import Mp3agic.Mp3File;
import Mp3agic.UnsupportedTagException;
import org.omg.CORBA.PUBLIC_MEMBER;

public class Song {
    private String artist;
    private String title;
    private String album;
    private String path;
    private File file;
    private Mp3File mp3File;
    private ID3v2 id3v2;
    private byte artwork[];
    private int duration;
    private int numberOfFrames;
    public Song(String path) throws FileNotFoundException {
        this.path = path;
        file = new File(path);

        setMetaData();
        try {
            mp3File=new Mp3File(path);
            id3v2=mp3File.getId3v2Tag();
            setArtwork();
            setDuration();
            numberOfFrames=mp3File.getFrameCount();
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
        artwork=id3v2.getAlbumImage();

    }
    public void setDuration(){
       duration= (int) mp3File.getLengthInSeconds();

    }
    public byte[] getArtwork() {
        return artwork;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public File getFile() {
        return file;
    }

    public String getPath() {
        return path;
    }

    public int getNumberOfFrames() {
        return numberOfFrames;
    }

    public int getDuration() {
        return duration;
    }
    //       public static void main(String[] args) {
//        try {
//            Song song = new Song("/Users/taratt/Music/iTunes/iTunes Media/Music/Justin Bieber/Unknown Album/Sorry (Lyric Video).mp3");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }

}


