package com.example.Foodstore.controller;


import com.example.Foodstore.entity.dto.CategoriaDTO;
import com.example.Foodstore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/categoria")

public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> obtenerTodos(){
        return categoriaService.obtenerTodos();
    }
    @GetMapping("/{id}")
    public CategoriaDTO obtenerPorId(@PathVariable Long id){
        return categoriaService.obtenerPorId(id);
    }
    @PostMapping
    public CategoriaDTO crear(@RequestBody CategoriaDTO categoriaDTO){
        return categoriaService.crear(categoriaDTO);
    }
    @PutMapping("/{id}")
    public CategoriaDTO actualizar(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO){
        return categoriaService.actualizar(id, categoriaDTO);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        categoriaService.eliminar(id);
    }


}
