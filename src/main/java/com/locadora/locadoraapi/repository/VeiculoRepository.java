package com.locadora.locadoraapi.repository;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locadora.locadoraapi.model.Caminhao;
import com.locadora.locadoraapi.model.Carro;
import com.locadora.locadoraapi.model.Moto;
import com.locadora.locadoraapi.model.Onibus;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.helpers.TipoCarroEnum;

public interface VeiculoRepository  extends JpaRepository<Veiculo, Long>{

    @Query("FROM Veiculo v WHERE v.placa = ?1")
    Veiculo encontrarPelaPlaca(String placa);

    ArrayList<Moto> findByCilindrada(int cilindrada);

    ArrayList<Carro> findByTipo(TipoCarroEnum tipoCarro);

    ArrayList<Caminhao> findByCarga(int carga);

    ArrayList<Onibus>  findByCapacidadePassageiros(int passageiros);
    
}
