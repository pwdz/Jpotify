package ClientPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import GUI.Essentials;
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
    private LibrarySong songs;
    private LibraryListenerToPlaylistBar libraryListenerToPlaylistBar;

    //    private ArrayList<Song> songs;
    public Library() {
        lists = new ArrayList<>();
        songs = new LibrarySong("Songs", "All of your songs", imageConverterToByteCode(".\\pics\\Music.png"));
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
}
