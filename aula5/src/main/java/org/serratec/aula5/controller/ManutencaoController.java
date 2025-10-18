package org.serratec.aula5.controller;

import jakarta.validation.Valid;
import org.serratec.aula5.domain.Manutencao;
import org.serratec.aula5.repository.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manutencao")
public class ManutencaoController {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @GetMapping
    public ResponseEntity<List<Manutencao>> listarManutencao() {
        return ResponseEntity.ok(manutencaoRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Manutencao> buscarPorId(@PathVariable Long id) {
        Optional<Manutencao> manutencao = manutencaoRepository.findById(id);
        if (manutencao.isPresent()) {
            return ResponseEntity.ok(manutencao.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Manutencao save(@Valid @RequestBody Manutencao manutencao){
        return manutencaoRepository.save(manutencao);
    }

}