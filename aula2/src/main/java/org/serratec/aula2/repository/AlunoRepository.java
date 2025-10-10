package org.serratec.aula2.repository;

import org.serratec.aula2.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {


    @Query("SELECT a FROM Aluno a WHERE a.nome = :nome ")
    List<Aluno> buscarPorNome(@Param("nome") String nome);
}