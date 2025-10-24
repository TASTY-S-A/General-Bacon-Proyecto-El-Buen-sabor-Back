package com.example.Foodstore.impl;


import com.example.Foodstore.entity.Enums.Rol;
import com.example.Foodstore.entity.Usuario;
import com.example.Foodstore.entity.dto.UsuarioDTO;
import com.example.Foodstore.entity.mapper.UsuarioMapper;
import com.example.Foodstore.repository.UsuarioRepository;
import com.example.Foodstore.service.UsuarioService;
import com.example.Foodstore.utils.Sha256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDto)
                .orElse(null);
    }

    @Override
    public UsuarioDTO crear(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        if (usuario.getContrasenia() != null) {
            usuario.setContrasenia(Sha256Util.hash(usuario.getContrasenia()));
        }
        if (usuario.getRol() == null) {
            usuario.setRol(Rol.USUARIO);
        }
        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}

