package com.locadora.locadoraapi.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.helpers.TipoCarroEnum;
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


    public Veiculo getVeiculoByPlaca(String placa) {
        return repositorio.encontrarPelaPlaca(placa);
    }


    public Collection<? extends Veiculo> getVeiculoByCilindrada(int cilindrada) {
       return repositorio.findByCilindrada(cilindrada);
    }


    public Collection<? extends Veiculo> getVeiculoByTipoCarro(int tipoCarro) {
         return repositorio.findByTipo(TipoCarroEnum.values()[tipoCarro-1] ); // 1 - passeio, 2 - SUV, 3 - pickup, subtrai-se um pois o indice inicia em zero
    }


    public Collection<? extends Veiculo> getVeiculoByCarga(int carga) {
         return repositorio.findByCarga(carga);
    }


    public Collection<? extends Veiculo> getVeiculoByPassageiros(int passageiros) {
         return repositorio.findByCapacidadePassageiros(passageiros);
    }
    
}
