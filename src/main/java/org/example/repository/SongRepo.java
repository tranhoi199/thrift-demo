package org.example.repository;

import org.example.gen.Song;

import java.util.HashMap;
import java.util.Map;

public class SongRepo {
    Map<Integer, Song> hashMapSong = new HashMap<>();

    private SongRepo() {

    }

    private static SongRepo instance;

    public static SongRepo getInstance() {
        if (instance == null) {
            instance = new SongRepo();
        }
        return instance;
    }

    public Map<Integer, Song> getHashMapSong() {
        return hashMapSong;
    }
}
