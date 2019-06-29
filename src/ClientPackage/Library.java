package ClientPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import GUI.Essentials;
import Listeners.*;
import Lists.*;
import Music.Song;
import Serialization.Serializer;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Library implements AddPlaylistListener, ChooseSongListener, ListGUIListener, CloseWindowListener, ListDisplayerListener,PlayListRemoveListener {
    private ArrayList<List> lists;
    private ArrayList<Album> albums;
    private SharedPlaylist sharedPlaylist;
    private FavouriteSongs favouriteSongs;
    private LibrarySong songs;
    private LibraryListenerToPlaylistBar libraryListenerToPlaylistBar;
    private List currentList;
    private LibraryChangeListListener libraryChangeListListener;
    private LibraryChangeSongListener libraryChangeSongListener;

    //    private ArrayList<Song> songs;
    public Library(ArrayList<List> fileLists) {
//        this.lists = fileLists;
        lists = new ArrayList<>();
        if (fileLists == null) {//1.No such a file 2.Empty file
            System.out.println("hi");
            songs = new LibrarySong("Songs", "All songs", imageConverterToByteCode(".\\pics\\Music.png"));
            favouriteSongs = new FavouriteSongs("Favourite Songs", "", imageConverterToByteCode(".\\pics\\Favourite2.png"));
            sharedPlaylist = new SharedPlaylist("Shared playlist", "", imageConverterToByteCode(".\\pics\\Shared.png"));
            this.lists.add(songs);
            this.lists.add(favouriteSongs);
            this.lists.add(sharedPlaylist);
            Serializer.writeToFile(this.lists, ".\\SaveFiles\\saved.bin");

        } else {//restore from file
            songs = (LibrarySong) fileLists.get(0);
            lists.add(fileLists.get(0));

            favouriteSongs = (FavouriteSongs) fileLists.get(1);
            lists.add(fileLists.get(1));

            sharedPlaylist = (SharedPlaylist) fileLists.get(2);
            lists.add(fileLists.get(2));

            for (int i = 3; i < fileLists.size(); i++) {
                lists.add((Playlist) fileLists.get(i));
            }

        }
        currentList = songs;
        albums = songs.buildAlbums();//EBI: check it!

        System.out.println("list size:" + lists.size());
    }

    public void setStartSong() {
        if (songs.getSongsPaths().size() > 0)
            libraryChangeSongListener.changeSong(songs.getSongsPaths().get(0),1);
    }

    public void organizePlaylistPanelInStart() {
        for (int i = 3; i < lists.size(); i++)
            libraryListenerToPlaylistBar.addNewPlaylist((Playlist) lists.get(i));
    }

    public void addList(List list) {
        lists.add(list);
    }

    @Override
    public void makePlaylist(String name, String description, String artworkPath) {

        Playlist playlist = new Playlist(name, description, imageConverterToByteCode(artworkPath));

        if (!lists.contains(playlist)) {
            addList(playlist);
            libraryListenerToPlaylistBar.addNewPlaylist(playlist);
        }

    }

    @Override
    public void addSongToLibrary(String songPath) {
        if (!songs.getSongsPaths().contains(songPath)) {
            songs.addSong(songPath);
            albums = songs.buildAlbums();
        }
//        System.out.println(songPath);
    }

    public void setLibraryListenerToPlaylistBar(LibraryListenerToPlaylistBar listener) {
        libraryListenerToPlaylistBar = listener;
    }

    private byte[] imageConverterToByteCode(String path) {
        File artworkFile = new File(path);
        byte[] artworkByteCode;
        try {
            artworkByteCode = Files.readAllBytes(artworkFile.toPath());
            return artworkByteCode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<List> getLists() {
        return lists;
    }

    @Override
    public void listClicked(ListType listType, String listName) {
        int tag = 1;
        switch (listType) {
            case LibrarySong://Songs
                currentList = songs;
                break;
            case FavouriteSong://Favourite Songs
                currentList = favouriteSongs;
                break;
            case SharedPlaylist://Shared playlist
                System.out.println(":||||||");
                currentList = sharedPlaylist;
                break;
            case Album://Albums
                if (listName.equals(""))
                    tag = 2;
                else {
                    tag = 1;
                    currentList = searchForAlbum(listName);
//                    System.out.println("yaaaaayyyaaaaaaaaaa");
                }
//                System.out.println("LISTCLICKED:"+albums.get(0).size());
                //code here
                break;
            case Playlist:
                if (searchForPlaylist(listName) != null) {
                    currentList = searchForPlaylist(listName);
                }
                break;
        }

        libraryChangeListListener.updateCenter(currentList, tag, albums, lists);
    }

    private Playlist searchForPlaylist(String playlistName) {
        System.out.println("PlaylistName:" + playlistName);
        for (List list : lists) {
            System.out.println("N:" + list.getName());
            if (list instanceof Playlist) {
                if ((list).getName().equals(playlistName))
                    if (((Playlist) list).isRemovable())//if removable is true then it's not Fav and Shared playlist.
                    {
                        return (Playlist) list;
                    }
            }
        }
        return null;
    }

    private Album searchForAlbum(String name) {
        for (Album album : albums) {
            if (album.getName().equals(name))
                return album;
        }
        return null;
    }

    public void setLibraryChangeListListener(LibraryChangeListListener listener) {
        libraryChangeListListener = listener;
    }

    public void refresh(String path) {
        songs.changeOrder(path, 1);
        Album albumFound = null;
        Album arrayOfAlbum[] = (Album[]) albums.toArray();
        int index = 0;
        for (Album album : albums) {
            if (album.getSongsPaths().contains(path)) {
                albumFound = album;
                index = albums.indexOf(album);
            }
            break;
        }

        for (int i = index; i > 0; i--) {
            arrayOfAlbum[i] = arrayOfAlbum[i - 1];
        }
        albums = new ArrayList<>();
        arrayOfAlbum[0] = albumFound;
        for (int i = 0; i < arrayOfAlbum.length; i++) {
            albums.add(arrayOfAlbum[i]);
        }

    }

    public LibrarySong getSongs() {
        return songs;
    }

    @Override
    public void windowClosed() {
//        System.out.println("*@*@*@*@*@*@*@*@");
        Serializer.writeToFile(lists, ".\\SaveFiles\\saved.bin");
    }

    @Override
    public void changeSongInLibrary(String songName) {
        String newPath = songs.searchForSong(songName);
//        System.out.println("newPath:" + newPath);
        libraryChangeSongListener.changeSong(newPath,0);
    }

    @Override
    public void addSongToFavourites(String songName) {
        String path = songs.searchForSong(songName);
        favouriteSongs.addSong(path);
    }

    @Override
    public void removeSongFromPlaylist(List list, String songName) {
        String path = songs.searchForSong(songName);
        if (list instanceof SharedPlaylist)
            sharedPlaylist.removeSong(path);
        else if (list instanceof FavouriteSongs)
            favouriteSongs.removeSong(path);
        else
            searchForPlaylist(list.getName()).removeSong(path);
    }

    @Override
    public void addSongToPlaylist(String playlistName, String songName) {
        String path = songs.searchForSong(songName);
        if (playlistName.equals("Shared playlist"))
            sharedPlaylist.addSong(path);
        else if (playlistName.equals("Favourite Songs"))
            favouriteSongs.addSong(path);
        else
            searchForPlaylist(playlistName).addSong(path);
    }

    public void setLibraryChangeSongListener(LibraryChangeSongListener listener) {
        libraryChangeSongListener = listener;
    }

    @Override
    public void removePlaylistLogically(String name) {
        List temp=null;
        for(int i=0;i<lists.size();i++) {
            temp=lists.get(i);
            if (temp.getName().equals(name)) {
                lists.remove(temp);
                break;
            }
        }
        libraryListenerToPlaylistBar.removePlaylist((Playlist) temp);
    }
}
