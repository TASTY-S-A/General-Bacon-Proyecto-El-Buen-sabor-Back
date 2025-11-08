package com.example.Foodstore.service;

import com.example.Foodstore.entity.dto.PedidoDTO;

import java.util.List;

public interface PedidoService {
    List<PedidoDTO> obtenerTodos();
    PedidoDTO obtenerPorId(Long id);
    PedidoDTO crear(PedidoDTO pedidosDTO);
    PedidoDTO eliminar(Long id);
    PedidoDTO actualizar(Long id, PedidoDTO pedidosDTO);
}
