package Lists;
import Music.Song;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

    public class List implements Serializable {
        protected ArrayList<String> songPath;
        protected String name;
        protected byte[] artwork;
        protected int totalTime;
        protected String description;
        public List(String name,String description)
        {
            this.description=description;
            this.name=name;
            songPath=new ArrayList<>();
            totalTime=0;
            setTotalTime();

        }
        public void addSong(String path){
            songPath.add(path);
            setTotalTime();
        }
        public String getName()
        {
            return name;
        }

        public ArrayList<String> search(String key){
            Song song;
            ArrayList<String> foundItems=new ArrayList<>();
            for (String path: songPath) {
                try {
                    song=new Song(path);
                    if(song.getTitle().contains(key))
                        foundItems.add(path);
                    if(song.getAlbum().contains(key))
                        foundItems.add(path);
                    if(song.getArtist().contains(key))
                        foundItems.add(path);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
            return foundItems;
        }
        public ArrayList<String> shuffle(){
            ArrayList<String> shuffledsongPath=new ArrayList<>();
            for (int i = 0; i < songPath.size(); i++) {
                shuffledsongPath.add(songPath.get(i));
            }
            Collections.shuffle(shuffledsongPath);
            return shuffledsongPath;
        }
        public String nextSong(String path){
            int index= songPath.indexOf(path);
            if(index!=songPath.size()-1)
                return songPath.get(index+1);
            else
                return songPath.get(0);

        }
        public String previousSong(String path){
            int index=songPath.indexOf(path);
            if(index!=0)
                return songPath.get(index-1);
            else
                return songPath.get(songPath.size()-1);
        }

    public ArrayList<String> getSongsPaths() {
        return songPath;
    }
//    public void addSong(String path)
//    {
//        songPath.add(path);
//    }
    public void setTotalTime(){
            totalTime=0;
            Song song;
        for (String path: songPath) {
            try {
                song=new Song(path);
                totalTime+=song.getDuration();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
    public String totalTimeToString(){
            int seconds= totalTime%60;
            int minutes=totalTime/60;
            if (seconds>=10)
                return minutes+":"+seconds;
            else
                return minutes+":0"+seconds;
    }
        public byte[] getArtwork() {
            return artwork;
        }
    }
