package com.example.Foodstore.service;


import com.example.Foodstore.entity.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> obtenerTodos();
    UsuarioDTO obtenerPorId(Long id);
    UsuarioDTO crear(UsuarioDTO usuarioDTO);
    UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO);
    void eliminar(Long id);
}

