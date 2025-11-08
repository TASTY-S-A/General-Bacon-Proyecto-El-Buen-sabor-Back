package com.example.Foodstore.entity.dto;

import com.example.Foodstore.entity.Enums.Estado;
import com.example.Foodstore.entity.Enums.MetodoPago;
import com.example.Foodstore.entity.Producto;
import com.example.Foodstore.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PedidoDTO{
    private Long id;
    private LocalDateTime fecha;
    private Double total;
    private Estado estado;
    private String direccion;
    private MetodoPago metodoPago;
    private List<ProductoDTO> productos;
    private Usuario usuario;
}
