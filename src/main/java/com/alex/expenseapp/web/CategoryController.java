package com.alex.expenseapp.web;


import com.alex.expenseapp.model.entity.Category;
import com.alex.expenseapp.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    public Collection<Category> categories() {

        return this.categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {

        Optional<Category> category = this.categoryRepository.findById(id);
        return category.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) throws URISyntaxException {
        Category result = this.categoryRepository.save(category);

        return ResponseEntity.created(new URI("/api/categories" + result.getId())).body(result);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) {
        Category result = this.categoryRepository.save(category);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        this.categoryRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
