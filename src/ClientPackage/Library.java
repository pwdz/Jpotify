package ClientPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import GUI.Essentials;
import Listeners.*;
import Lists.*;
import Music.Song;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Library implements AddPlaylistListener, ChooseSongListener, ListGUIListener {
    private ArrayList<List> lists;
    private SharedPlaylist sharedPlaylist;
    private FavouriteSongs favouriteSongs;
    private LibrarySong songs;
    private LibraryListenerToPlaylistBar libraryListenerToPlaylistBar;
    private List currentList;
    private LibraryChangeListListener libraryChangeListListener;

    //    private ArrayList<Song> songs;
    public Library() {
        lists = new ArrayList<>();
        songs = new LibrarySong("Songs", "All songs", imageConverterToByteCode(".\\pics\\Music.png"));
        favouriteSongs = new FavouriteSongs("Favourite Songs", "", imageConverterToByteCode(".\\pics\\Favourite2.png"));
        sharedPlaylist = new SharedPlaylist("Shared playlist", "", imageConverterToByteCode(".\\pics\\Shared.png"));
        currentList = songs;
//        libraryChangeListListener.updateCenter(currentList);
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

    @Override
    public void listClicked(ListType listType, String listName) {
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
                //code here
                break;
            case Playlist:
                System.out.println("~~~~~~~~~");
                if (searchForPlaylist(listName) != null) {
                    currentList = searchForPlaylist(listName);
                    System.out.println("][][][][][][");
                }
                break;
        }
        libraryChangeListListener.updateCenter(currentList);
    }

    private Playlist searchForPlaylist(String playlistName) {
        System.out.println("PlaylistName:"+playlistName);
        for (List list : lists) {
            System.out.println("N:"+list.getName());
            if (list instanceof Playlist) {
                if (((Playlist) list).getName().equals(playlistName))
                    if (((Playlist) list).isRemovable())//if removable is true then it's not Fav and Shared playlist.
                    {
                        System.out.println("000000"+list.getName());
                        return (Playlist) list;
                    }
            }
        }
        return null;
    }

    public void setLibraryChangeListListener(LibraryChangeListListener listener)
    {
        libraryChangeListListener = listener;
    }
}
