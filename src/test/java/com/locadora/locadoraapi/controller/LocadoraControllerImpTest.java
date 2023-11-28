package com.locadora.locadoraapi.controller;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locadora.locadoraapi.exception.ClienteJaCadastrado;
import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.model.Carro;
import com.locadora.locadoraapi.model.Cliente;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.DTO.VeiculoDto;
import com.locadora.locadoraapi.model.helpers.TipoCarroEnum;
import com.locadora.locadoraapi.service.ClienteService;
import com.locadora.locadoraapi.service.VeiculoService;

/*
 * Testes para a camada Web
 */
@WebMvcTest(controllers = LocadoraImplController.class)
public class LocadoraControllerImpTest {
   
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VeiculoService veiculoService;
    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;
  

    @Test
    void deveRetornarSucesso_QuandoInserirVeiculo() throws Exception {
        VeiculoDto veiculoDto = new VeiculoDto("Hyundai", "HB-20", 2023, 8000.0, 10, "PIA-9655", null,null,null, TipoCarroEnum.PASSEIO);
        
        when(veiculoService.inserir(any(Veiculo.class)))
        .thenReturn(new 
                    Carro("Hyundai", "HB-20", 2023, 8000.0, 10, "PIA-9655", TipoCarroEnum.PASSEIO)
                    );
        
        this.mockMvc.
                perform(post("/locadora/create/veiculo")
                .contentType(MediaType.APPLICATION_JSON) 
                .content(objectMapper.writeValueAsString(veiculoDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hyundai")))
				;
    }

    @Test
    void deveRetornarException_QuandoInserirVeiculoJaCadastrado() throws Exception {
        VeiculoDto veiculoDto = new VeiculoDto("Hyundai", "HB-20", 2023, 8000.0, 10, "PIA-9655", null,null,null, TipoCarroEnum.PASSEIO);
        
        when(veiculoService.inserir(any(Veiculo.class)))
        .thenThrow( VeiculoJaCadastrado.class);
                   
        
        this.mockMvc.
                perform(post("/locadora/create/veiculo")
                .contentType(MediaType.APPLICATION_JSON) 
                .content(objectMapper.writeValueAsString(veiculoDto)))
                .andExpect(status().isConflict())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof VeiculoJaCadastrado));
    }

    @Test
    void deveRetornarSucesso_QuandoInserirCliente() throws Exception {
        Cliente dto = new Cliente("Maria", 99999999999l, null,null);
        
        when(clienteService.inserir(any(Cliente.class)))
        .thenReturn(new Cliente("Maria", 99999999999l, null,null) );
        
        this.mockMvc.
                perform(post("/locadora/create/cliente")
                .contentType(MediaType.APPLICATION_JSON) 
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Maria")))
				;
    }

    @Test
    void deveRetornarException_QuandoInserirClienteCadastrado() throws Exception {
          Cliente dto = new Cliente("Maria", 99999999999l, null,null);
        
        when(clienteService.inserir(any(Cliente.class)))
        .thenThrow( ClienteJaCadastrado.class);
                   
        
        this.mockMvc.
                perform(post("/locadora/create/cliente")
                .contentType(MediaType.APPLICATION_JSON) 
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ClienteJaCadastrado));
    }

    @Test
    void deveRetornarSucesso_QuandoCalcularAluguel() throws Exception {
               
        when(veiculoService.getValorTotalAluguel(any(String.class), any(Integer.class)))
        .thenReturn(1000.0 );
        
        this.mockMvc.
                perform(get("/locadora/calcular-aluguel/{placa}/{dias}", "PIA-9655", 10)
                .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1000.0")))
				;
    }
    
    
}

