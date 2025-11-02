package com.example.Foodstore.service;


import com.example.Foodstore.entity.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> obtenerTodos();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO crear(ProductoDTO productoDTO);
    ProductoDTO eliminar(Long id);
    ProductoDTO actualizar(Long id, ProductoDTO productoDTO);
}
