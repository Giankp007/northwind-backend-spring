package com.pritzit.benedict.northwind.backend.services;

import com.pritzit.benedict.northwind.backend.dtos.CategoryDto;
import com.pritzit.benedict.northwind.backend.entities.Category;
import com.pritzit.benedict.northwind.backend.exceptions.ResourceNotFoundException;
import com.pritzit.benedict.northwind.backend.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategories() {
        log.info("Fetching all categories from DB");

/*  // START Langversion
        List<CategoryDto> dtoList = new ArrayList<>();
        List<Category> all = categoryRepository.findAll();

        for(Category cat : all){
            dtoList.add(toDto(cat));
        }

        return dtoList;
    // END Langversion */

        // Kurzversion (inline)
        // Übergibt alle Entities an die Methode toDto und returned das Ergebnis als Liste
        return categoryRepository.findAll().stream().map(this::toDto).toList();
    }

    public CategoryDto getCategoryById(Short id) {
        log.info("Fetching category with id {}", id);
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            CategoryDto dto = toDto(categoryOptional.get());
            log.info("Returning Dto [{}] from Entity [{}]", dto, categoryOptional.get());
            return dto;
        }

        throw new ResourceNotFoundException("Category with id " + id + " not found");
    }

    public CategoryDto createCategory(CategoryDto category) {
        log.info("Creating category with name {}", category.categoryName());

        Category entity = toEntity(category);

        Category save = categoryRepository.save(entity);
        log.info("Category with id {} created successfully", save.getId());

        log.info("Converting to dto");
        CategoryDto savedDto = toDto(save);
        log.info("Returning dto {}", savedDto);

        return savedDto;
    }

    public CategoryDto updateCategory(Short id, CategoryDto category) {
        log.info("Updating category with id {} to {}", id, category);

        Optional<Category> categoryFromDb = categoryRepository.findById(id);

        if (categoryFromDb.isEmpty())
            throw new ResourceNotFoundException("Category with id " + id + " not found");

        Category categoryToUpdate = categoryFromDb.get();

        // Keine Konvertierung von DTO auf Entity nötig. Entity wird direkt bearbeitet
        categoryToUpdate.setCategoryName(category.categoryName());
        categoryToUpdate.setDescription(category.description());

        return toDto(categoryRepository.save(categoryToUpdate));
    }

    private Category toEntity(CategoryDto dto){
        Category entity = new Category();
        entity.setId(dto.categoryId());
        entity.setCategoryName(dto.categoryName());
        entity.setDescription(dto.description());

        return entity;
    }

    private CategoryDto toDto(Category entity){
        return new CategoryDto(
                entity.getId(),
                entity.getCategoryName(),
                entity.getDescription()
        );
    }
}
