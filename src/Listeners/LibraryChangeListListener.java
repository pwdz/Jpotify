package Listeners;

import Lists.List;

public interface LibraryChangeListListener {
    void updateCenter(List list);//after Library checks GUI PlayList clicks on labels, lets MainFrame know which list to show.
    //Src: Library  Des:Mainframe
}
