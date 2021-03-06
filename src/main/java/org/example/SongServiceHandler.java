package org.example;

import org.example.gen.*;
import org.apache.thrift.TException;

import java.util.*;
import java.util.stream.Collectors;

import org.example.repository.*;
import org.example.gen.*;
import org.example.repository.*;

public class SongServiceHandler implements SongService.Iface {

    Map<Integer, Song> hashMapSong = SongRepo.getInstance().getHashMapSong();
    Map<Integer, Like> hashMapLike = LikeRepo.getInstance().getHashMapLike();
    Map<Integer, Listen> hashMapListen = ListenRepo.getInstance().getHashMapListen();
    Map<String, List<Integer>> hashMapArtistListSong = ArtistSongsRepo.getInstance().getHashMapArtistListSong();

    Map<Integer, String> hashMapArtist = ArtistRepo.getInstance().getHashMapArtist();

    List<Song> listTopSongOnLike = new ArrayList<>(TopSongsLikeRepo.getInstance().getListTopSongOnLike());
    List<Song> listTopSongOnListen = new ArrayList<>(TopSongsListenRepo.getInstance().getListTopSongOnListen());

    final List<Object> lockLike = new ArrayList<Object>(10);
    final List<Object> lockListen = new ArrayList<Object>(10);
    Object lockId = new Object();

    int currentId = 0;


    public SongServiceHandler() throws TException {
        Song song1 = new Song(1, "Photograph", Arrays.asList("Ed Sheeran"));
        Song song2 = new Song(2, "Shape of you", Arrays.asList("Ed Sheeran"));
        Song song3 = new Song(3, "Drown", Arrays.asList("Martine Garrix", "Clinton Kane"));
        Song song4 = new Song(4, "Perfect", Arrays.asList("Ed Sheeran"));
        addSong(song1);
        addSong(song2);
        addSong(song3);
        addSong(song4);

        //initialize lock
        for (int i = 0; i < 10; i++) {
            lockLike.add(new Object());
            lockListen.add(new Object());
        }
    }

