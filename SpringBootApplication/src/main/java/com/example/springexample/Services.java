package com.example.springexample;

import com.example.springexample.dto.News;

import java.util.Collection;

public interface Services<T> {
    T getById(Long id);

    Collection<T> getAll();

    void create(T item);

    void update(Long id, T item);

    News delete(Long id);
}
