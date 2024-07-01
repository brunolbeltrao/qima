package com.qima.market.hexagono.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    private Long id;
    private String name;

    public boolean validProduct(){
        //impement validations
        return true;
    }

}
