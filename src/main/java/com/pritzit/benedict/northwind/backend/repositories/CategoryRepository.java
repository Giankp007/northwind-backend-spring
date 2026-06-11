package com.pritzit.benedict.northwind.backend.repositories;

import com.pritzit.benedict.northwind.backend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Short> {
}
