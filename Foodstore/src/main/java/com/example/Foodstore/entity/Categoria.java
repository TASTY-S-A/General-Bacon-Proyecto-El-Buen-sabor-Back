package com.example.Foodstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Categoria extends Base{
    private String nombre;
    private String descripcion;
    private String imagen;
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;
}
