package org.serratec.aula5.controller;

import jakarta.validation.Valid;
import org.serratec.aula5.domain.Veiculo;
import org.serratec.aula5.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    //Buscar
    @GetMapping
    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Veiculo> buscar(@PathVariable Long id){
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        if(veiculo.isPresent()){
            return ResponseEntity.ok(veiculo.get());
        }
        return ResponseEntity.notFound().build();
    }
    //INSERIR
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo save(@Valid @RequestBody Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }
    //ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar (@Valid @PathVariable Long id, @RequestBody Veiculo veiculo){
        Optional<Veiculo> veiculoAtualizado = veiculoRepository.findById(id);
        if(!veiculoAtualizado.isPresent()){
            return ResponseEntity.notFound().build();
        }
        veiculo.setId(id);
        veiculo = veiculoRepository.save(veiculo);
        return ResponseEntity.ok(veiculo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Veiculo> deletar (@PathVariable Long id){
        if(!veiculoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        veiculoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}