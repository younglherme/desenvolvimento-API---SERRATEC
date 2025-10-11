package org.serratec.exercicio3.controller;

import jakarta.validation.Valid;
import org.serratec.exercicio3.domain.Cliente;
import org.serratec.exercicio3.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    //BUSCAR
    @GetMapping("/{id_cliente}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id_cliente){
        Optional<Cliente> cliente = clienteRepository.findById(id_cliente);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }
    //INSERIR
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente inserir(@Valid @RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }
    //ATUALIZAR
    @PutMapping("/{id_cliente}")
    public ResponseEntity<Cliente> atualizar(@Valid  @PathVariable Long id_cliente, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id_cliente);
        if(!clienteOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        cliente.setId_cliente(id_cliente);
        cliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }
    //DELETE
    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<Cliente> deletar(@PathVariable Long id_cliente){
        if(!clienteRepository.existsById(id_cliente)){
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id_cliente);
        return ResponseEntity.ok().build();
    }
    }