    @Override
    public ReturnCode addSong(Song song) throws TException {
        int id;
        synchronized (lockId) {
            currentId = currentId + 1;
            id = currentId;
            song.setId(id);
            hashMapSong.put(id, song);
        }
        // add new song to Song table

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
                return ReturnCode.SUCCESS;
            }
            //get list song and update
            List<Integer> currentListSongId = hashMapArtistListSong.get(artist);
            currentListSongId.add(song.getId());
        }
        return ReturnCode.SUCCESS;
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

    private void _removeArtistSong(int songId, Song song) {

    }

    private void _removeLikeTable(int songId) {
        if (hashMapLike.containsKey(songId)) {
            hashMapLike.remove(songId);
        }
    }

    private void _removeListenTable(int songId) {
        if (hashMapListen.containsKey(songId)) {
            hashMapListen.remove(songId);
        }
    }

    private void _removeArtistList(Song song) {
        //get list of singer
        List<String> artistsOfSong = song.getSinger();

        //loop for each singer and delete song id in hashMapArtistListSong
        for (String artist : artistsOfSong) {
            if (hashMapArtistListSong.containsKey(artist)) {
                List<Integer> updatedSong = new ArrayList<>(hashMapArtistListSong.get(artist));

                //filter id song in list
                List<Integer> newList = updatedSong.stream()
                        .filter(item -> !item.equals(song.getId()))
                        .collect(Collectors.toList());
                //re-update song in list
                hashMapArtistListSong.put(artist, new ArrayList<>(newList));
            }
        }
    }

    @Override
    public ReturnCode removeSong(int id) throws TException {
        // key id in hashMap also a id in Song table
        if (hashMapSong.containsKey(id)) {
            Song song = hashMapSong.get(id);
            hashMapSong.remove(id);
            _removeLikeTable(id);
            _removeListenTable(id);
            _removeArtistList(song);
            return ReturnCode.SUCCESS;
        }
        return ReturnCode.INVALID;
    }

    @Override
    public ReturnCode updateSong(Song song) throws TException {

        // key id in hashMap also a id in Song table
        int id = song.getId();
        // if invalid id
        if (!hashMapSong.containsKey(id)) {
            return ReturnCode.INVALID;
        }
        hashMapSong.put(id, song);
        return ReturnCode.SUCCESS;
    }

    public void _calculateTopSongBaseOnLike() {
        // convert hash map value to list
        List<Like> songList = new ArrayList<>(hashMapLike.values());

        int limitValue = songList.size() >= 50 ? 50 : songList.size();
        //get top 1 Liked song.
        Comparator<Like> comparator = Comparator.comparingInt(Like::getNumLike).reversed();
        List<Like> topSong = songList.stream()
                .sorted(comparator)
                .limit(limitValue)
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
    public ReturnCode performLike(int songId) throws TException {
        //return -1 if invalid songid
        if (!hashMapSong.containsKey(songId)) {
            return ReturnCode.INVALID;
        }
        // songId is also id field of Like table, also key of hashMapLike
        if (hashMapLike.containsKey(songId)) {
            synchronized (lockLike.get(songId % 10)) {
                int result = _performAddLike(songId, 1);
                System.out.println("result in like:" + result);
                return ReturnCode.SUCCESS;
            }
        }

        synchronized(lockLike.get(songId % 10)) {
            hashMapLike.put(songId, new Like(songId, 1));
        }
        return ReturnCode.SUCCESS;
    }

    @Override
    public ReturnCode performUnlike(int songId) throws TException {
        if (hashMapLike.containsKey(songId)) {
            synchronized (lockLike.get(songId % 10)) {
                 _performAddLike(songId, -1);
                return ReturnCode.SUCCESS;
            }
        }
        return ReturnCode.INVALID;
    }

    public int _performAddListen(int songid, int valueToAdd) {
        Listen listenToUpdate = hashMapListen.get(songid);
        listenToUpdate.setNumListen(listenToUpdate.getNumListen() + valueToAdd);
        hashMapListen.put(songid, listenToUpdate);
        return hashMapListen.get(songid).getNumListen();
    }

    @Override
    public ReturnCode performIncreaseListen(int songId) throws TException {
        if (!hashMapSong.containsKey(songId)) {
            return ReturnCode.INVALID;
        }
        // songId is also id field of Listen table, also key of hashMapListen
        if (hashMapListen.containsKey(songId)) {
            synchronized (lockListen.get(songId % 10)) {
                int result = _performAddListen(songId, 1);
                System.out.println("result in listen: " + result);
                return ReturnCode.SUCCESS;
            }
        }
        synchronized (lockListen) {
            hashMapListen.put(songId, new Listen(songId, 1));
        }

        return ReturnCode.SUCCESS;
    }

    @Override
    public ArtistListSongResponse getSongsByArtist(String artist) throws TException {

        if (hashMapArtist.containsValue(artist)) {
            List<Integer> listSongIdOfArtist = hashMapArtistListSong.get(artist);

            List<Song> songList = listSongIdOfArtist.stream()
                    .map(item -> hashMapSong.get(item))
                    .collect(Collectors.toList());

            return new ArtistListSongResponse(200, songList);
        }
        return new ArtistListSongResponse(406, null);
    }



    @Override
    public ArtistListSongResponse getTopSongBaseOnLike(int numbefOfTop) throws TException {
        if (numbefOfTop >= listTopSongOnLike.size() || numbefOfTop < 1) {
            return new ArtistListSongResponse(200, listTopSongOnLike);
        }

        List<Song> ret = listTopSongOnLike.subList(0, numbefOfTop);
        return new ArtistListSongResponse(200, ret);
    }

    public List<Song> _calculateTopSongBaseOnListen() {
        List<Listen> listenList = new ArrayList<>(hashMapListen.values());
        int limitValue = listenList.size() >= 50 ? 50 : listenList.size();
        //default calculate top 50
        Comparator<Listen> comparator = Comparator.comparingInt(Listen::getNumListen).reversed();
        List<Listen> topListen = listenList.stream()
                .sorted(comparator)
                .limit(limitValue) //just top 1 song
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
        if (numbefOfTop >= listTopSongOnListen.size() || numbefOfTop < 1) {
            return new ArtistListSongResponse(200, listTopSongOnListen);
        }

        List<Song> ret = listTopSongOnListen.subList(0, numbefOfTop);
        return new ArtistListSongResponse(200, ret);
    }
}
