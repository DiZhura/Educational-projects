package com.example.springexample.repositories;

import com.example.springexample.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("select s from CategoryEntity s where s.title = :title")
    Optional<CategoryEntity> findByTitle(String title);
}
