package org.example.repository;

import org.example.gen.Listen;

import java.util.HashMap;
import java.util.Map;

public class ListenRepo {
    Map<Integer, Listen> hashMapListen = new HashMap<>();

    private ListenRepo() {

    }

    private static ListenRepo instance;

    public static ListenRepo getInstance() {
        if (instance == null) {
            instance = new ListenRepo();
        }
        return instance;
    }

    public Map<Integer, Listen> getHashMapListen() {
        return hashMapListen;
    }
}
