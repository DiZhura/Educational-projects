package com.example.springexample.controller;


import com.example.springexample.entity.NewsEntity;
import com.example.springexample.service.NewsServices;
import com.example.springexample.dto.News;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsServices newsService;

    @GetMapping(path = "/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) { //работает
        return new ResponseEntity<>(newsService.getById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Collection<News>> getAll() {
        return new ResponseEntity<>(newsService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<News> create(@RequestBody News newsDto) { //работает
        NewsEntity newsEntity = newsService.create(newsDto);
        return new ResponseEntity(newsEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> update(@PathVariable Long id, @RequestBody News newsDto) { //работает
        return new ResponseEntity(newsService.update(id, newsDto), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) { //работает
        return new ResponseEntity<>(newsService.delete(id), HttpStatus.OK);

    }
}
