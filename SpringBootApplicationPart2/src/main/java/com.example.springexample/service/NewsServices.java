package com.example.springexample.service;

import com.example.springexample.controller.MyExceptions;
import com.example.springexample.dto.News;
import com.example.springexample.entity.CategoryEntity;
import com.example.springexample.entity.NewsEntity;
import com.example.springexample.repositories.CategoryRepository;
import com.example.springexample.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NewsServices implements CRUDNewsServices<News> {
    private static final Logger log = LoggerFactory.getLogger(NewsServices.class);
    @Autowired
    private final NewsRepository repository;
    @Autowired
    private final CategoryRepository categoryRepository;


    @Override
    public News getById(Long id) {
        log.info("Get news by ID: " + id);
        NewsEntity news;
        if (repository.existsById(id)) {
             news = repository.findById(id).orElseThrow();
        } else {
            throw new MyExceptions("\"message\": \"" + "Новость с ID " + id + " не найдена\"");
        }
        return NewsMapToDto(news);
    }

    @Override
    public Collection<News> getAll() {
        log.info("Get all");
        return repository.findAll()
                .stream()
                .map(this::NewsMapToDto)
                .toList();
    }

    @Override
    public NewsEntity create(News news) {
        log.info("Create");
        NewsEntity newsEntity = NewsMapToEntity(news);
        repository.save(newsEntity);
        return newsEntity;
    }

    @Override
    public NewsEntity update(Long id, News news) {
        log.info("Update");
        NewsEntity newsEntity = NewsMapToEntity(news);
        newsEntity.setId(id);
        newsEntity.setDate(news.getDate());
        repository.save(newsEntity);
        return newsEntity;
    }

    @Override
    public News delete(Long id) {
        log.info("Delete by ID: " + id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new MyExceptions("\"message\": \"" + "Новость с ID " + id + " не найдена\"");
        }
        return null;
    }

    public News NewsMapToDto(NewsEntity newsEntity) {
        News news = new News();
        news.setId(newsEntity.getId());
        news.setTitle(newsEntity.getTitle());
        news.setDate(newsEntity.getDate());
        news.setText(newsEntity.getText());
        news.setCategory(newsEntity.getCategory().getTitle());
        return news;
    }

    public NewsEntity NewsMapToEntity(News news) {
       NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle(news.getTitle());
        newsEntity.setText(news.getText());
        Optional<CategoryEntity> categoryOptional = categoryRepository.findByTitle(news.getCategory());
        if (categoryOptional.isPresent()) {
            newsEntity.setCategory(categoryOptional.get());
        } else {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setTitle(news.getCategory());
            newsEntity.setCategory(categoryEntity);
            categoryRepository.save(categoryEntity);
        }
        return newsEntity;
    }
}
