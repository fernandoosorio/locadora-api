package com.locadora.locadoraapi.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.exception.VeiculoNaoCadastrado;
import com.locadora.locadoraapi.model.Carro;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.DTO.VeiculoDto;
import com.locadora.locadoraapi.model.helpers.TipoCarroEnum;
import com.locadora.locadoraapi.service.ClienteService;
import com.locadora.locadoraapi.service.VeiculoService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class LocadoraControllerTest {
    @InjectMocks
    private LocadoraImplController locadoraImplController;
    
    @MockBean
    private VeiculoService veiculoService;
    @MockBean
    private ClienteService clienteService;
    @MockBean
    private VeiculoNaoCadastrado veiculoNaoCadastrado;
    @MockBean
    private VeiculoJaCadastrado veiculoJaCadastrado;

    @Autowired
    private ObjectMapper objectMapper;
  

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(this.locadoraImplController, this.veiculoNaoCadastrado);
    }

    @Test
    public void deveRetornarSucesso_QuandoInserirVeiculo() throws Exception {
            
       VeiculoDto veiculoDto = new VeiculoDto("Hyundai", "HB-20", 2023, 8000.0, 10, "PIA-9655", null,null,null, TipoCarroEnum.PASSEIO);

    //    mvc.perform(post("/locadora/create/veiculo")
    //     .contentType(MediaType.APPLICATION_JSON)
    //     .content(objectMapper.writeValueAsString(veiculoDto)))
    //     .andExpect(status().isOk());

         RestAssuredMockMvc.given()
            .contentType(ContentType.JSON )
            .body(objectMapper.writeValueAsString(veiculoDto))
            .when()
                .post("/locadora/create/veiculo")
            .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void deveRetornarException_quandoVeiculoJaCadastrado() throws Exception{
        VeiculoDto veiculoDto = new VeiculoDto("Hyundaix", "HB-20", 2023, 8000.0, 10, "PIA-9655", null,null,null, TipoCarroEnum.PASSEIO);

        when(this.veiculoService.inserir(any(Veiculo.class)))
        .thenThrow(new VeiculoJaCadastrado());

        assertThrows(VeiculoJaCadastrado.class, () -> {
            RestAssuredMockMvc.given()
                .contentType(ContentType.JSON )
                .body(objectMapper.writeValueAsString(veiculoDto))
            .when()
                .post("/locadora/create/veiculo");
        });
        


    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarVeiculoByPlaca(){

        when(this.veiculoService.getVeiculoByPlaca("PIA-9655"))
        .thenReturn( 
            new Carro("Hyundai", "HB-20", 2023, 2.0, 10, "PIA-9655", TipoCarroEnum.PASSEIO) 
            );

        RestAssuredMockMvc.given()
            .accept(ContentType.JSON            )
            .when()
                .get("/locadora/pesquisar/veiculoByPlaca/{placa}", "PIA-9655")
            .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void deveRetornarException_quandoVeiculoNaoEncontrado() throws Exception{
        String placaInexistente = "2";

        mvc.perform(get("/locadora/pesquisar/veiculoByPlaca/{placa}", placaInexistente) 
        .contentType(MediaType.APPLICATION_JSON) )
        .andExpect( status().isNotFound())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof VeiculoNaoCadastrado))
        .andExpect(result -> assertEquals("Veículo não cadastrado", result.getResolvedException().getMessage() ))
         ;

    }
    
}
