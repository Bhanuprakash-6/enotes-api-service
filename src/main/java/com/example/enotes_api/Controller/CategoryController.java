package com.example.enotes_api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enotes_api.Dto.CategoryDto;
import com.example.enotes_api.Dto.CategoryResponseDto;
import com.example.enotes_api.Entity.Category;
import com.example.enotes_api.Service.CategoryService;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("save-category")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto) {
        Boolean savedC = categoryService.saveCategoryC(categoryDto);
        if (savedC) {
            return new ResponseEntity<>("saved", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAll-category")
    public ResponseEntity<?> getAllCategories() {
        List<CategoryDto> gotC = categoryService.getAllCategory();
        if (org.springframework.util.CollectionUtils.isEmpty(gotC)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(gotC,HttpStatus.OK);
        }
    }

    @GetMapping("getActive-category")
    public ResponseEntity<?> getActiveCategories() {
        List<CategoryResponseDto> gotC = categoryService.getActiveCategory();
        if (org.springframework.util.CollectionUtils.isEmpty(gotC)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(gotC,HttpStatus.OK);
        }
    }

}
