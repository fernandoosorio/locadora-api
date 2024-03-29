package com.locadora.locadoraapi.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.locadoraapi.exception.ClienteNaoCadastrado;
import com.locadora.locadoraapi.exception.VeiculoAlugado;
import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.exception.VeiculoNaoAlugado;
import com.locadora.locadoraapi.exception.VeiculoNaoCadastrado;
import com.locadora.locadoraapi.model.Aluguel;
import com.locadora.locadoraapi.model.Cliente;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.helpers.TipoCarroEnum;
import com.locadora.locadoraapi.model.helpers.TipoVeiculoClassHelper;
import com.locadora.locadoraapi.repository.AluguelRepository;
import com.locadora.locadoraapi.repository.ClienteRepository;
import com.locadora.locadoraapi.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repositorio;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AluguelRepository aluguelRepository;

    public Veiculo inserir(Veiculo  veiculo)  throws VeiculoJaCadastrado {

        if( repositorio.existsByPlaca(veiculo.getPlaca()))
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


    public Aluguel registrarAluguel(String placa, Date dataInicio, int dias, Long cpf) {
        Veiculo  veiculo = Optional.ofNullable( this.repositorio.findByPlaca(placa) )
        .orElseThrow( () -> new VeiculoNaoCadastrado() );

        //verificar se o veiculo esta alugado
        Optional.ofNullable(this.aluguelRepository.findFirstByVeiculoPlacaAndBaixoFalse(placa) )
        .ifPresent( (aluguel) -> { throw new VeiculoAlugado();});

        Cliente cliente = Optional.ofNullable( this.clienteRepository.findByCpf(cpf) )
        .orElseThrow( () -> new ClienteNaoCadastrado() );

        LocalDateTime dataInicioDateTime = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDateTime dataFim = dataInicioDateTime.plusDays(dias);

        Double totalAluguel = veiculo.aluguel(dias);
        
        Aluguel aluguel = new Aluguel(cliente, veiculo, dataInicioDateTime, dataFim, totalAluguel);

        this.aluguelRepository.save(aluguel);   
        
        return aluguel;
    }


    public Aluguel registrarDevolucao(String placa, Date data, Long cpf) {

        Optional.ofNullable( this.repositorio.findByPlaca(placa) )
        .orElseThrow( () -> new VeiculoNaoCadastrado() );

        //verificar se o veiculo esta alugado
        Aluguel aluguel = Optional.ofNullable(this.aluguelRepository.findFirstByVeiculoPlacaAndBaixoFalse(placa) )
        .orElseThrow( () -> new VeiculoNaoAlugado() );

        Optional.ofNullable( this.clienteRepository.findByCpf(cpf) )
        .orElseThrow( () -> new ClienteNaoCadastrado() );

        LocalDateTime devolucao = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        aluguel.setDataDevolucaoReal(devolucao);
        aluguel.setBaixo(true);

        this.aluguelRepository.save(aluguel);   
        
        return aluguel;
    }


    public void depreciarVeiculos(int tipo, double taxaDepreciacao) {

        if(tipo == 0){
            this.repositorio.findAll().forEach( (veiculo) -> {
                veiculo.depreciar(taxaDepreciacao);
                this.repositorio.save(veiculo);
            });

        }else{
             this.repositorio.findAllVeiculosByType(TipoVeiculoClassHelper.getTipoVeiculoClass(tipo)).forEach( (veiculo) -> {
                veiculo.depreciar(taxaDepreciacao);
                this.repositorio.save(veiculo);
        });
        }
        
    }


    public void aumentarDiaria(int tipo, double taxaAumento) {
         if(tipo == 0){
            this.repositorio.findAll()
                .forEach( (veiculo) -> {
                    veiculo.aumentarDiaria(taxaAumento);
                    this.repositorio.save(veiculo);
            });
        }else{
          
            this.repositorio.findAllVeiculosByType(TipoVeiculoClassHelper.getTipoVeiculoClass(tipo)).forEach( (veiculo) -> {
                veiculo.aumentarDiaria(taxaAumento);
                this.repositorio.save(veiculo);
        });
        }

       
    }


    public double faturamentoTotal(int tipo, Date inicio, Date fim) {
        LocalDateTime inicioLocalDateTime = inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime fimLocalDateTime = fim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        if(tipo == 0){
                return this.aluguelRepository.findByDataInicioBetweenAndDataDevolucaoRealNotNullAndBaixoIsTrue(inicioLocalDateTime, fimLocalDateTime)
                    .stream()
                    .mapToDouble( (aluguel) ->  aluguel.getTotalAluguel() )
                    .sum();

        }else{
            return this.aluguelRepository.findByDataInicioBetweenAndVeiculoTipo(inicioLocalDateTime, fimLocalDateTime, TipoVeiculoClassHelper.getTipoVeiculoClass(tipo))
                .stream()
                .mapToDouble( (aluguel) -> aluguel.getTotalAluguel() )
                .sum();
        }
       
       
    }


    public int quantidadeTotalDeDiarias(int tipo, Date inicio, Date fim) {
        LocalDateTime inicioLocalDateTime = inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime fimLocalDateTime = fim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        
         if(tipo == 0){
                return this.aluguelRepository.findByDataInicioBetween(inicioLocalDateTime, fimLocalDateTime)
                    .stream()
                    .mapToInt( (aluguel) ->  aluguel.getDias() )
                    .sum();

        }else{
            return this.aluguelRepository.findByDataInicioBetweenAndVeiculoTipo(inicioLocalDateTime, fimLocalDateTime, TipoVeiculoClassHelper.getTipoVeiculoClass(tipo))
                .stream()
                .mapToInt( (aluguel) -> aluguel.getDias() )
                .sum();
        }
    }
    
}
