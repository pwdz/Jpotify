package Lists;

import Music.Song;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LibrarySong extends Playlist {
    public LibrarySong(String name, String description, byte[] artwork) {
        super(name, description, artwork);
    }

    public ArrayList<Album> buildAlbums() {
        ArrayList<String> paths=new ArrayList<>(songPath.size());
        for(String s:songPath)
            paths.add(s);
        ArrayList<Album> albums=new ArrayList<>();
        Album album;
        for(int i=0;i<paths.size();i++)
        {
            if(paths.get(i).equals(""))
                continue;
            try {
                Song song=new Song(paths.get(i));
                album = new Album(song.getAlbum(),"");
                for(int j=i;j<paths.size();j++)
                {
                    if(paths.get(j).equals(""))
                        continue;;
//                    System.out.println("khiar_|_");
                    Song temp=new Song(paths.get(j));
                    if(song.getAlbum().equals(temp.getAlbum()))
                    {
                        album.addSong(songPath.get(j));
                        System.out.println(songPath.get(j));
                        paths.set(j,"");
                    }
                    System.out.println("album:"+song.getAlbum());
                    System.out.println("nnAlbum:"+temp.getAlbum());
                }
                albums.add(album);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        for(Album a:albums)
            System.out.println(a.getSongsPaths());
        for (String s:songPath)
            System.out.println("-+-:"+s);
        return albums;
        /*ArrayList<Album> albums = new ArrayList<>();
        Song song;
        if (songPath.size() > 0) {
            try {
                song = new Song(songPath.get(0));
                Album album = new Album(song.getAlbum(),"");
                albums.add(album);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
            return albums;
        boolean found;
        for (String path : songPath) {
            try {
                song = new Song(path);
                found = false;
                for (int i = 0; i < albums.size() && !found; i++) {
                    if (song.getAlbum().equals(albums.get(i).getName())) {
                        albums.get(i).addSong(song.getPath());
                        found = true;
                    }
                }
                if (!found) {
                    Album temp = new Album(song.getAlbum(), "");
                    albums.add(temp);
                    System.out.println("pathhththths SIZE:" + temp.getSongsPaths().size());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        return albums;*/
    }


}

