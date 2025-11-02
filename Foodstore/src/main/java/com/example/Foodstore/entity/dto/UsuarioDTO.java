package com.example.Foodstore.entity.dto;

import com.example.Foodstore.entity.Enums.Rol;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String mail;
    private String contrasenia;
    private String apellido;
    private String celular;
    private Boolean eliminado;
    private Rol rol;
}

