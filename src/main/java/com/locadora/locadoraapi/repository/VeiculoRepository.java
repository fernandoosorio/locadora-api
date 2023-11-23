package com.locadora.locadoraapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locadora.locadoraapi.model.Veiculo;

public interface VeiculoRepository  extends JpaRepository<Veiculo, Long>{

    @Query("FROM Veiculo v WHERE v.placa = ?1")
    Veiculo encontrarPelaPlaca(String placa);
    
}
