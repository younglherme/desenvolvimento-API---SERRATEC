package org.serratec.projetoservicedto.Repository;

import org.serratec.projetoservicedto.Entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}