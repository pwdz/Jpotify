package Lists;
import Music.Song;

import java.util.ArrayList;

public class List {
    protected ArrayList<Song> songs;
    protected String name;

    public List(String name)
    {
        this.name=name;
        songs=new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
