package com.example.enotes_api.ServiceImp;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.example.enotes_api.Controller.CategoryController;
import com.example.enotes_api.Dto.CategoryDto;
import com.example.enotes_api.Dto.CategoryResponseDto;
import com.example.enotes_api.EnotesApiApplication;
import com.example.enotes_api.Entity.Category;
import com.example.enotes_api.Repository.CategoryRepo;
import com.example.enotes_api.Service.CategoryService;
import com.example.enotes_api.Util.Validation;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private Validation validation; 

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

        //Validation
        validation.categoryValidation(categoryDto);


        Category category = mapper.map(categoryDto,Category.class);

        if(ObjectUtils.isEmpty(category.getId())){
            category.setIs_delete(false);
            category.setCreated_by(1);
            category.setCreated_on(new Date());
        }else{
            updateCategory(category);
        }

        
        Category saveC = categoryRepo.save(category);
        if (ObjectUtils.isEmpty(saveC)) {
            return false;           
        }
        else{
            return true;
        }
    }

    private void updateCategory(Category category) {
        Optional<Category> gotCat = categoryRepo.findById(category.getId());
        
        if(gotCat.isPresent()){
            Category exisCategory = gotCat.get();
            category.setCreated_by(exisCategory.getCreated_by());
            category.setCreated_on(exisCategory.getCreated_on());
            category.setIs_delete(exisCategory.getIs_delete());
            category.setUpdated_by(1);
            category.setUpdated_on(new Date());
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

    @Override
    public CategoryDto getById(Integer id) {
        Optional<Category> got = categoryRepo.findByIdAndIsDeletedFalse(id);
        if(got.isPresent()){
            Category category = got.get();
            return mapper.map(category, CategoryDto.class);
        }

        return null;
    }

    @Override
    public Boolean deleteById(Integer id) {
        Optional<Category> got = categoryRepo.findById(id);
        if(got.isPresent()){
            Category category = got.get();
            category.setIs_delete(true);
            categoryRepo.save(category);
            return true;
        }

        return false;
        
        
    }

    @Override
    public List<CategoryResponseDto> getActiveNotDeleted() {
         List<Category> gotC = categoryRepo.findByActiveTrueAndNotDeleted();
         return gotC.stream().map(cat -> mapper.map(cat, CategoryResponseDto.class)).toList();
    }

    

    
    
}
