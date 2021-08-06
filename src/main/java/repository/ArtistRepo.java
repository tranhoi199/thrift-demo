package repository;

import java.util.HashMap;
import java.util.Map;

public class ArtistRepo {
    Map<Integer, String> hashMapArtist = new HashMap<>();

    private ArtistRepo() {
    }

    private static ArtistRepo instance;

    public static ArtistRepo getInstance() {
        if (instance == null) {
            instance = new ArtistRepo();
        }
        return instance;
    }

    public Map<Integer, String> getHashMapArtist() {
        return hashMapArtist;
    }
}
