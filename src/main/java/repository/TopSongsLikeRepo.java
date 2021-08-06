package repository;

import gen.Song;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TopSongsLikeRepo {
    List<Song> listTopSongOnLike = new ArrayList<>();

    private TopSongsLikeRepo() {
    }

    private static TopSongsLikeRepo instance;

    public static TopSongsLikeRepo getInstance() {
        if (instance == null) {
            instance = new TopSongsLikeRepo();
        }
        return instance;
    }

    public List<Song> getListTopSongOnLike() {
        return listTopSongOnLike;
    }
}
