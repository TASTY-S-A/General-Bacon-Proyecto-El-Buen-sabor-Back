package com.example.Foodstore.entity.mapper;


import com.example.Foodstore.entity.Categoria;
import com.example.Foodstore.entity.dto.CategoriaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoriaMapper {
    private final ProductoMapper productoMapper;

    public CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) return null;
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .productos(
                        categoria.getProductos() != null
                                ? categoria.getProductos().stream()
                                .map(productoMapper::toDto)
                                .collect(Collectors.toList())
                                : List.of()
                )
                .build();
    }

    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) return null;

        return Categoria.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .build();
    }
}
