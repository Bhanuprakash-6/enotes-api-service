package com.example.enotes_api.ServiceImp;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.enotes_api.Dto.CategoryDto;
import com.example.enotes_api.Dto.CategoryResponseDto;
import com.example.enotes_api.Entity.Category;
import com.example.enotes_api.Repository.CategoryRepo;
import com.example.enotes_api.Service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper mapper;

    //@Autowired
    private CategoryResponseDto categoryResponseDto;

    @Override
    public Boolean saveCategoryC(CategoryDto categoryDto) {
        //Category category = new Category();
        // category.setName(categoryDto.getName());
        // category.setDescription(categoryDto.getDescription());
        // category.setIs_active(categoryDto.getIs_active());

        Category category = mapper.map(categoryDto,Category.class);

        category.setIs_delete(false);
        category.setCreated_by(1);
        category.setCreated_on(new Date());
        Category saveC = categoryRepo.save(category);
        if (ObjectUtils.isEmpty(saveC)) {
            return false;           
        }
        else{
            return true;
        }
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> gotC = categoryRepo.findAll();

        List<CategoryDto> categoryDtoList = gotC.stream().map(cat-> mapper.map(cat, CategoryDto.class)).toList();
        return categoryDtoList;
    }
    
    @Override
    public List<CategoryResponseDto> getActiveCategory() {
        List<Category> gotC = categoryRepo.findByActiveTrue();

        return gotC.stream().map(cat -> mapper.map(cat, CategoryResponseDto.class)).toList();
        
    }
    
}
