import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataRepository {
    Map<Integer, Song> hashMapSong = new HashMap<>();
    Map<Integer, Like> hashMapLike = new HashMap<>();
    Map<Integer, Listen> hashMapListen = new HashMap<>();

    public DataRepository(Map<Integer, Song> hashMapSong, Map<Integer, Like> hashMapLike, Map<Integer, Listen> hashMapListen) {
        this.hashMapSong = hashMapSong;
        this.hashMapLike = hashMapLike;
        this.hashMapListen = hashMapListen;
    }

    public void init() {
        Song song1 = new Song(1, "Photograph", Arrays.asList("Ed Sheeran"));
        Song song2 = new Song(2, "Shape of you", Arrays.asList("Ed Sheeran"));

        hashMapSong.put(1, song1);
        hashMapSong.put(2, song2);
    }

    public Map<Integer, Song> getHashMapSong() {
        return hashMapSong;
    }

    public void setHashMapSong(Map<Integer, Song> hashMapSong) {
        this.hashMapSong = hashMapSong;
    }

    public Map<Integer, Like> getHashMapLike() {
        return hashMapLike;
    }

    public void setHashMapLike(Map<Integer, Like> hashMapLike) {
        this.hashMapLike = hashMapLike;
    }

    public Map<Integer, Listen> getHashMapListen() {
        return hashMapListen;
    }

    public void setHashMapListen(Map<Integer, Listen> hashMapListen) {
        this.hashMapListen = hashMapListen;
    }
}
