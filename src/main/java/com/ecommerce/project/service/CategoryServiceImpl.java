package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;


import java.util.List;


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
}
