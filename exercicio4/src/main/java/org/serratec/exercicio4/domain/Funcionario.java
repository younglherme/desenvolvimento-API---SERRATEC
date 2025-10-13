package org.serratec.exercicio4.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name ="funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotBlank(message ="Nome é Obrigatório!")
    @Size(max = 100)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message ="CPF é Obrigatório!")
    @CPF(message ="CPF inválido!")
    @Size(max = 11)
    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @NotNull(message = "Salário é obrigatório")
    @Positive(message = "Salário deve ser positivo")
    @Column(name = "salario",nullable =false, length = 60)
    private BigDecimal salario;

    @PastOrPresent
    @Column(name = "data_Admissao", nullable = false)
    private LocalDate dataAdmissao;

    public Funcionario() {}

    public Funcionario(Long id,
                       String nome,
                       String cpf,
                       BigDecimal salario,
                       LocalDate dataAdmissao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
}