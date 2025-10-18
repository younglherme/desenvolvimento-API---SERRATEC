package org.serratec.projetoservicedto.Controller;

import jakarta.validation.Valid;
import org.serratec.projetoservicedto.DTO.UsuarioDTO;
import org.serratec.projetoservicedto.DTO.UsuarioInserirDTO;
import org.serratec.projetoservicedto.Entity.Usuario;
import org.serratec.projetoservicedto.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> inserir(@Valid @RequestBody UsuarioInserirDTO usuarioInserirDTO) {

        UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(usuarioDTO);
    }
}