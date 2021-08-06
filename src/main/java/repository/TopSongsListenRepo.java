package repository;

import gen.Song;

import java.util.LinkedList;
import java.util.List;

public class TopSongsListenRepo {
    List<Song> listTopSongOnListen = new LinkedList<>();

    private TopSongsListenRepo() {
    }

    private static TopSongsListenRepo instance;

    public static TopSongsListenRepo getInstance() {
        if (instance == null) {
            instance = new TopSongsListenRepo();
        }
        return instance;
    }

    public List<Song> getListTopSongOnListen() {
        return listTopSongOnListen;
    }
}
