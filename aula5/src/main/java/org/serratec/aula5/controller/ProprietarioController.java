package org.serratec.aula5.controller;

import jakarta.validation.Valid;
import org.serratec.aula5.domain.Proprietario;
import org.serratec.aula5.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @GetMapping
    public ResponseEntity<List<Proprietario>> listarProprietarios() {
        List<Proprietario> proprietarios = proprietarioRepository.findAll();
        return ResponseEntity.ok(proprietarios);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario criarProprietario(@Valid @RequestBody Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);
    }

    @PostMapping("/lista")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Proprietario> criarListaProprietarios(@Valid @RequestBody List<Proprietario> proprietarios) {
        return proprietarioRepository.saveAll(proprietarios);
    }
}