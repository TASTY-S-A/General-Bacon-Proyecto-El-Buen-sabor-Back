package com.example.Foodstore.entity;

import com.example.Foodstore.entity.Enums.Estado;
import com.example.Foodstore.entity.Enums.MetodoPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Pedido extends Base{
    private LocalDate fecha;
    private Double total;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String direccion;
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @ManyToMany
    @JoinTable(
            name = "pedido_producto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
