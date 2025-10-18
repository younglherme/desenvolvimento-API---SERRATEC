package org.serratec.exercicio6.controller;

import jakarta.validation.Valid;
import org.serratec.exercicio6.domain.Produto;
import org.serratec.exercicio6.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto inserirProduto( @RequestBody Produto produto){
        return produtoRepository.save(produto);
    }
}