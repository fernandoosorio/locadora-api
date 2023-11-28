package com.locadora.locadoraapi.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.locadoraapi.exception.ClienteJaCadastrado;
import com.locadora.locadoraapi.exception.ClienteNaoCadastrado;
import com.locadora.locadoraapi.exception.VeiculoAlugado;
import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.exception.VeiculoNaoAlugado;
import com.locadora.locadoraapi.exception.VeiculoNaoCadastrado;
import com.locadora.locadoraapi.model.Cliente;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.DTO.ClienteDTO;
import com.locadora.locadoraapi.model.DTO.VeiculoDto;
import com.locadora.locadoraapi.service.ClienteService;
import com.locadora.locadoraapi.service.VeiculoService;


@RestController
public class LocadoraImplController implements Locadora {

    @Autowired
    private VeiculoService veiculoService;

     @Autowired
    private ClienteService clienteService;

    @Override
    public ResponseEntity<Veiculo> inserir(@RequestBody VeiculoDto veiculoDto) throws VeiculoJaCadastrado, SQLException {
        return ResponseEntity.ok ( this.veiculoService.inserir(veiculoDto.toVeiculo()) );
    }

    @Override
    public ResponseEntity<Cliente> inserir(@RequestBody  ClienteDTO clienteDTO) throws ClienteJaCadastrado, SQLException {
        return ResponseEntity.ok ( this.clienteService.inserir(clienteDTO.toCliente()) );
    }

    @Override
    public ResponseEntity<Veiculo> pesquisar(String placa) throws VeiculoNaoCadastrado {
        Veiculo  veiculo = Optional.ofNullable( this.veiculoService.getVeiculoByPlaca(placa) )
            .orElseThrow( () -> new VeiculoNaoCadastrado() );
      
        return ResponseEntity.ok ( veiculo  );
    }

    @Override
    public ResponseEntity< ArrayList<Veiculo> > pesquisarMoto(int cilindrada) {
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        veiculos.addAll( this.veiculoService.getVeiculoByCilindrada(cilindrada) );
        return ResponseEntity.ok (veiculos );
    }

    @Override
    public ResponseEntity< ArrayList<Veiculo> > pesquisarCarro(int tipoCarro) {
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        veiculos.addAll( this.veiculoService.getVeiculoByTipoCarro(tipoCarro) );
        return ResponseEntity.ok (veiculos );
    }

    @Override
    public ResponseEntity< ArrayList<Veiculo> > pesquisarCaminhao(int carga) {
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        veiculos.addAll( this.veiculoService.getVeiculoByCarga(carga) );
        return ResponseEntity.ok (veiculos );
    }

    @Override
    public ResponseEntity<ArrayList<Veiculo>> pesquisarOnibus(int passageiros) {
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        veiculos.addAll( this.veiculoService.getVeiculoByPassageiros(passageiros) );
        return ResponseEntity.ok (veiculos );
    }

    @Override
    public double calcularAluguel(String placa, int dias) throws VeiculoNaoCadastrado {
       return this.veiculoService.getValorTotalAluguel(placa, dias);
    }

    @Override
    public void registrarAluguel(String placa, Date data, int dias, Long cpf)
            throws VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado {
        
        this.veiculoService.registrarAluguel(placa, data, dias, cpf);
    }

    @Override
    public void registrarDevolucao(String placa, Date data, Long cpf)
            throws VeiculoNaoCadastrado, VeiculoNaoAlugado, ClienteNaoCadastrado {
        
        this.veiculoService.registrarDevolucao(placa, data, cpf);
    }

    @Override
    public void depreciarVeiculos(int tipo, double taxaDepreciacao) {
        throw new UnsupportedOperationException("Unimplemented method 'depreciarVeiculos'");
    }

    @Override
    public void aumentarDiaria(int tipo, double taxaAumento) {
        throw new UnsupportedOperationException("Unimplemented method 'aumentarDiaria'");
    }

    @Override
    public double faturamentoTotal(int tipo, Date inicio, Date fim) {

        throw new UnsupportedOperationException("Unimplemented method 'faturamentoTotal'");
    }

    @Override
    public int quantidadeTotalDeDiarias(int tipo, Date inicio, Date fim) {

        throw new UnsupportedOperationException("Unimplemented method 'quantidadeTotalDeDiarias'");
    }
    
}
