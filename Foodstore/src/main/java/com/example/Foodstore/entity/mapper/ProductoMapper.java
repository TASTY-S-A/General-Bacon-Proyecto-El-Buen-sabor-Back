package com.example.Foodstore.entity.mapper;


import com.example.Foodstore.entity.Categoria;
import com.example.Foodstore.entity.Producto;
import com.example.Foodstore.entity.dto.ProductoDTO;
import com.example.Foodstore.entity.dto.CategoriaDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public ProductoDTO toDto(Producto producto) {
        if (producto == null) return null;
        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .imagen(producto.getImagen())
                .stock(producto.getStock())
                .categoria( //condición ? valor_si_verdadero : valor_si_falso
                        producto.getCategoria() != null
                                ? CategoriaDTO.builder()
                                .id(producto.getCategoria().getId())
                                .nombre(producto.getCategoria().getNombre())
                                .build()
                                : null
                )
                .build();
    }

    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) return null;
        return Producto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .imagen(dto.getImagen())
                .stock(dto.getStock())
                .categoria(
                        dto.getCategoria() != null
                                ? Categoria.builder()
                                .id(dto.getCategoria().getId())
                                .nombre(dto.getCategoria().getNombre())
                                .build()
                                : null
                )
                .build();
    }
}
