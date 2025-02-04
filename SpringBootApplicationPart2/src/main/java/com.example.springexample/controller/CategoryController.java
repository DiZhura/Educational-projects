package com.example.springexample.controller;

import com.example.springexample.dto.Category;
import com.example.springexample.entity.CategoryEntity;
import com.example.springexample.service.CategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryServices categoryService;

    @GetMapping(path = "/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) { //работает
        return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<Collection<Category>> getAll() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) { //работает
        CategoryEntity categoryEntity = categoryService.create(category);
        return new ResponseEntity(categoryEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) { //работает
        return new ResponseEntity(categoryService.update(id, category), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) { //работает
        return new ResponseEntity<>(categoryService.delete(id), HttpStatus.OK);
    }

}
