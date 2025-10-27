package com.example.enotes_api.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.enotes_api.Dto.CategoryDto;
import com.example.enotes_api.Dto.CategoryResponseDto;
 

@Service
public interface CategoryService {

    public Boolean saveCategoryC(CategoryDto categoryDto);

    public List<CategoryDto> getAllCategory();

    public List<CategoryResponseDto> getActiveCategory();
}
