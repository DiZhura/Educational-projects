package com.example.springexample;


import com.example.springexample.dto.News;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/news")
public class CommentController {
    private final CommentNewsServices commentNewsService;

    public CommentController(CommentNewsServices commentNewsServices) {
        this.commentNewsService = commentNewsServices;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) { //работает
        if (commentNewsService.getById(id) == null) {
            System.out.println("Новость с ID " + id + " не найдена");
            return new ResponseEntity<>("Новость с ID " + id + " не найдена", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentNewsService.getById(id), HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<Collection<News>> getAll() {
        return new ResponseEntity<>(commentNewsService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<News> create(@RequestBody News commentNewsDto) { //работает
        commentNewsService.create(commentNewsDto);
        return new ResponseEntity<News>(commentNewsDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> update(@PathVariable Long id, @RequestBody News commentNewsDto) { //работает
        if (commentNewsService.getById(id) == null) {
            System.out.println("Новость с ID " + id + " не найдена");
        }
        commentNewsService.update(id, commentNewsDto);
        return new ResponseEntity<News>(commentNewsDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) { //работает
        if (commentNewsService.getById(id) == null) {
            System.out.println("Новость с ID" + id + " не найдена");
            return new ResponseEntity<>("Новость с ID" + id + " не найдена", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentNewsService.delete(id), HttpStatus.OK);
    }
}
