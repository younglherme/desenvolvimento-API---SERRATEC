package org.serratec.serratecmusic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "Playlist")
    @NotBlank(message = "Nome da playlist é obrigatório")
    @Column
    private String nome;

    @Schema(description = "Descrição")
    @Size(max = 500)
    @Column
    private String descricao;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "playlist_musica",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "musica_id"))
    @JsonIgnore
    private List<Musica> musicas;

    public Playlist() {}

    public Playlist(Integer id, String nome, String descricao, Usuario usuario, List<Musica> musicas) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.usuario = usuario;
        this.musicas = musicas;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

}