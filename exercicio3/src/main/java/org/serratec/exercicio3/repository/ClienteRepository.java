package org.serratec.exercicio3.repository;

import org.serratec.exercicio3.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}