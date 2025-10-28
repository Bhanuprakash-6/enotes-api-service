package com.example.enotes_api.Dto;

import java.util.Date;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {
    private Integer id;

    
    private String name;

   
    private String description;

    
    private Boolean is_active;

    private Boolean is_delete;

    private Integer created_by;

    private Date created_on;

    private Integer updated_by;

    private Date updated_on;
}
