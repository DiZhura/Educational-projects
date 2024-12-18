package com.example.springexample;


import com.example.springexample.dto.News;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CommentNewsServices implements Services<News> {
    Long newId = 1L;
    private final ConcurrentHashMap<Long, News> storage = new ConcurrentHashMap<>();

    @Override
    public News getById(Long id) {
        System.out.println("Get by ID:" + id);
        return storage.get(id);
    }

    @Override
    public Collection<News> getAll() {
        System.out.println("get all");
        return storage.values();
    }

    @Override
    public void create(News item) {
        item.setId(newId++);
        item.setDate(Instant.now());
        storage.put(item.getId(), item);
        System.out.println("Create news ID " + newId);
    }

    @Override
    public void update(Long id, News item) {
        System.out.println("Update ID: " + id);
        storage.put(id, item);
    }

    @Override
    public News delete(Long id) {
        System.out.println("Delete ID:" + id);
        return storage.remove(id);
    }
}
