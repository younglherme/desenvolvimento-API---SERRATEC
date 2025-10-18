package org.serratec.serratecmusic.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.serratec.serratecmusic.Enum.Genero;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "musica")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Schema(description = "Titulo")
    @NotBlank(message = "Título é obrigatório")
    @Column
    private String titulo;

    @Schema(description = "Minutos da Musica")
    @Column
    @Min(1)
    private Integer minutos;

    @Schema(description = "Gênero Musical")
    @NotNull(message = "Gênero é obrigatório")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @ManyToMany
    @JoinTable(
            name = "musica_artista",
            joinColumns = @JoinColumn(name = "id_musica"),
            inverseJoinColumns = @JoinColumn(name = "id_artista"))
    @JsonManagedReference
    private List<Artista> artistas;

    @ManyToMany(mappedBy = "musicas")
    private List<Playlist> playlists;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }
}