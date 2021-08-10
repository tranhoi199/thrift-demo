package org.example;

import org.example.gen.SongService;
import org.apache.thrift.server.TServer;

import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;


public class SongServer {
    public static SongService.Processor processor;
    public static SongServiceHandler handler;

    public static void main(String[] args) {
        try {
            handler = new SongServiceHandler();
//            processor = new SongService.Processor(handler);

            processor = new SongService.Processor<>(handler);
            Runnable simple = new Runnable() {
                @Override
                public void run() {
                    startServer(processor);
                }
            };

            new Thread(simple).start();

            //worker reposonse to invoke method to re-calculate top song
            new Thread(() -> {
                while (true) {
                    handler._calculateTopSongBaseOnListen();
                    handler._calculateTopSongBaseOnLike();
                    try {
                        Thread.sleep(1000 * 5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startServer(SongService.Processor processor) {
        try {
            TNonblockingServerSocket socket = new TNonblockingServerSocket(9090);
            TServer server = new TThreadedSelectorServer(new TThreadedSelectorServer.Args(socket)
                    .processor(processor)
                    .selectorThreads(20)
                    .workerThreads(512)
            );

            System.out.println("starting the simple server...");
            server.serve();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

