package com.example.Foodstore.controller;


import com.example.Foodstore.entity.dto.PedidoDTO;
import com.example.Foodstore.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<PedidoDTO> obtenerTodos(){
        return pedidoService.obtenerTodos();
    }
    @GetMapping("/{id}")
    public PedidoDTO obtenerPorId(@PathVariable Long id){
        return pedidoService.obtenerPorId(id);
    }
    @PostMapping
    public PedidoDTO crear(@RequestBody PedidoDTO pedidoDTO){
        return pedidoService.crear(pedidoDTO);
    }
    @PutMapping("/actualizar/{id}")
    public PedidoDTO actualizar(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO){
        return pedidoService.actualizar(id, pedidoDTO);
    }
    @PostMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id){
        pedidoService.eliminar(id);
    }
}
