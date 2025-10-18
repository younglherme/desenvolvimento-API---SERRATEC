package org.serratec.serratecmusic.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome")
    @NotBlank(message = "Nome é obrigatório")
    @Column
    private String nome;

    @Schema(description = "Nacionalidade")
    @NotBlank(message = "Nacionalidade é obrigatória")
    @Column
    private String nacionalidade;

    @JsonIgnore
    @JsonBackReference
    @ManyToMany(mappedBy = "artistas")
    private List<Musica> musicas;

    public Artista() {}

    public Artista(Long id, String nome, String nacionalidade, List<Musica> musicas) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.musicas = musicas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
}