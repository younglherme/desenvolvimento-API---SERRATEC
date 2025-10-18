package org.serratec.serratecmusic.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.serratec.serratecmusic.Enum.Genero;

import java.util.List;

public class MusicaDTO {
    @NotBlank
    private String titulo;

    @Min(1)
    private Integer minutos;

    @NotNull
    private Genero genero;

    @NotEmpty
    private List<Long> artistaIds;

    public MusicaDTO() {
    }

    public MusicaDTO(String titulo, Integer minutos, Genero genero, List<Long> artistaIds) {
        this.titulo = titulo;
        this.minutos = minutos;
        this.genero = genero;
        this.artistaIds = artistaIds;
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

    public List<Long> getArtistaIds() {
        return artistaIds;
    }

    public void setArtistaIds(List<Long> artistaIds) {
        this.artistaIds = artistaIds;
    }
}