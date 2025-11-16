package com.example.Foodstore.impl;

import com.example.Foodstore.entity.Categoria;
import com.example.Foodstore.entity.Producto;
import com.example.Foodstore.entity.dto.CategoriaDTO;
import com.example.Foodstore.entity.dto.PedidoDTO;
import com.example.Foodstore.entity.dto.ProductoDTO;
import com.example.Foodstore.entity.mapper.CategoriaMapper;
import com.example.Foodstore.entity.mapper.ProductoMapper;
import com.example.Foodstore.repository.CategoriaRepository;
import com.example.Foodstore.repository.ProductoRepository;
import com.example.Foodstore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoImpl implements ProductoService {
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;


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
    public Producto actualizar(Long id, ProductoDTO dto) {
        dto.setId(id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (dto.getNombre() != null) producto.setNombre(dto.getNombre());
        if (dto.getPrecio() != null) producto.setPrecio(dto.getPrecio());
        if (dto.getImagen() != null) producto.setImagen(dto.getImagen());
        if (dto.getStock() != null) producto.setStock(dto.getStock());
        if (dto.getCategoria() != null && dto.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            producto.setCategoria(categoria);
        }
        return productoRepository.save(producto);
    }

    @Override
    public List<ProductoDTO> obtenerPorCategoria(Long id) {
        return productoRepository.findAll().stream()
                .filter(p -> p.getEliminado() == null || !p.getEliminado())
                .filter(p -> id == 0 || p.getCategoria().getId().equals(id))
                .map(productoMapper::toDto)
                .toList();
    }

    @Override
    public ProductoDTO cambiarStock(Long id, Integer stock){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Integer stockFinal = producto.getStock() + stock;
        if (stockFinal > 0){
            producto.setStock(producto.getStock() + stock);
            return productoMapper.toDto(productoRepository.save(producto));
        }
        return null;
    }

}
