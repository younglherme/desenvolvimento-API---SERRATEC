package org.serratec.projetoservicedto.Repository;

import org.serratec.projetoservicedto.Entity.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil,Long>{
}