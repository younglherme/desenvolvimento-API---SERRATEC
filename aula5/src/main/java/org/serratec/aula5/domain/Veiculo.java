package org.serratec.aula5.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="Preencha a placa!")
    @Size(max = 7)
    @Column(name = "placa",nullable = false, length = 7)
    private String placa;

    @NotNull(message = "Preencha a marca")
    @Size(max=30)
    @Column(name = "marca",nullable=false, length=30)
    private String marca;

    @NotNull(message = "Preencha o modelo")
    @Size(max=40)
    @Column(name = "modelo",nullable=false, length=40)
    private String modelo;

    @Embedded
    private Caracteristicas caracteristicas;

    public Veiculo() {}

    public Veiculo(Long id, String placa, String marca, String modelo) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Caracteristicas getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Caracteristicas caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}