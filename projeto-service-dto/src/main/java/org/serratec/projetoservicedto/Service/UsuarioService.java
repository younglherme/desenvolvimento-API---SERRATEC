package org.serratec.projetoservicedto.Service;

import jakarta.transaction.Transactional;
import org.serratec.projetoservicedto.DTO.UsuarioDTO;
import org.serratec.projetoservicedto.DTO.UsuarioInserirDTO;
import org.serratec.projetoservicedto.Entity.Perfil;
import org.serratec.projetoservicedto.Entity.Usuario;
import org.serratec.projetoservicedto.Entity.UsuarioPerfil;
import org.serratec.projetoservicedto.Exception.EmailException;
import org.serratec.projetoservicedto.Exception.SenhaException;
import org.serratec.projetoservicedto.Repository.PerfilRepository;
import org.serratec.projetoservicedto.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilService perfilService;

    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
        for (Usuario usuario : usuarios) {
            usuariosDTO.add(new UsuarioDTO(usuario));
        }

        return usuariosDTO;

        /*
         * return usuarioRepository .findAll() .stream() .map(UsuarioDTO::new)
         * .collect(Collectors.toList());
         */
    }

    @Transactional
    public UsuarioDTO inserir(UsuarioInserirDTO usuarioDTO) throws EmailException {

        if(!usuarioDTO.getSenha().equalsIgnoreCase(usuarioDTO.getConfirmaSenha())) {
            throw new SenhaException("Senha e Confirma Senha não conferem");
        }

        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()) != null) {
            throw new EmailException("Email já existente");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());

        Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();
        for (Perfil perfil : usuarioDTO.getPerfis()) {
            perfil = perfilService.buscarPorId(perfil.getId());
            UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil, LocalDate.now());
            usuarioPerfis.add(usuarioPerfil);
        }
        usuario.setUsuarioPerfis(usuarioPerfis);
        usuario = usuarioRepository.save(usuario);

        return new UsuarioDTO(usuario);
    }
}
