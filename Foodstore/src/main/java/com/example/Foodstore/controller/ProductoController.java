package com.example.Foodstore.controller;

import com.example.Foodstore.entity.dto.ProductoDTO;
import com.example.Foodstore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/productos")

public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<ProductoDTO> listar(){
        return productoService.obtenerTodos();
    }
    @GetMapping("/{id}")
    public ProductoDTO obtenerPorId(@PathVariable Long id){
        return productoService.obtenerPorId(id);
    }
    @PostMapping
    public ProductoDTO crear(@RequestBody ProductoDTO productoDTO){
        return productoService.crear(productoDTO);
    }
    @PutMapping("/{id}")
    public ProductoDTO actualizar(@PathVariable Long id, @RequestBody ProductoDTO productoDTO){
        return productoService.actualizar(id, productoDTO);
    }
    @PostMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id){
        productoService.eliminar(id);
    }


    @GetMapping("/obtenerPorCategoria/{id}")
    public List<ProductoDTO> obtenerPorCategoria(@PathVariable Long id){
        return productoService.obtenerPorCategoria(id);
    }
}
