package com.example.Foodstore.service;

import com.example.Foodstore.entity.dto.CategoriaDTO;

import java.util.List;


public interface CategoriaService {
    List<CategoriaDTO> obtenerTodos();
    CategoriaDTO obtenerPorId(Long id);
    CategoriaDTO crear(CategoriaDTO categoriaDTO);
    CategoriaDTO eliminar(Long id);
    CategoriaDTO actualizar(Long id, CategoriaDTO categoriaDTO);
}
