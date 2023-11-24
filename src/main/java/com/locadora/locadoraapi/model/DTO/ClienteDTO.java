package com.locadora.locadoraapi.model.DTO;

import com.locadora.locadoraapi.model.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDTO(    
    @NotBlank String nome,
    @NotNull Integer cpf,
    String telefone,
    String email
) {

    public Cliente toCliente() {
        return new Cliente(this.nome, this.cpf, this.telefone, this.email);
    }
}