package org.serratec.exercicio6.controller;


import jakarta.validation.Valid;
import org.serratec.exercicio6.domain.Cliente;
import org.serratec.exercicio6.repository.ClienteRepository;
import org.serratec.exercicio6.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente inserirCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }



}