package com.example.enotes_api.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enotes_api.Dto.CategoryDto;
import com.example.enotes_api.Dto.CategoryResponseDto;
import com.example.enotes_api.Entity.Category;
import com.example.enotes_api.Service.CategoryService;
import com.example.enotes_api.Util.CommonUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("save-category")
    public ResponseEntity<?> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Boolean savedC = categoryService.saveCategoryC(categoryDto);
        if (savedC) {
            return CommonUtils.createBuilderResponseMeessage("saved success", HttpStatus.CREATED);
            //return new ResponseEntity<>("saved", HttpStatus.CREATED);
        } else {
            return CommonUtils.createErrorBuilderResponse("not saved ", HttpStatus.INTERNAL_SERVER_ERROR);
            //return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAll-category")
    public ResponseEntity<?> getAllCategories() {
        List<CategoryDto> gotC = categoryService.getAllCategory();
        if (org.springframework.util.CollectionUtils.isEmpty(gotC)) {
            return ResponseEntity.noContent().build();
        } else {
            return CommonUtils.createBuilderResponse(gotC, HttpStatus.OK);
            //return new ResponseEntity<>(gotC,HttpStatus.OK);
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

    @GetMapping("getActiveNotDeleted-category")
    public ResponseEntity<?> getActiveNotDeleted() {
        List<CategoryResponseDto> gotC = categoryService.getActiveNotDeleted();
        if (org.springframework.util.CollectionUtils.isEmpty(gotC)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(gotC,HttpStatus.OK);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getByIds(@PathVariable Integer id){
        CategoryDto categoryDto = categoryService.getById(id);
        if (ObjectUtils.isEmpty(categoryDto)) {
            return new ResponseEntity<>("id not found",HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categoryDto,HttpStatus.OK);
        }
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteByIds(@PathVariable Integer id){
        Boolean categoryDto = categoryService.deleteById(id);
        if (ObjectUtils.isEmpty(categoryDto)) {
            return new ResponseEntity<>("id not found",HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Deleted the id ",HttpStatus.OK);
        }
    } 

}
