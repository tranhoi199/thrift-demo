import gen.ArtistListSongResponse;
import gen.Like;
import gen.Listen;
import gen.Song;
import gen.SongResponse;
import gen.SongService;
import org.apache.thrift.TException;

import java.util.*;
import java.util.stream.Collectors;

import repository.*;

public class SongServiceHandler implements SongService.Iface {

    Map<Integer, Song> hashMapSong = SongRepo.getInstance().getHashMapSong();
    Map<Integer, Like> hashMapLike = LikeRepo.getInstance().getHashMapLike();
    Map<Integer, Listen> hashMapListen = ListenRepo.getInstance().getHashMapListen();
    Map<String, List<Integer>> hashMapArtistListSong = ArtistSongsRepo.getInstance().getHashMapArtistListSong();

    Map<Integer, String> hashMapArtist = ArtistRepo.getInstance().getHashMapArtist();

    List<Song> listTopSongOnLike = TopSongsLikeRepo.getInstance().getListTopSongOnLike();
    List<Song> listTopSongOnListen = TopSongsListenRepo.getInstance().getListTopSongOnListen();

    Object lockAddSong = new Object();


    public SongServiceHandler() throws TException {
        Song song1 = new Song(1, "Photograph", Arrays.asList("Ed Sheeran"));
        Song song2 = new Song(2, "Shape of you", Arrays.asList("Ed Sheeran"));
        Song song3 = new Song(3, "Drown", Arrays.asList("Martine Garrix", "Clinton Kane"));
        Song song4 = new Song(4, "Perfect", Arrays.asList("Ed Sheeran"));
        addSong(song1);
        addSong(song2);
        addSong(song3);
        addSong(song4);

    }

    @Override
    public int  addSong(Song song) throws TException {
        // add new song to Song table
        synchronized (lockAddSong){
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
                    hashMapArtistListSong.put(artist, new ArrayList<>(Arrays.asList(song.getId())));
                    return 200;
                }
                //get list song and update
                List<Integer> currentListSongId = hashMapArtistListSong.get(artist);
                currentListSongId.add(song.getId());
            }
            return 200;
        }

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

    public void _calculateTopSongBaseOnLike() {
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

        return hashMapLike.get(songId).getNumLike();
    }

    @Override
    public synchronized int performLike(int songId) throws TException {
        // songId is also id field of Like table, also key of hashMapLike
        if (hashMapLike.containsKey(songId)) {
            return _performAddLike(songId, 1);
        }

        hashMapLike.put(songId, new Like(songId, 1));
        return 1;
    }

    @Override
    public synchronized int performUnlike(int songId) throws TException {
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
    public synchronized int performIncreaseListen(int songId) throws TException {
        // songId is also id field of Listen table, also key of hashMapListen
        if (hashMapListen.containsKey(songId)) {
            return _performAddListen(songId, 1);
        }

        hashMapListen.put(songId, new Listen(songId, 1));
        return 1;
    }

    @Override
    public ArtistListSongResponse getSongsByArtist(String artist) throws TException {

        if (hashMapArtist.containsValue(artist)) {
            List<Integer> listSongIdOfArtist = hashMapArtistListSong.get(artist);

            System.out.println(listSongIdOfArtist);
            List<Song> songList = listSongIdOfArtist.stream()
                    .map(item -> hashMapSong.get(item))
                    .collect(Collectors.toList());

            return new ArtistListSongResponse(200, songList);
        }
        return new ArtistListSongResponse(406, null);
    }



    @Override
    public ArtistListSongResponse getTopSongBaseOnLike(int numbefOfTop) throws TException {
        return new ArtistListSongResponse(200, listTopSongOnLike) ;
    }

    public List<Song> _calculateTopSongBaseOnListen() {
        List<Listen> listenList = new ArrayList<>(hashMapListen.values());

        //default calculate top 50
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
        listTopSongOnListen = result;
        return result;
    }

    @Override
    public ArtistListSongResponse getTopSongBaseOnListen(int numbefOfTop) throws TException {
        return new ArtistListSongResponse(200, listTopSongOnListen);
    }
}
