package org.serratec.projetoservicedto.Controller;

import org.serratec.projetoservicedto.DTO.FuncionarioSalarioDTO;
import org.serratec.projetoservicedto.Entity.Funcionario;
import org.serratec.projetoservicedto.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @GetMapping
    public ResponseEntity<List<Funcionario>> listar() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/pagina")
    public ResponseEntity<Page<Funcionario>> listaComPaginacao(@PageableDefault(sort="nome",direction = Sort.Direction.ASC,page = 0,size =8) Pageable pageable) {

        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/salario")
    public ResponseEntity<Page<Funcionario>> buscarPorSalario (@RequestParam(defaultValue = "0") Double salarioMinimo,
                                                               @RequestParam(defaultValue = "15000") Double salarioMaximo, Pageable pageable) {
            Page<Funcionario> funcionarios = funcionarioRepository
                    .findBySalarioBetween(salarioMinimo, salarioMaximo, pageable);

            return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/nome")
    public ResponseEntity<Page<Funcionario>> buscarPorNome (@RequestParam(defaultValue = "") String nome, Pageable pageable) {

        Page<Funcionario> funcionarios = funcionarioRepository
                .findByNomeContainingIgnoreCase(nome, pageable);

        return ResponseEntity.ok(funcionarios);

    }
    @GetMapping("/salarios-por-idade")
    public ResponseEntity<List<FuncionarioSalarioDTO>> buscaSalariosPorIdade()
    {
        return ResponseEntity.ok(funcionarioRepository.buscaSalariosPorIdade());
    }

}