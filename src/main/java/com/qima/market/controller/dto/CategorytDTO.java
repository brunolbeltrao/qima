package com.qima.market.controller.dto;

import com.qima.market.hexagono.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategorytDTO {
    private Long id;
    private String name;

    private Category category = new Category();

    public static CategorytDTO build(Category category) {
        return  CategorytDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
