package org.serratec.projetoservicedto.Service;

import org.serratec.projetoservicedto.Entity.Perfil;
import org.serratec.projetoservicedto.Repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public Perfil buscarPorId(Long id) {
        Optional<Perfil> perfil = perfilRepository.findById(id);

        return perfil.get();
    }
}