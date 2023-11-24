package com.locadora.locadoraapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.locadoraapi.exception.ClienteJaCadastrado;
import com.locadora.locadoraapi.model.Cliente;
import com.locadora.locadoraapi.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repositorio;


    public Cliente inserir(Cliente  c) {

        if( repositorio.findByCpf(c.getCpf()) != null)
            throw new ClienteJaCadastrado();
        
        return repositorio.save(c);
    }


    
}
