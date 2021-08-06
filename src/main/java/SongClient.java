import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.layered.TFramedTransport;

import java.util.List;

public class SongClient {
    public static void main(String[] args) {
        try {
            TTransport transport;

            transport = new TFramedTransport(new TSocket("localhost", 9090));
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            SongService.Client client = new SongService.Client(protocol);

            System.out.println();

            performGetSong(client, 2);

//            performRemoveSong(client, 3);

            //test update song
//            SongResponse temp = client.getSong(1);
//            List singers = temp.getSong().getSinger();
//            singers.add("Me");
//            temp.getSong().setSinger(singers);
//            performUpdateSong(client, temp.getSong());
//            performGetSong(client, 1);

            //test increase like
            performLike(client, 1);
            performLike(client, 1);
            performLike(client, 1);
            performLike(client, 1);

            //test decrese unlike
            performUnlike(client, 1);
            performUnlike(client, 1);

            //test increase listen
            performIncreaseListen(client, 1);
            performIncreaseListen(client, 1);
            performIncreaseListen(client, 1);
            performIncreaseListen(client, 1);

            //test get top like
            performLike(client, 2);
            performLike(client, 2);
            performLike(client, 2);
            performLike(client, 2);
            performLike(client, 2);
            System.out.println("get top song liked");
            getTopSongBaseOnLike(client, 1);

            //test get top song listen
            System.out.println("get top song listen");
            getTopSongBaseOnListen(client, 1);

            //get artist list song
            System.out.println("List song of artist:");
            performGetSongByArtist(client,"Ed Sheeran");

//            System.out.println("List song of artist:");
//            performGetSongByArtist(client,"Me");

            performGetSong(client, 4);
            performGetSong(client, 3);

//            new Thread(() -> {
//                for (int i = 0; i < 10; i++) {
//                    performLike(client, 2);
//                }
//            }).start();

//            for(int i = 0; i < 10; i++){
//                TTransport transport1 = new TFramedTransport(new TSocket("localhost", 9090));
//                transport1.open();
//
//                TProtocol protocol1 = new  TBinaryProtocol(transport1);
//                SongService.Client client1 = new SongService.Client(protocol1);
//                new Thread(() -> {
//                    for(int j =0 ;j < 10;j++){
//                        performLike(client1, 2);
//                    }
//                }).start();
//            }
//
//            for(int k = 0; k < 10; k++){
//                TTransport transport1 = new TFramedTransport(new TSocket("localhost", 9090));
//                transport1.open();
//
//                TProtocol protocol1 = new  TBinaryProtocol(transport1);
//                SongService.Client client1 = new SongService.Client(protocol1);
//                new Thread(() -> {
//                    for(int j =0 ;j < 10;j++){
//                        performIncreaseListen(client1, 2);
//                    }
//                }).start();
//            }


        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void performGetSong(SongService.Client client, int id) {
        try {
            SongResponse song = client.getSong(id);
            System.out.println("song got: " + song.toString());
        } catch (Exception e) {
            System.out.println("unkown result");
            e.printStackTrace();
        }

    }

    public static void performGetSongByArtist(SongService.Client client, String artistName) {
        try {
            ArtistListSongResponse listSongResponse = client.getSongsByArtist(artistName);
            System.out.println("List song got: " + listSongResponse.toString());
        } catch (Exception e) {
            System.out.println("unkown result");
            e.printStackTrace();
        }

    }

    public static void performRemoveSong(SongService.Client client, int id) {
        try {
            client.removeSong(id);
            System.out.println("deleted song");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performUpdateSong(SongService.Client client, Song songToUpdate) {
        try {
            client.updateSong(songToUpdate);
            System.out.println("updated song");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performLike(SongService.Client client, int songId) {
        try {
            int like = client.performLike(songId);
            System.out.println("like :" + like);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performUnlike(SongService.Client client, int songId) {
        try {
            int like = client.performUnlike(songId);
            System.out.println("like :" + like);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void performIncreaseListen(SongService.Client client, int songId) {
        try {
            int listen = client.performIncreaseListen(songId);
            System.out.println("listen :" + listen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getTopSongBaseOnLike(SongService.Client client, int numberOfTop) {
        try {
            ArtistListSongResponse list = client.getTopSongBaseOnLike(numberOfTop);
            System.out.println("List song:\n" + list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getTopSongBaseOnListen(SongService.Client client, int numberOfTop) {
        try {
            ArtistListSongResponse list = client.getTopSongBaseOnListen(numberOfTop);
            System.out.println("List song:\n" + list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
