package org.serratec.exercicio6.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message ="Logradouro é Obrigatório!")
    @Size(max = 100)
    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @NotBlank(message ="Bairro é Obrigatório!")
    @Size(max = 100)
    @Column(name = "bairro", nullable = false, length = 100)
    private String bairro;

    @NotBlank(message ="Complemento é Obrigatório!")
    @Size(max = 100)
    @Column(name = "complemento", nullable = false, length = 100)
    private String complemento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}