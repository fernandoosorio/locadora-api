package com.locadora.locadoraapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repositorio;


    public Veiculo inserirVeiculo(Veiculo  v) {

        if( repositorio.encontrarPelaPlaca(v.getPlaca()) != null)
            throw new VeiculoJaCadastrado();
        
        return repositorio.save(v);
    }
    
}
