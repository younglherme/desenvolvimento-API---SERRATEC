package org.serratec.exercicio3.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @Column(name = "nome", nullable = false, length = 60)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate data_nasicmento;

    public Cliente() {
    }

    public Cliente(Long id_cliente, String nome, String cpf, String email, LocalDate data_nasicmento) {
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.data_nasicmento = data_nasicmento;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData_nasicmento() {
        return data_nasicmento;
    }

    public void setData_nasicmento(LocalDate data_nasicmento) {
        this.data_nasicmento = data_nasicmento;
    }
}