package org.serratec.exercicio4.controller;

import jakarta.validation.Valid;
import org.serratec.exercicio4.domain.Funcionario;
import org.serratec.exercicio4.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    //BUSCAR
    @GetMapping
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscar(@PathVariable Long id){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if(funcionario.isPresent()){
            return ResponseEntity.ok(funcionario.get());
        }
        return ResponseEntity.notFound().build();
    }
    //INSERIR
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario inserir(@Valid @RequestBody Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }
    //ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@Valid @PathVariable Long id, @RequestBody Funcionario funcionario) {
            Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
            if(!funcionarioOptional.isPresent()){
                return ResponseEntity.notFound().build();
            }
            funcionario.setId(id);
            funcionario = funcionarioRepository.save(funcionario);
            return ResponseEntity.ok(funcionario);
        }
    //DELETE
     @DeleteMapping("/{id}")
     public ResponseEntity<Funcionario> deletar(@PathVariable Long id){
            if(!funcionarioRepository.existsById(id)){
                return ResponseEntity.notFound().build();
            }
            funcionarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

}