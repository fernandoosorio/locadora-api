package com.locadora.locadoraapi.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    @Column
    private Long cpf;
    @Column
    private String nome;
    @Column
    private String telefone;
    @Column
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Aluguel> alugueis;

    public Cliente() {
    }

    //criar construtor com todos os atributos
    public Cliente(String nome, Long cpf,  String telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCpf() {
        return cpf;
    }
    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }


    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Aluguel> getAlugueis() {
        return alugueis;
    }

    

}
