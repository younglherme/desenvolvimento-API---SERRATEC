package org.serratec.serratecmusic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.serratecmusic.entity.Perfil;
import org.serratec.serratecmusic.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Perfis", description = "Gerenciamento de perfis")
@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @Operation(summary = "Lista todos os Perfis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando todos os Perfis"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<Perfil> listarTodos() {
        return perfilRepository.findAll();
    }

    @Operation(summary = "Lista Perfis por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando o Perfil"),
            @ApiResponse(responseCode = "400", description = "Perfil informado não foi encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id) {
        return perfilRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Atualiza um novo Perfil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Perfil Registrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar o peril"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizaPerfil(@PathVariable Long id, @RequestBody Perfil perfilAtualizado) {
        return perfilRepository.findById(id)
                .map(perfil -> {
                    perfil.setTelefone(perfilAtualizado.getTelefone());
                    perfil.setDataNascimento(perfilAtualizado.getDataNascimento());
                    Perfil salvo = perfilRepository.save(perfil);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Remove um perfil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Perfil deletado"),
            @ApiResponse(responseCode = "404", description = "Perfil não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPerfil(@PathVariable Long id) {
        return perfilRepository.findById(id)
                .map(perfil -> {
                    perfilRepository.delete(perfil);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}