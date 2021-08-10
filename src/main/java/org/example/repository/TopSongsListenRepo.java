package org.example.repository;

import org.example.gen.Song;

import java.util.ArrayList;
import java.util.List;

public class TopSongsListenRepo {
    List<Song> listTopSongOnListen = new ArrayList<>();

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
