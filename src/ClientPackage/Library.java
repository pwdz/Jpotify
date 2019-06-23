package ClientPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import Listeners.AddPlaylistListener;
import Listeners.ChooseSongListener;
import Listeners.LibraryListenerToPlaylistBar;
import Lists.*;
import Music.Song;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Library implements AddPlaylistListener, ChooseSongListener {
    private ArrayList<List> lists;
    private SharedPlaylist sharedPlaylist;
    private FavouriteSongs favouriteSongs;
    private ArrayList<String> songs;
    private LibraryListenerToPlaylistBar libraryListenerToPlaylistBar;
    //    private ArrayList<Song> songs;
    public Library() {
        lists = new ArrayList<>();
        songs = new ArrayList<>();
    }

    public void addSong(String path) {
        songs.add(path);
    }

    public void addList(List list) {
        lists.add(list);
    }

    @Override
    public void makePlaylist(String name, String description, String artworkPath) {

        try {
            File artworkFile = new File(artworkPath);
            byte[] artworkByteCode = Files.readAllBytes(artworkFile.toPath());
            Playlist playlist = new Playlist(name, description, artworkByteCode, true);
            if (!lists.contains(playlist)) {
                addList(playlist);
                libraryListenerToPlaylistBar.addNewPlaylist(playlist);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addSongToLibrary(String songPath) {
        if(!songs.contains(songPath)) {
            addSong(songPath);
        }
//        System.out.println(songPath);
    }

    public void setLibraryListenerToPlaylistBar(LibraryListenerToPlaylistBar listener)
    {
        libraryListenerToPlaylistBar = listener;
    }
}
