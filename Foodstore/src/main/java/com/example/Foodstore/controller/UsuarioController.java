package com.example.Foodstore.controller;

import com.example.Foodstore.entity.dto.UsuarioDTO;
import com.example.Foodstore.service.AuthService;
import com.example.Foodstore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/usuarios")

public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }


    @PostMapping
    public UsuarioDTO crear(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.crear(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
