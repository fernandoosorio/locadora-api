package com.locadora.locadoraapi.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
import com.locadora.locadoraapi.model.DTO.VeiculoDto;
import com.locadora.locadoraapi.service.VeiculoService;

@RestController
public class LocadoraImplController implements Locadora {

    @Autowired
    private VeiculoService veiculoService;

    @Override
    public ResponseEntity<Veiculo> inserir(@RequestBody VeiculoDto v) throws VeiculoJaCadastrado, SQLException {

        return ResponseEntity.ok ( this.veiculoService.inserirVeiculo(v.toVeiculo()) );
    }

    @Override
    public void inserir(@RequestBody  Cliente c) throws ClienteJaCadastrado, SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inserir'");
    }

    @Override
    public Veiculo pesquisar(String placa) throws VeiculoNaoCadastrado {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pesquisar'");
    }

    @Override
    public ArrayList<Veiculo> pesquisarMoto(int cilindrada) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pesquisarMoto'");
    }

    @Override
    public ArrayList<Veiculo> pesquisarCarro(int tipoCarro) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pesquisarCarro'");
    }

    @Override
    public ArrayList<Veiculo> pesquisarCaminhao(int carga) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pesquisarCaminhao'");
    }

    @Override
    public ArrayList<Veiculo> pesquisarOnibus(int passageiros) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pesquisarOnibus'");
    }

    @Override
    public double calcularAluguel(String placa, int dias) throws VeiculoNaoCadastrado {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularAluguel'");
    }

    @Override
    public void registrarAluguel(String placa, Date data, int dias, int cpf)
            throws VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarAluguel'");
    }

    @Override
    public void registrarDevolucao(String placa, Date data, int cpf)
            throws VeiculoNaoCadastrado, VeiculoNaoAlugado, ClienteNaoCadastrado {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarDevolucao'");
    }

    @Override
    public void depreciarVeiculos(int tipo, double taxaDepreciacao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'depreciarVeiculos'");
    }

    @Override
    public void aumentarDiaria(int tipo, double taxaAumento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aumentarDiaria'");
    }

    @Override
    public double faturamentoTotal(int tipo, Date inicio, Date fim) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'faturamentoTotal'");
    }

    @Override
    public int quantidadeTotalDeDiarias(int tipo, Date inicio, Date fim) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quantidadeTotalDeDiarias'");
    }

    

//     @Autowired
//     private VeiculoService veiculoService;

//     @GetMapping
//     public ResponseEntity<?> getAllVeiculos(){
//         return  ResponseEntity.ok( "Retorno ok" ) ;
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Veiculo> getVeiculoById(@PathVariable String id){
//         return  ResponseEntity.ok( veiculoService.getVeiculo(id) ) ;
//   }


    
}
