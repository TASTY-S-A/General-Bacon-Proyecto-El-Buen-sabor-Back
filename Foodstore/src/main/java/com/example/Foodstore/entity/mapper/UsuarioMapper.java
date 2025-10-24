package com.example.Foodstore.entity.mapper;


import com.example.Foodstore.entity.Usuario;
import com.example.Foodstore.entity.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDto(Usuario usuario) {
        if (usuario == null) return null;
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .mail(usuario.getMail())
                .contrasenia(usuario.getContrasenia())
                .apellido(usuario.getApellido())
                .celular(usuario.getCelular())
                .rol(usuario.getRol())
                .build();
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;
        return Usuario.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .mail(dto.getMail())
                .contrasenia(dto.getContrasenia())
                .celular(dto.getCelular())
                .rol(dto.getRol())
                .build();
    }
}
