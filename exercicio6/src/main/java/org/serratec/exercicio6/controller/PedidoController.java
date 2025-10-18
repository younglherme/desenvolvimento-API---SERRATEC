package org.serratec.exercicio6.controller;

import jakarta.validation.Valid;
import org.serratec.exercicio6.domain.Endereco;
import org.serratec.exercicio6.domain.Pedido;
import org.serratec.exercicio6.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido inserirPedido( @RequestBody Pedido pedido){
        return pedidoRepository.save(pedido);
    }
}