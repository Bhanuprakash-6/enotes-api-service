package com.example.enotes_api.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryResponseDto {
    private Integer id;

    private String name;

    private String description;
}
