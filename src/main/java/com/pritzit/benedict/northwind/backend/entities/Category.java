package com.pritzit.benedict.northwind.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "categories")
@Entity
public class Category {
    @Column(name = "picture")
    private byte[] picture;
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;
    @Column(name = "category_name", nullable = false, length = 15)
    private String categoryName;
    @Id
    @Column(name = "category_id", nullable = false)
    private Short id;
}
