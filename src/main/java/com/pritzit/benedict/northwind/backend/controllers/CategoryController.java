package com.pritzit.benedict.northwind.backend.controllers;

import com.pritzit.benedict.northwind.backend.dtos.CategoryDto;
import com.pritzit.benedict.northwind.backend.entities.Category;
import com.pritzit.benedict.northwind.backend.exceptions.ResourceNotFoundException;
import com.pritzit.benedict.northwind.backend.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        log.info("Endpoint [GET /category] called");
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Short id) {
        log.info("Retrieving category with id: {}", id);

        try {
            CategoryDto category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(category);
        } catch (ResourceNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.notFound().build();
        }
        finally{
            log.info("Category retrieval completed for id: {}", id);
        }
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto category) {
        log.info("Creating category: {}", category);
        try {
            CategoryDto save = categoryService.createCategory(category);
            return ResponseEntity.created(URI.create("/category/" + save.categoryId())).body(save);
        }
        catch (Exception e){
            log.error("Error saving category: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Short id, @RequestBody CategoryDto updatedCategory){
        log.info("Called [PUT: /category/{}]", id);

        try{
            CategoryDto categoryToUpdate = categoryService.updateCategory(id, updatedCategory);
            return ResponseEntity.ok(categoryToUpdate);
        } catch (ResourceNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


}
