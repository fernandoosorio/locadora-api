package com.locadora.locadoraapi.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.locadora.locadoraapi.exception.ClienteJaCadastrado;
import com.locadora.locadoraapi.exception.ClienteNaoCadastrado;
import com.locadora.locadoraapi.exception.VeiculoAlugado;
import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.exception.VeiculoNaoAlugado;
import com.locadora.locadoraapi.exception.VeiculoNaoCadastrado;
import com.locadora.locadoraapi.model.Cliente;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.DTO.VeiculoDto;

@RequestMapping("/locadora")
public interface Locadora {

    @PostMapping("/crete/veiculo") 
    public  ResponseEntity<Veiculo> inserir(@RequestBody  VeiculoDto v) throws VeiculoJaCadastrado, SQLException;

	@PostMapping("/crete/cliente") 
    public void inserir(@RequestBody  Cliente c) throws ClienteJaCadastrado, SQLException;

	@GetMapping("/pesquisar/veiculoByPlaca/{placa}") 
    public ResponseEntity<Veiculo> pesquisar(@PathVariable String placa) throws VeiculoNaoCadastrado; 

    @GetMapping("/pesquisar/motoByCilindrada/{cilindrada}") 
    public ResponseEntity< ArrayList<Veiculo> > pesquisarMoto(@PathVariable int cilindrada);
	// tipo de carro 1 (passeio), 2 (SUV), 3 (pickup)
    @GetMapping("/pesquisar/carroByTipoCarro/{tipoCarro}") 
    public ResponseEntity< ArrayList<Veiculo> > pesquisarCarro(@PathVariable int tipoCarro);

    @GetMapping("/pesquisar/caminhaoByCarga/{carga}") 
    public ResponseEntity< ArrayList<Veiculo> > pesquisarCaminhao(@PathVariable int carga);

    @GetMapping("/pesquisar/onibusByPassageiros/{passageiros}") 
    public ResponseEntity< ArrayList<Veiculo> > pesquisarOnibus(@PathVariable int passageiros);
    
    //Seguro Moto = (valor do bem * 11%)/365
   	//Seguro Carro = (valor do bem * 3%)/365
   	//Seguro Caminhão = (valor do bem * 8%)/365
   	//Seguro Ônibus = (valor do bem * 20%)/365
    //Aluguel = (valor da diária + seguro) * quantidade de dias
    @GetMapping("/calcular-aluguel/{placa}/{dias}") 
    public double calcularAluguel(@PathVariable String placa,@PathVariable int dias) throws VeiculoNaoCadastrado;
    
    @PostMapping("create/aluguel/{placa}/{data}/{dias}/{cpf}") 
    public void registrarAluguel(@PathVariable String placa,@PathVariable Date data,@PathVariable int dias,@PathVariable int cpf) throws VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado;
   
    @GetMapping("/registrar-devolucao/{placa}/{data}/{cpf}") 
    public void registrarDevolucao(@PathVariable String placa, @PathVariable Date data,@PathVariable int cpf) throws VeiculoNaoCadastrado, VeiculoNaoAlugado, ClienteNaoCadastrado;
   
	// tipo de veiculo
	// 0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus)
    @GetMapping("/calcular/depreciarVeiculos/{tipo}/{taxaDepreciacao}") 
    public void depreciarVeiculos(@PathVariable int tipo,@PathVariable  double taxaDepreciacao);
    @GetMapping("/calcular/aumentarDiaria/{tipo}/{taxaAumento}") 
    public void aumentarDiaria(@PathVariable int tipo, @PathVariable double taxaAumento);
    @GetMapping("/calcular/faturamentoTotal/{tipo}/{inicio}/{fim}") 
    public double faturamentoTotal(@PathVariable  int tipo,@PathVariable Date inicio, @PathVariable Date fim);
    @GetMapping("/calcular/quantidadeTotalDeDiarias/{tipo}/{inicio}/{fim}") 
    public int quantidadeTotalDeDiarias(@PathVariable int tipo, @PathVariable  Date inicio, @PathVariable  Date fim);
    
}
