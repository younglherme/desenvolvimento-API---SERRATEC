package org.serratec.aula2.controller;


import jakarta.validation.Valid;
import org.serratec.aula2.domain.Aluno;
import org.serratec.aula2.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> listar(){
        return alunoRepository.findAll();
    }
    //BUSCAR
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id){
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isPresent()){
            return ResponseEntity.ok(aluno.get());
        }
        return ResponseEntity.notFound().build();
    }
    //BUSCAR PELO NOME
    @GetMapping("/buscar")
    public ResponseEntity<List<Aluno>> buscarPorNome(@RequestParam String nome) {
        List<Aluno> alunos = alunoRepository.buscarPorNome(nome);
        return ResponseEntity.ok(alunos);
    }

    //INSERIR
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno inserir(@Valid @RequestBody Aluno aluno){
        return alunoRepository.save(aluno);
    }
    //ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@Valid @PathVariable Long id, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if(!alunoOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        aluno.setId(id);
        aluno = alunoRepository.save(aluno);
        return ResponseEntity.ok(aluno);
    }
    //DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(!alunoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        alunoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}