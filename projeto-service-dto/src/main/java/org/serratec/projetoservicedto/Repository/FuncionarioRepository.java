package org.serratec.projetoservicedto.Repository;

import org.serratec.projetoservicedto.DTO.FuncionarioSalarioDTO;
import org.serratec.projetoservicedto.Entity.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query("SELECT f from Funcionario  f WHERE f.salario>= :salarioMinimo AND " +
            "f.salario <= :salarioMaximo")
    Page<Funcionario> buscarSalario (Double salarioMinimo, Double salarioMaximo, Pageable pageable);

    @Query("SELECT f FROM Funcionario f WHERE UPPER(f.nome) like UPPER(CONCAT('%', :paramNome, '%'))")
    Page<Funcionario> buscarNome (String paramNome, Pageable pageable);

    @Query(value = "select date_part('year',age(now(), data_nascimento)) as idade,"
            + " avg(salario) as mediaSalario," + " min(salario) as menorSalario,"
            + " max(salario) as maiorSalario," + " count(*) as totalFuncionarios "
            + " from funcionario " + " group by idade " + " having count(*)>1 "
            + " order by idade desc ",nativeQuery = true)
    List<FuncionarioSalarioDTO> buscaSalariosPorIdade();

    Page<Funcionario> findBySalarioBetween(Double valorMinimo, Double valorMaximo, Pageable pageable);

    Page<Funcionario> findByNomeContainingIgnoreCase(String paramNome, Pageable pageable);
}
