package com.example.Foodstore.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CategoriaDTO {
    private Long id;
    private String nombre;
    private List<ProductoDTO> productos;
}
