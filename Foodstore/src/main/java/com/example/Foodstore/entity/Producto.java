package com.example.Foodstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Producto extends Base{
    private String nombre;
    private double precio;
    private String imagen;
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = true)
    @JsonIgnoreProperties("productos")
    private Categoria categoria;
}
