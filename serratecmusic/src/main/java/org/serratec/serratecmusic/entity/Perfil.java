package org.serratec.serratecmusic.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Schema(description = "Telefone")
    @NotBlank(message = "Telefone é obrigatório")
    @Size(min=11, max = 11)
    private String telefone;

    @Schema(description = "Data de nascimento")
    @NotNull(message = "Informe a data de nascimento")
    @Past(message = "Data de nascimento inválida(deve ser inferior a data atual)")
    private LocalDate dataNascimento;

    public Perfil() {}

    public Perfil(Long id, String telefone, LocalDate dataNascimento) {
        this.id = id;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}