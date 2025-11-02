package com.example.Foodstore.impl;

import com.example.Foodstore.entity.Categoria;
import com.example.Foodstore.entity.Producto;
import com.example.Foodstore.entity.dto.CategoriaDTO;
import com.example.Foodstore.entity.dto.ProductoDTO;
import com.example.Foodstore.entity.mapper.ProductoMapper;
import com.example.Foodstore.repository.ProductoRepository;
import com.example.Foodstore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoImpl implements ProductoService {
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> obtenerTodos() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getEliminado() == null || !p.getEliminado()) {
                resultado.add(productoMapper.toDto(p));
            }
        }
        return resultado;
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .map(productoMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProductoDTO crear(ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        return productoMapper.toDto(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO eliminar(Long id) {
        productoRepository.findById(id)
                .ifPresent(producto -> {
                    producto.setEliminado(true);
                    productoRepository.save(producto);
                });
        return null;
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setImagen(productoDTO.getImagen());
        return productoMapper.toDto(productoRepository.save(producto));
    }
}
