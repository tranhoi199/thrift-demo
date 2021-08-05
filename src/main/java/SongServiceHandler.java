import org.apache.thrift.TException;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SongServiceHandler implements SongService.Iface {
    Map<Integer, Song> hashMapSong = new HashMap<>();
    Map<Integer, Like> hashMapLike = new HashMap<>();
    Map<Integer, Listen> hashMapListen = new HashMap<>();
    Map<String, List<Integer>> hashMapArtistListSong = new HashMap<>();

    Map<Integer, String> hashMapArtist = new HashMap<>();

    List<Song> listTopSongOnLike = new LinkedList<>();

    public SongServiceHandler() throws TException {
        Song song1 = new Song(1, "Photograph", Arrays.asList("Ed Sheeran"));
        Song song2 = new Song(2, "Shape of you", Arrays.asList("Ed Sheeran"));
        Song song3 = new Song(3, "Drown", Arrays.asList("Martine Garrix", "Clinton Kane"));
        addSong(song1);
        addSong(song2);
        addSong(song3);

    }

    @Override
    public int addSong(Song song) throws TException {
        // add new song to Song table
        hashMapSong.put(hashMapSong.size() + 1, song);

        // add artist to Artist table
        List<String> listArtist = song.getSinger();
        for (String artist : listArtist) {
            if (!hashMapArtist.containsValue(artist)) {
                hashMapArtist.put(hashMapArtist.size() + 1, artist);
            }
        }

        // add song to artistSong table (hashMapArtistSong)
        // hashMapArtistSong has key is artist name and value is list of songId
        for (String artist: listArtist) {
            if (!hashMapArtistListSong.containsKey(artist)) {
                // add new artist in ArtistSong table
                hashMapArtistListSong.put(artist, Arrays.asList(song.getId()));
            }
            else {
                //get list song and update
                List<Integer> currentListSongId = hashMapArtistListSong.get(artist);
                List<Integer> updatedListSong = new ArrayList<>(currentListSongId);
                updatedListSong.add(song.getId());
                //update list song in map
                hashMapArtistListSong.put(artist, updatedListSong);
            }
        }
        return 200;
    }

    @Override
    public SongResponse getSong(int id) throws TException {
        // key id in hashMap also a id in Song table
        if (hashMapSong.containsKey(id)) {
            Song song = hashMapSong.get(id);
            return new SongResponse(200, song);
        }
        return new SongResponse(406, null);
    }

    @Override
    public int removeSong(int id) throws TException {
        // key id in hashMap also a id in Song table
        if (hashMapSong.containsKey(id)) {
            hashMapSong.remove(id);
            return 200;
        }
        return 406;
    }

    @Override
    public int updateSong(Song song) throws TException {

        // key id in hashMap also a id in Song table
        int id = song.getId();
        // if invalid id
        if (!hashMapSong.containsKey(id)) {
            return 406;
        }
        hashMapSong.put(id, song);
        return 200;
    }

    private void _calculateTopSongBaseOnLike() {
        // convert hash map value to list
        List<Like> songList = new ArrayList<>(hashMapLike.values());

        //get top 1 Liked song.
        Comparator<Like> comparator = Comparator.comparingInt(Like::getNumLike).reversed();
        List<Like> topSong = songList.stream()
                .sorted(comparator)
                .limit(1)
                .collect(Collectors.toList());

        //get list of top 1 id
        List<Integer> songIdList = topSong.stream()
                .map(Like::getSongid)
                .collect(Collectors.toList());

        //get list of song from hashMapSong
        List<Song> result = new LinkedList<>();
        for (int i : songIdList) {
            result.add(hashMapSong.get(i));
        }
        listTopSongOnLike = result;
    }

    private int _performAddLike(int songId, int valueToAdd) {

        Like likeToUpdate = hashMapLike.get(songId).deepCopy();
        likeToUpdate.setNumLike(likeToUpdate.getNumLike() + valueToAdd);
        hashMapLike.put(songId, likeToUpdate);
        //cal to recalculate top song base on like
        _calculateTopSongBaseOnLike();
        return hashMapLike.get(songId).getNumLike();
    }

    @Override
    public int performLike(int songId) throws TException {
        // songId is also id field of Like table, also key of hashMapLike
        if (hashMapLike.containsKey(songId)) {
            return _performAddLike(songId, 1);
        }

        int likeId = songId;
        hashMapLike.put(songId, new Like(likeId, songId, 1));
        return 1;
    }

    @Override
    public int performUnlike(int songId) throws TException {
        if (hashMapLike.containsKey(songId)) {
            return _performAddLike(songId, -1);
        }
        return -1;
    }

    public int _performAddListen(int songid, int valueToAdd) {
        Listen listenToUpdate = hashMapListen.get(songid);
        listenToUpdate.setNumListen(listenToUpdate.getNumListen() + valueToAdd);
        hashMapListen.put(songid, listenToUpdate);
        return hashMapListen.get(songid).getNumListen();
    }

    @Override
    public int performIncreaseListen(int songId) throws TException {
        // songId is also id field of Listen table, also key of hashMapListen
        if (hashMapListen.containsKey(songId)) {
            return _performAddListen(songId, 1);
        }

        int listenId = songId;
        hashMapListen.put(songId, new Listen(listenId, songId, 1));
        return 1;
    }

    @Override
    public ArtistListSongResponse getSongsByArtist(String artist) throws TException {

        if (hashMapArtist.containsValue(artist)) {
            List<Integer> listSongIdOfArtist = hashMapArtistListSong.get(artist);
            List<Song> songList = (List<Song>) listSongIdOfArtist.stream()
                    .map(item -> hashMapSong.get(item))
                    .collect(Collectors.toList());

            return new ArtistListSongResponse(200, songList);
        }
        return new ArtistListSongResponse(406, null);
    }



    @Override
    public List<Song> getTopSongBaseOnLike() throws TException {
        return listTopSongOnLike;
    }

    private List<Song> _calculateTopSongBaseOnListen() {
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


    @Override
    public List<Song> getTopSongBaseOnListen() throws TException {
        return _calculateTopSongBaseOnListen();
    }
}
