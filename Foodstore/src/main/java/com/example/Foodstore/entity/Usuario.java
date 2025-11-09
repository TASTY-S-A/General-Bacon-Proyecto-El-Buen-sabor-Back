package com.example.Foodstore.entity;

import com.example.Foodstore.entity.Enums.Rol;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Usuario extends Base{
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Pedido> pedidos = new ArrayList<>();
}
