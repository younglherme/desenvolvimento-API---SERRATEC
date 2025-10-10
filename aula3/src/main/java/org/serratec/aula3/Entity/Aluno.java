package org.serratec.aula3.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matricula", nullable = false, unique = true)
    private Long matricula;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

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
}