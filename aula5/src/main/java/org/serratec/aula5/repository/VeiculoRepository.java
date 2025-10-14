package org.serratec.aula5.repository;

import org.serratec.aula5.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}