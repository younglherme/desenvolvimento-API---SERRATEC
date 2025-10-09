package org.serratec.aula2.controller;


import org.serratec.aula2.domain.Aluno;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private static List<Aluno> lista = new ArrayList<Aluno>();

    static {
        lista.add(new Aluno(2354L,"Carla", "2246-29823"));
        lista.add(new Aluno(2343L,"Carlos","2242-9001"));
        lista.add(new Aluno(1409L,"Maria", "22431-9094"));

    }

    @GetMapping
    public List<Aluno> listar(){
        return lista;
    }
    @GetMapping("/{matricula}")
    public Aluno buscar (@PathVariable Long matricula){

        return lista.stream().filter(a ->
                a.getMatricula()
                .equals(matricula))
                .findFirst()
                .orElse(null);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno inserir(@RequestBody Aluno aluno){
        lista.add(aluno);
        return aluno;
    }
    @DeleteMapping("/{matricula}")
    public void delete(@PathVariable Long matricula){
        for(int i = 0; i< lista.size();i++){
            if(lista.get(i).getMatricula().equals(matricula)){
                lista.remove(i);
                break;
            }
        }
    }
    @PutMapping("/{matricula}")
    public Aluno atualizar(@RequestBody Aluno aluno, @PathVariable Long matricula){

        for(int i = 0; i<lista.size();i++){
            if (lista.get(i).getMatricula().equals(matricula)){
                Aluno a = new Aluno(
                        matricula,
                        aluno.getNome(),
                        aluno.getTelefone());
                lista.set(i,a);
                return a;
            }
        }
        return null;
    }

}