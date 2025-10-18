package org.serratec.exercicio6.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private LocalDate dataPedido;

    @Column
    private LocalDate dataEntrega;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
            joinColumns = @JoinColumn (name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<Produto> produtos =  new ArrayList<>();

    public Pedido() {}

    public Pedido(Long id, LocalDate dataPedido, LocalDate dataEntrega, Cliente cliente, List<Produto> produtos) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.cliente = cliente;
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}