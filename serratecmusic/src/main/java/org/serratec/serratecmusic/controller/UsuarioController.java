package org.serratec.serratecmusic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.serratec.serratecmusic.entity.Usuario;
import org.serratec.serratecmusic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Usuários", description = "Gerenciamento de usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Operation(summary = "Lista todos os Usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando todos os Usuarios"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Operation(summary = "Lista usuario pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando o Usuario"),
            @ApiResponse(responseCode = "400", description = "Usuario informado não foi encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário Registrado"),
            @ApiResponse(responseCode = "400", description = "Erro no registro"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid Usuario usuario) {
        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Operation(summary = "Atualiza um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizaUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioAtualizado.getNome());
                    usuario.setEmail(usuarioAtualizado.getEmail());

                    if (usuarioAtualizado.getPerfil() != null) {
                        if (usuario.getPerfil() == null) {
                            usuario.setPerfil(usuarioAtualizado.getPerfil());
                        } else {
                            usuario.getPerfil().setTelefone(usuarioAtualizado.getPerfil().getTelefone());
                            usuario.getPerfil().setDataNascimento(usuarioAtualizado.getPerfil().getDataNascimento());
                        }
                    }

                    Usuario salvo = usuarioRepository.save(usuario);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Remove um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuarioRepository.delete(usuario);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
