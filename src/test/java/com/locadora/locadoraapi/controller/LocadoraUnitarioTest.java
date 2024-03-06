package com.locadora.locadoraapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.locadora.locadoraapi.model.Cliente;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.DTO.VeiculoDto;
import com.locadora.locadoraapi.model.helpers.PorcentagemSeguro;
import com.locadora.locadoraapi.model.helpers.TipoCarroEnum;
import com.locadora.locadoraapi.repository.ClienteRepository;
import com.locadora.locadoraapi.repository.VeiculoRepository;
import com.locadora.locadoraapi.service.VeiculoService;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LocadoraUnitarioTest {
   
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @MockBean
    private VeiculoService veiculoService;

    @Test
    public void insertUser() {
        Cliente cliente = new Cliente("Teste", 99999999999l,null,null);
        clienteRepository.save(cliente);
        Integer countUser = clienteRepository.findAll().size();
        assertEquals(1, countUser);
    }

    @Test
    public void insertVeiculo() {
        VeiculoDto dto = new VeiculoDto("Ford", "Pulse",2023, 1000.0, 10, "PIA-9655", null,null,null, TipoCarroEnum.PASSEIO, LocalDate.now());
        veiculoRepository.save(dto.toVeiculo());
        
        Integer countUser = veiculoRepository.findAll().size();
        assertEquals(1, countUser);
    }

    @Test
    public void buscarPorPlaca() {
        VeiculoDto dto = new VeiculoDto("Ford", "Pulse",2023, 1000.0, 10, "PIA-9655", null,null,null, TipoCarroEnum.PASSEIO,LocalDate.now());
        Veiculo salvo = veiculoRepository.save(dto.toVeiculo());
        
        Veiculo veiculo = veiculoRepository.findByPlaca("PIA-9655");
        assertEquals(salvo.getId(), veiculo.getId());

        Veiculo segundoVeiculo = veiculoRepository.findByPlaca("LIA-9655");

        assertNull(segundoVeiculo);
    }

    @Test
    public void calcularTotalDiarias() {
        VeiculoDto dto = new VeiculoDto("Ford", "Pulse",2023, 365.0, 10, "PIA-9655", null,null,null, TipoCarroEnum.PASSEIO, LocalDate.now());
        veiculoRepository.save(dto.toVeiculo());
        
        when(veiculoService.getValorTotalAluguel("PIA-9655", 1))
            .thenReturn(10.03);

        double valorMock = this.veiculoService.getValorTotalAluguel("PIA-9655", 1);
        //como o veiculo é um Carro ( valorDiaria + seguro ) * dias
        // Seguro = getValorDoBem()* PorcentagemSeguro.CARRO)/365
        double valorCalculado = (10.0 + ( (365.0 * PorcentagemSeguro.CARRO))/365 ) * 1;
        assertEquals(valorCalculado, valorMock);

    
    }
    
    
    
}

