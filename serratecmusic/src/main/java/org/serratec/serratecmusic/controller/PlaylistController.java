package org.serratec.serratecmusic.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.serratecmusic.entity.Artista;
import org.serratec.serratecmusic.entity.Musica;
import org.serratec.serratecmusic.entity.Playlist;
import org.serratec.serratecmusic.entity.Usuario;
import org.serratec.serratecmusic.repository.MusicaRepository;
import org.serratec.serratecmusic.repository.PlaylistRepository;
import org.serratec.serratecmusic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Tag(name = "Playlists", description = "Gerenciamento de playlists")
@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MusicaRepository musicaRepository;


    @Operation(summary = "Lista todas Playlists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando todas as Playlists"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<Playlist> listarTodas() {
        return playlistRepository.findAll();
    }

    @Operation(summary = "Lista playlist pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando a playlist"),
            @ApiResponse(responseCode = "400", description = "A playlist informada não foi encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        return playlist.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria uma nova Playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Playlist Criada"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da Playlist"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<Playlist> criarPlaylist(@RequestBody @Valid Playlist playlist, @RequestParam Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        playlist.setUsuario(usuario);
        Playlist salva = playlistRepository.save(playlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }


    @Operation(summary = "Atualiza uma playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Playlist atualizada"),
            @ApiResponse(responseCode = "404", description = "Playlist não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Playlist> atualizarMusicas(@PathVariable Long id, @RequestBody List<Long> musicaIds) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não encontrada"));
        List<Musica> musicas = musicaRepository.findAllById(musicaIds);
        playlist.setMusicas(musicas);
        Playlist salva = playlistRepository.save(playlist);
        return ResponseEntity.ok(salva);
    }


    @Operation(summary = "Exclui uma Playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Playlist excluida"),
            @ApiResponse(responseCode = "404", description = "Playlist não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlaylist(@PathVariable Long id) {
        return playlistRepository.findById(id)
                .map(playlist -> {
                    playlistRepository.delete(playlist);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}


