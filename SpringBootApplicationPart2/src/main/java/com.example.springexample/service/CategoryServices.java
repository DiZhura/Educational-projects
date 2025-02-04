package com.example.springexample.service;

import com.example.springexample.controller.MyExceptions;
import com.example.springexample.dto.Category;
import com.example.springexample.entity.CategoryEntity;
import com.example.springexample.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServices implements CRUDCategoryServices<Category> {
    private static final Logger log = LoggerFactory.getLogger(CategoryServices.class);
    @Autowired
    private final CategoryRepository repository;
    @Autowired
    private final NewsServices newsServices;


    @Override
    public Category getById(Long id) {
        CategoryEntity category;
        log.info("Get category by ID: " + id);
        if (repository.existsById(id)) {
            category = repository.findById(id).orElseThrow();
        } else {
            throw new MyExceptions("\"message\": \"" + "Категория с ID " + id + " не найдена\"");
        }
        return categoryMapToDto(category.getTitle());
    }

    @Override
    public Collection<Category> getAll() {
        log.info("Get all");
        return repository.findAll()
                .stream()
                .map(categoryEntity -> categoryMapToDto(categoryEntity.getTitle()))
                .toList();
    }

    @Override
    public CategoryEntity create(Category category) {
        log.info("Create");
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity = categoryMapToEntity(category);
        repository.save(categoryEntity);
        return categoryEntity;
    }

    @Override
    public CategoryEntity update(Long id, Category category) {
        log.info("Update");
        CategoryEntity categoryEntity = categoryMapToEntity(category);
        categoryEntity.setId(id);
        repository.save(categoryEntity);
        return categoryEntity;
    }

    @Override
    public Category delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new MyExceptions("\"message\": \"" + "Новость с ID " + id + " не найдена\"");
        }
        return null;
    }

    public Category categoryMapToDto(String categoryString) {
        Optional<CategoryEntity> categoryEntityOptional = repository.findByTitle(categoryString);
        if (categoryEntityOptional.isPresent()) {
            CategoryEntity categoryEntity = categoryEntityOptional.get();
            Category category = new Category();
            category.setId(categoryEntity.getId());
            category.setTitle(categoryEntity.getTitle());
            category.setNews(
                    categoryEntity.getNews()
                            .stream()
                            .map(newsServices::NewsMapToDto).toList());
            return category;
        }
        return null;
    }

    public CategoryEntity categoryMapToEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setTitle(category.getTitle());

        return categoryEntity;
    }
}
