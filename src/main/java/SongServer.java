import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class SongServer {
    public static SongService.Processor processor;
    public static SongServiceHandler handler;

    public static void main(String[] args) {
        try {
            handler = new SongServiceHandler();
            processor = new SongService.Processor(handler);

            startServer(processor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startServer(SongService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            System.out.println("starting the simple server...");
            server.serve();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
