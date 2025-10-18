package org.serratec.aula5.repository;

import org.serratec.aula5.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
