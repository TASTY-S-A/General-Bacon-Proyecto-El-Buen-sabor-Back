package com.example.Foodstore.controller;

import com.example.Foodstore.entity.Usuario;
import com.example.Foodstore.entity.dto.UsuarioDTO;
import com.example.Foodstore.entity.mapper.UsuarioMapper;
import com.example.Foodstore.service.AuthService;
import com.example.Foodstore.service.UsuarioService;
import com.example.Foodstore.utils.Sha256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody Usuario request) {
        Usuario usuario = authService.obtenerPorMail(request.getMail());
        if (usuario == null) {
            return ResponseEntity.status(401).body(null);
        }
        if (!usuario.getContrasenia().equals(Sha256Util.hash(request.getContrasenia()))) {
            return ResponseEntity.status(401).body(null);
        }
        UsuarioDTO dto = usuarioMapper.toDto(usuario);
        dto.setContrasenia(null);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> crear(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = authService.obtenerPorMail(usuarioDTO.getMail());
        if (usuarioExistente != null) {
            return ResponseEntity.status(409).body(null);
        }
        UsuarioDTO nuevoUsuario = usuarioService.crear(usuarioDTO);
        return ResponseEntity.status(201).body(nuevoUsuario);
    }

}
