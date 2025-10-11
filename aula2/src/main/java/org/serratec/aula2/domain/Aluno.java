package org.serratec.aula2.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message ="Matricula é Obrigatório!")
    @Column(name = "matricula", nullable = false, unique = true)
    private Long matricula;

    @NotBlank(message = "Nome é Obrigatório!")
    @Size(max = 50)
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "Telefone é Obrigatório!")
    @Size(max = 15)
    @Column(name = "telefone", nullable = false)
    private String telefone;


    public Aluno() {
    }

    public Aluno(Long matricula, String nome, String telefone) {
        this.matricula = matricula;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}