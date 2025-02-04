package com.example.springexample.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "text")
    private String text;
    @CreationTimestamp
    @Column(name = "date")
    private Instant date;
    @ManyToOne
    @JoinColumn(name = "category")
    @JsonIgnore
    private CategoryEntity category;

    @JsonProperty("category")
    public String getCategoryTitle() {
        if (category != null) {
            return category.getTitle();
        } else {
            return null;
        }
    }
}
