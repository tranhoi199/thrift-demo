package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistSongsRepo {
    Map<String, List<Integer>> hashMapArtistListSong = new HashMap<>();

    private ArtistSongsRepo() {

    }

    private static ArtistSongsRepo instance;

    public static ArtistSongsRepo getInstance() {
        if (instance == null) {
            instance = new ArtistSongsRepo();
        }
        return instance;
    }

    public Map<String, List<Integer>> getHashMapArtistListSong() {
        return hashMapArtistListSong;
    }
}
