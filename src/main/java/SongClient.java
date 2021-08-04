import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.List;

public class SongClient {
    public static void main(String[] args) {
        try {
            TTransport transport;

            transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            SongService.Client client = new SongService.Client(protocol);

            System.out.println();

            performGetSong(client, 2);

            performRemoveSong(client, 3);

            //test update song
            Song temp = client.getSong(1);
            List singers = temp.getSinger();
            singers.add("Me");
            temp.setSinger(singers);
            performUpdateSong(client, temp);
            performGetSong(client, 1);

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



        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void performGetSong(SongService.Client client, int id) {
        try {
            Song song = client.getSong(id);
            System.out.println("song got: " + song.toString());
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



}
