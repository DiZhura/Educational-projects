package com.example.springexample.service;

import com.example.springexample.dto.News;
import com.example.springexample.entity.NewsEntity;

import java.util.Collection;

public interface CRUDNewsServices<T> {
    T getById(Long id);

    Collection<T> getAll();

    NewsEntity create(T item);

    NewsEntity update(Long id, T item);

    News delete(Long id);
}
