package org.example.repository;

import org.example.gen.Like;

import java.util.HashMap;
import java.util.Map;

public class LikeRepo {
    Map<Integer, Like> hashMapLike = new HashMap<>();

    private LikeRepo() {

    }

    private static LikeRepo instance;

    public static LikeRepo getInstance() {
        if (instance == null) {
            instance = new LikeRepo();
        }
        return instance;
    }

    public Map<Integer, Like> getHashMapLike() {
        return hashMapLike;
    }
}
