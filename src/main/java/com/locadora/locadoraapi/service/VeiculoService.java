package com.locadora.locadoraapi.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.exception.VeiculoNaoCadastrado;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.helpers.TipoCarroEnum;
import com.locadora.locadoraapi.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repositorio;


    public Veiculo inserir(Veiculo  veiculo)  throws VeiculoJaCadastrado {

        if( repositorio.findByPlaca(veiculo.getPlaca()) != null)
            throw new VeiculoJaCadastrado();
        
        return repositorio.save(veiculo);
    }


    public Veiculo getVeiculoByPlaca(String placa)  {

        return Optional.ofNullable( repositorio.findByPlaca(placa) )
            .orElseThrow( () -> new VeiculoNaoCadastrado() );
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

    public double getValorTotalAluguel(String placa, int dias) {
        Veiculo  veiculo = Optional.ofNullable( this.repositorio.findByPlaca(placa) )
        .orElseThrow( () -> new VeiculoNaoCadastrado() );


        return veiculo.aluguel(dias);
    }
    
}
