package com.example.springexample.service;

import com.example.springexample.dto.Category;
import com.example.springexample.entity.CategoryEntity;

import java.util.Collection;

public interface CRUDCategoryServices<T> {
    T getById(Long id);

    Collection<T> getAll();

    CategoryEntity create(T item);

    CategoryEntity update(Long id, T item);

    Category delete(Long id);
}
