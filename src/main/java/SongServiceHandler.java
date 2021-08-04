import org.apache.thrift.TException;

import java.util.*;
import java.util.stream.Collectors;

public class SongServiceHandler implements SongService.Iface {
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
        return hashMapLike.get(songId).getNumLike();
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
        return hashMapListen.get(songid).getNumListen();
    }

    @Override
    public int performIncreaseListen(int songId) throws TException {
        // songId is also id field of Listen table, also key of hashMapListen
        if (hashMapListen.containsKey(songId)) {
            return performAddListen(songId, 1);
        } else {
            int listenId = songId;
            hashMapListen.put(songId, new Listen(listenId, songId, 1));
            return 1;
        }
    }

    @Override
    public List<Song> getTopSongBaseOnLike() throws TException {
        // convert hash map value to list
        List<Like> songList = new ArrayList<>(hashMapLike.values());

        //get top 1 Liked song.
        Comparator<Like> comparator = Comparator.comparingInt(Like::getNumLike).reversed();
        List<Like> topSong = songList.stream()
                .sorted(comparator)
                .limit(1)
                .collect(Collectors.toList());

        //get list of top 10 id
        List<Integer> songIdList = topSong.stream()
                .map(Like::getSongid)
                .collect(Collectors.toList());

        //get list of song from hashMapSong
        List<Song> result = new LinkedList<>();
        for (int i : songIdList) {
            result.add(hashMapSong.get(i));
        }
        return result;
    }

    @Override
    public List<Song> getTopSongBaseOnListen() throws TException {
        List<Listen> listenList = new ArrayList<>(hashMapListen.values());

        //get top song
        Comparator<Listen> comparator = Comparator.comparingInt(Listen::getNumListen);
        List<Listen> topListen = listenList.stream()
                .sorted(comparator)
                .limit(1) //just top 1 song
                .collect(Collectors.toList());

        //map to list of songId
        List<Integer> songIdList;
        songIdList = topListen.stream()
                .map(Listen::getSongid)
                .collect(Collectors.toList());

        //get song object in hashMap and append to list
        List<Song> result = new LinkedList<>();
        for (int i : songIdList) {
            result.add(hashMapSong.get(i));
        }

        return result;
    }
}
