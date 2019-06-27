package Listeners;

import Lists.Album;
import Lists.List;

import java.util.ArrayList;

public interface LibraryChangeListListener {
    void updateCenter(List list, int tag, ArrayList<Album>albums);//after Library checks GUI PlayList clicks on labels, lets MainFrame know which list to show.
    //Src: Library  Des:Mainframe
}
