package org.serratec.exercicio4.repository;

import org.serratec.exercicio4.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}