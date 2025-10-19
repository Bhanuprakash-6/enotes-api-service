package com.example.enotes_api.ServiceImp;

 
 
import java.util.Date;
import java.util.List;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.enotes_api.Entity.Category;
import com.example.enotes_api.Repository.CategoryRepo;
import com.example.enotes_api.Service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Boolean saveCategory(Category category) {
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
    public List<Category> getAllCategory() {
        List<Category> gotC = categoryRepo.findAll();
        return gotC;
    }

    

    
    
}
