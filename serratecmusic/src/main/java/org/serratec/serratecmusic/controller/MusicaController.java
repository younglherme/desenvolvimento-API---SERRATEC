package org.serratec.serratecmusic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.serratec.serratecmusic.DTO.MusicaDTO;
import org.serratec.serratecmusic.entity.Artista;
import org.serratec.serratecmusic.entity.Musica;
import org.serratec.serratecmusic.repository.ArtistaRepository;
import org.serratec.serratecmusic.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Tag(name = "Músicas", description = "Gerenciamento de músicas")
@RestController
@RequestMapping("/musicas")
public class MusicaController {
    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Operation(summary = "Lista todas as musicas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando todos as musicas"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<Musica> listarTodas() {
        return musicaRepository.findAll();
    }

    @Operation(summary = "Lista musica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando a musica"),
            @ApiResponse(responseCode = "400", description = "A musica informada não foi encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Musica> buscarPorId(@PathVariable Long id) {
        return musicaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Inserindo uma nova musica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "musica Registrada"),
            @ApiResponse(responseCode = "400", description = "Erro ao registrar"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<Musica> criarMusica(@RequestBody @Valid MusicaDTO musicaDTO) {
        Musica musica = new Musica();
        musica.setTitulo(musicaDTO.getTitulo());
        musica.setMinutos(musicaDTO.getMinutos());
        musica.setGenero(musicaDTO.getGenero());


        List<Artista> artistas = artistaRepository.findAllById(musicaDTO.getArtistaIds());
        if (artistas.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        musica.setArtistas(artistas);

        Musica salva = musicaRepository.save(musica);

        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @Operation(summary = "Atualiza uma musica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "musica atualizada"),
            @ApiResponse(responseCode = "404", description = "musica não encontrada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(@PathVariable Long id, @Valid @RequestBody Musica musica) {
        if (!musicaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        musica.setId(id);
        musica = musicaRepository.save(musica);

        return ResponseEntity.ok(musica);
    }

    @Operation(summary = "Remove uma musica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "musica excluida"),
            @ApiResponse(responseCode = "404", description = "musica não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return musicaRepository.findById(id)
                .map(musica -> {
                    musicaRepository.delete(musica);
                    return ResponseEntity.noContent().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}