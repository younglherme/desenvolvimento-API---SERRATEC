package org.serratec.aula5.domain;


import jakarta.persistence.*;

@Entity
@Table(name="pessoa")
public class Pessoa {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;

    @Column
    private String nome;
    @Column
    private String email;

    public Pessoa() {}

    public Pessoa(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}