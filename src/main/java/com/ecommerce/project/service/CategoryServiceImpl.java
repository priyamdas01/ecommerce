package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService{
    private List<Category> categories;
    private Long nextId = 1L;

    public CategoryServiceImpl(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categories;
    }

    @Override
    public void createCategory(Category category) {

        category.setCategoryId(nextId++);
        this.categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not Found"));

        categories.remove(category);
        return "Category " + category.getCategoryId() + " deleted successfully";
    }

    @Override
    public Category updateCategory(Category cat, Long categoryId) {
        Optional<Category> optionalCategory = categories.stream()
                        .filter(c -> c.getCategoryId().equals(categoryId))
                        .findFirst();
        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(cat.getCategoryName());
            return existingCategory;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not founf");
        }

    }


}
