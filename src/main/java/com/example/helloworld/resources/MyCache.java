package com.example.helloworld.resources;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MyCache {
    private static MyCache instance;
    private static int counter;
    private final LoadingCache<String, String> cache;
    private final static int CACHE_SIZE = 1000;
    private final static int CACHE_EXPIRE_SECS = 5;


    public MyCache() {
        this(CACHE_SIZE, CACHE_EXPIRE_SECS);
    }

    public MyCache(int cacheSize, int cacheExpireSecs) {
        cache = CacheBuilder.newBuilder().maximumSize(cacheSize)
            .expireAfterWrite(cacheExpireSecs, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String id) throws Exception {
                    return loadFromDb(id);
                }
            });
        counter = 1;
    }

    public static MyCache getInstance() {
        if (instance == null) {
            instance = new MyCache();
        }
        return instance;
    }

    public String load(String id) throws Exception {
        try {
            String value = cache.get(id);
            System.out.println("Loading cache: " + id + " => " + value);
            return value;
        } catch (ExecutionException e) {
            throw new Exception();
        }
    }

    public void clearCache() {
        cache.invalidateAll();
    }

    public String loadFromDb(String id) {
        ++counter;
        return "value" + counter;
    }
}
