package com.marcoscouto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private String name;
    private Integer stock;
    private Double price;
}
