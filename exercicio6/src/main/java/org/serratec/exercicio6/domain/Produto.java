package org.serratec.exercicio6.domain;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @Column
    private double valor;

    @ManyToMany(mappedBy = "produtos")
    private List<Pedido> pedidos;

    public Produto() {}

    public Produto(Long id, String descricao, double valor, List<Pedido> pedidos) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.pedidos = pedidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}