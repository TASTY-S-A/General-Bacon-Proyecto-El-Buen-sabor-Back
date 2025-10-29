package com.example.Foodstore.impl;

import com.example.Foodstore.entity.Categoria;
import com.example.Foodstore.entity.dto.CategoriaDTO;
import com.example.Foodstore.entity.mapper.CategoriaMapper;
import com.example.Foodstore.repository.CategoriaRepository;
import com.example.Foodstore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaImpl implements CategoriaService {
    @Autowired
    private CategoriaMapper categoriaMapper;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> obtenerTodos() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDTO)
                .orElse(null);
    }

    @Override
    public CategoriaDTO crear(CategoriaDTO CategoriaDTO) {
        Categoria categoria = categoriaMapper.toEntity(CategoriaDTO);
        return categoriaMapper.toDTO(categoriaRepository.save(categoria));
    }

    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public CategoriaDTO actualizar(Long id, CategoriaDTO CategoriaDTO) {
        Categoria Categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrado"));
        Categoria.setNombre(CategoriaDTO.getNombre());
        return categoriaMapper.toDTO(categoriaRepository.save(Categoria));
    }
}
