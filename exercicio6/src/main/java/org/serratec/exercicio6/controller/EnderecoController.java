package org.serratec.exercicio6.controller;


import jakarta.validation.Valid;
import org.serratec.exercicio6.domain.Cliente;
import org.serratec.exercicio6.domain.Endereco;
import org.serratec.exercicio6.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco inserirEndereco(@RequestBody Endereco endereco){
        return enderecoRepository.save(endereco);
    }

}