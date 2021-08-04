import org.apache.thrift.TException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongServiceHandler implements SongService.Iface{
    Map<Integer, Song> hashMapSong = new HashMap<>();
    Map<Integer, Like> hashMapLike = new HashMap<>();
    Map<Integer, Listen> hashMapListen = new HashMap<>();

    public SongServiceHandler() {
        Song song1 = new Song(1, "Photograph", Arrays.asList("Ed Sheeran"));
        Song song2 = new Song(2, "Shape of you", Arrays.asList("Ed Sheeran"));
        hashMapSong.put(1, song1);
        hashMapSong.put(2, song2);
    }

    @Override
    public Song getSong(int id) throws TException {
        // key id in hashMap also a id in Song table
        if (hashMapSong.containsKey(id)) {
            return hashMapSong.get(id);
        } else {
            return null;
        }

    }

    @Override
    public void removeSong(int id) throws TException {
        // key id in hashMap also a id in Song table
        if (hashMapSong.containsKey(id)) {
            hashMapSong.remove(id);
        }
    }

    @Override
    public void updateSong(Song song) throws TException {
        // key id in hashMap also a id in Song table
        int id = song.getId();
        hashMapSong.put(id, song);
    }

    public int performAddLike(int songId, int valueToAdd) {
        Like likeToUpdate = hashMapLike.get(songId).deepCopy();
        likeToUpdate.setNumLike(likeToUpdate.getNumLike() + valueToAdd);
        hashMapLike.put(songId, likeToUpdate);
        return likeToUpdate.getNumLike();
    }

    @Override
    public int performLike(int songId) throws TException {
        // songId is also id field of Like table, also key of hashMapLike
        if (hashMapLike.containsKey(songId)) {
            return performAddLike(songId, 1);
        } else {
            int likeId = songId;
            hashMapLike.put(songId, new Like(likeId, songId, 1));
            return 1;
        }
    }

    @Override
    public int performUnlike(int songId) throws TException {
        if (hashMapLike.containsKey(songId)) {
            return performAddLike(songId, -1);
        } else {
            return -1;
        }
    }

    public int performAddListen(int songid, int valueToAdd) {
        Listen listenToUpdate = hashMapListen.get(songid);
        listenToUpdate.setNumListen(listenToUpdate.getNumListen() + valueToAdd);
        hashMapListen.put(songid, listenToUpdate);
        return listenToUpdate.getNumListen();
    }

    @Override
    public int performIncreaseListen(int songId) throws TException {
        // songId is also id field of Listen table, also key of hashMapListen
        if (hashMapListen.containsKey(songId)) {
            performAddListen(songId, 1);
        }
        return 0;
    }
}
