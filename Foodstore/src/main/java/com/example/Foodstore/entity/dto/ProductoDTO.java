package com.example.Foodstore.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private String imagen;
    private CategoriaDTO categoria;
}
