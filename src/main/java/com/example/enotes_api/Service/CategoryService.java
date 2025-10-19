package com.example.enotes_api.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.enotes_api.Entity.Category;

@Service
public interface CategoryService {

    public Boolean saveCategory(Category category);

    public List<Category> getAllCategory();

    
    
}
