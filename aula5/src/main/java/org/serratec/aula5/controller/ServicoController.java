package org.serratec.aula5.controller;

import jakarta.validation.Valid;
import org.serratec.aula5.domain.Manutencao;
import org.serratec.aula5.domain.Servico;
import org.serratec.aula5.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public ResponseEntity<List<Servico>> listar() {
        return ResponseEntity.ok(servicoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);

        if (servico.isPresent()) {
            return ResponseEntity.ok(servico.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico criar(@Valid @RequestBody Servico servico) {
        return servicoRepository.save(servico);
    }

    @PostMapping("/lista")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Servico> criarLista(@Valid @RequestBody List<Servico> servicos) {
        return servicoRepository.saveAll(servicos);
    }
}