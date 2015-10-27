package com.example.helloworld.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import com.example.helloworld.resources.MyCache;

public class Saying {
    private long id;

    private String content;

    public Saying() {
        // Jackson deserialization
    }

    public Saying(long id, String key) throws Exception {
        this.id = id;
        MyCache cache = MyCache.getInstance();
        System.out.println("cache instance: " + cache.toString());
        this.content = cache.load(key);
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}