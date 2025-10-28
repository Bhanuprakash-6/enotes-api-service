package com.example.enotes_api.Util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.example.enotes_api.Dto.CategoryDto;
import com.example.enotes_api.ExceptionHandler.ValidationException;

@Component
public class Validation {
    public void categoryValidation(CategoryDto categoryDto ){

        Map<String , Object> error = new LinkedHashMap<>(); 


        //validation name field
        if(ObjectUtils.isEmpty(categoryDto)){
            throw new IllegalArgumentException("category objects cannot bee empty");
        }else{
            if(ObjectUtils.isEmpty(categoryDto.getName())){
                throw new IllegalArgumentException("category name field is empty");
            }else{
                if(categoryDto.getName().length() < 10){
                    error.put("name","name min 10 length");
                }
                if(categoryDto.getName().length() > 100){
                    error.put("name","name max 100 length");
                }
            }
        }

        //validation description field
        if(ObjectUtils.isEmpty(categoryDto.getDescription())){
            error.put("description","description cannot be empty");
        }

        //validation description field
        if(ObjectUtils.isEmpty(categoryDto.getIs_active())){
            error.put("is_active","is_active cannot be empty");
        }else{
            if (categoryDto.getIs_active() == null) {
                error.put("is_active", "invalid input: is_active must be true or false");
            }
        }

        if(!error.isEmpty()){
            throw new ValidationException(error); 
        }
    }
}
