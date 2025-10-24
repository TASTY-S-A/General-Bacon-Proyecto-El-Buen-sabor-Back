package com.example.Foodstore.service;

import com.example.Foodstore.entity.Usuario;
import com.example.Foodstore.repository.UsuarioRepository;
import com.example.Foodstore.utils.Sha256Util;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario register(Usuario usuario) {
        usuario.setContrasenia(Sha256Util.hash(usuario.getContrasenia()));
        return usuarioRepository.save(usuario);
    }
    public Usuario obtenerPorMail(String mail) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByMail(mail);
        return usuarioOpt.orElse(null);
    }


}
