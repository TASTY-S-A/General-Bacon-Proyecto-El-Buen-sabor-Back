package com.example.Foodstore.impl;

import com.example.Foodstore.entity.Enums.Estado;
import com.example.Foodstore.entity.Pedido;
import com.example.Foodstore.entity.Producto;
import com.example.Foodstore.entity.Usuario;
import com.example.Foodstore.entity.dto.PedidoDTO;
import com.example.Foodstore.entity.mapper.PedidoMapper;

import com.example.Foodstore.entity.mapper.ProductoMapper;
import com.example.Foodstore.repository.PedidoRepository;
import com.example.Foodstore.repository.ProductoRepository;
import com.example.Foodstore.repository.UsuarioRepository;
import com.example.Foodstore.service.PedidoService;
import com.example.Foodstore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoImpl implements PedidoService {
    @Autowired
    private PedidoMapper pedidoMapper;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoService productoService;

    @Override
    public List<PedidoDTO> obtenerTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> resultado = new ArrayList<>();
        for (Pedido c : pedidos) {
            if (c.getEliminado() == null || !c.getEliminado()) {
                resultado.add(pedidoMapper.toDTO(c));
            }
        }
        return resultado;
    }

    @Override
    public PedidoDTO obtenerPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(pedidoMapper::toDTO)
                .orElse(null);
    }

    @Override
    public PedidoDTO crear(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);

        if (pedidoDTO.getUsuario() != null && pedidoDTO.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(pedidoDTO.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + pedidoDTO.getUsuario().getId()));
            pedido.setUsuario(usuario);
        }
        if(pedidoDTO.getEstado() == null) {
            pedido.setEstado(Estado.PENDIENTE);
        }
        if(pedidoDTO.getFecha() == null){
            pedido.setFecha(LocalDate.now());
        }
        if (pedidoDTO.getProductos() != null && !pedidoDTO.getProductos().isEmpty()) {
            List<Producto> productos = pedidoDTO.getProductos().stream()
                    .map(pdto -> productoRepository.findById(pdto.getId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + pdto.getId())))
                    .collect(Collectors.toList());
            pedido.setProductos(productos);
        }
        double total = pedido.getProductos().stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        pedido.setTotal(total);

        Pedido guardado = pedidoRepository.save(pedido);
        return pedidoMapper.toDTO(guardado);
    }

    @Override
    public PedidoDTO eliminar(Long id) {
        pedidoRepository.findById(id)
                .ifPresent(pedido -> {
                    pedido.setEliminado(true);
                    pedidoRepository.save(pedido);
                });
        return null;
    }

    @Override
    public PedidoDTO actualizar(Long id, PedidoDTO pedidoDTO) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setDireccion(pedidoDTO.getDireccion());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setEstado(pedidoDTO.getEstado());
        pedido.setMetodoPago(pedidoDTO.getMetodoPago());
        if (pedidoDTO.getProductos() != null) {
            pedido.setProductos(
                    pedidoDTO.getProductos().stream()
                            .map(productoMapper::toEntity)
                            .collect(Collectors.toList())
            );
        }
        if (pedidoDTO.getUsuario() != null) {
            pedido.setUsuario(pedidoDTO.getUsuario());
        }
        return pedidoMapper.toDTO(pedidoRepository.save(pedido));
    }

}
