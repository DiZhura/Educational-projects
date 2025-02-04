package com.example.springexample.repositories;

import com.example.springexample.entity.CategoryEntity;
import com.example.springexample.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    @Query("select s from NewsEntity s where s.title = :title")
    Optional<CategoryEntity> findNewsByTitle(String title);
}
