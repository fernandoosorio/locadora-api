package com.locadora.locadoraapi.model.DTO;

import com.locadora.locadoraapi.model.Caminhao;
import com.locadora.locadoraapi.model.Carro;
import com.locadora.locadoraapi.model.Moto;
import com.locadora.locadoraapi.model.Onibus;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.helpers.TipoCarroEnum;

public record VeiculoDto(   String marca, 
                            String modelo,
                            Integer anoDeFabricacao,
                            double valorDoBem,
                            double valorDiaria,
                            String placa,
                            Integer capacidadePassageiros,
                            Integer cilindrada,
                            Integer carga,
                            TipoCarroEnum tipo
                        ) { 
    
    public Veiculo toVeiculo() {
        if(capacidadePassageiros != null) return new Onibus(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa, capacidadePassageiros);
        if(cilindrada != null) return new Moto(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa, cilindrada);   
        if(carga != null) return new Caminhao(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa, carga); 
        if(tipo != null) return new Carro(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa, tipo);
        return null; 
    
    }
}


