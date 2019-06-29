package Listeners;

import ClientPackage.Library;
import Lists.Album;
import Lists.List;

import java.util.ArrayList;

public interface LibraryChangeListListener {
    void updateCenter(List list, int tag, ArrayList<Album>albums, ArrayList<List>lists);//after Library checks GUI PlayList clicks on labels, lets MainFrame know which list to show.
    //Src: Library  Des:Mainframe
}
