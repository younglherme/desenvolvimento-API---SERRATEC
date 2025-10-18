package org.serratec.serratecmusic.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.serratec.serratecmusic.entity.Artista;
import org.serratec.serratecmusic.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Artistas", description = "Gerenciamento de artistas")
@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaRepository artistaRepository;

    @GetMapping
    public List<Artista> listarTodos() {
        List<Artista> artistas = artistaRepository.findAll();
        System.out.println(">>> Total de artistas: " + artistas.size());
        artistas.forEach(artista -> System.out.println(">>> Artista: " + artista.getNome()));
        return artistas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> buscarPorId(@PathVariable Long id) {
        return artistaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Artista> criarArtista(@RequestBody @Valid Artista artista) {
        Artista salvo = artistaRepository.save(artista);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> atualizar(@PathVariable Long id, @RequestBody @Valid Artista artistaAtualizado) {
        return artistaRepository.findById(id)
                .map(artista -> {
                    artista.setNome(artistaAtualizado.getNome());
                    artista.setNacionalidade(artistaAtualizado.getNacionalidade());
                    artistaRepository.save(artista);
                    return ResponseEntity.ok(artista);
                }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return artistaRepository.findById(id)
                .map(artista -> {
                    artistaRepository.delete(artista);
                    return ResponseEntity.noContent().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}