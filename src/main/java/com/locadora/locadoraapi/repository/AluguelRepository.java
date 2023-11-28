package com.locadora.locadoraapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.locadora.locadoraapi.model.Aluguel;

public interface AluguelRepository  extends JpaRepository<Aluguel, Long>{

    Aluguel findFirstByVeiculoPlacaAndBaixoFalse(String placa);
    
}
