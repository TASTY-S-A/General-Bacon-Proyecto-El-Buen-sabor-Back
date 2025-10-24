package com.example.Foodstore.entity;

import com.example.Foodstore.entity.Enums.Estado;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class Pedido extends Base{
    private LocalDate fecha;
    private Double total;
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
