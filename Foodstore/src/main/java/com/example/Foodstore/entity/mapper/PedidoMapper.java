package com.example.Foodstore.entity.mapper;

import com.example.Foodstore.entity.Pedido;
import com.example.Foodstore.entity.Producto;
import com.example.Foodstore.entity.Usuario;
import com.example.Foodstore.entity.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor

public class PedidoMapper {

    private final ProductoMapper productoMapper;

    public PedidoDTO toDTO(Pedido pedido) {
        if (pedido == null) return null;

        return PedidoDTO.builder()
                .id(pedido.getId())
                .fecha(pedido.getFecha())
                .total(pedido.getTotal())
                .estado(pedido.getEstado())
                .cantidad(pedido.getCantidad())
                .direccion(pedido.getDireccion())
                .metodoPago(pedido.getMetodoPago())
                .productos(
                        pedido.getProductos() != null ?
                                pedido.getProductos().stream()
                                        .map(productoMapper::toDto)
                                        .collect(Collectors.toList())
                                : List.of()
                )
                .usuarioId(
                        pedido.getUsuario() != null ?
                                pedido.getUsuario().getId() :
                                null
                )
                .build();
    }

    public Pedido toEntity(PedidoDTO dto) {
        if (dto == null) return null;

        return Pedido.builder()
                .fecha(dto.getFecha())
                .total(dto.getTotal())
                .cantidad(dto.getCantidad())
                .estado(dto.getEstado())
                .direccion(dto.getDireccion())
                .metodoPago(dto.getMetodoPago())
                .productos(
                        dto.getProductos() != null ?
                                dto.getProductos().stream()
                                        .map(p -> Producto.builder().id(p.getId()).build())
                                        .collect(Collectors.toList())
                                : List.of()
                )
                .usuario(
                        dto.getUsuarioId() != null ?
                                Usuario.builder().id(dto.getUsuarioId()).build() :
                                null
                )
                .build();
    }
}
