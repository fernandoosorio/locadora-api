package com.locadora.locadoraapi.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.locadora.locadoraapi.exception.VeiculoNaoCadastrado;
import com.locadora.locadoraapi.model.Carro;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.helpers.TipoCarroEnum;
import com.locadora.locadoraapi.service.ClienteService;
import com.locadora.locadoraapi.service.VeiculoService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest

public class LocadoraControllerTest {
    @Autowired
    private LocadoraImplController locadoraImplController;
    @MockBean
    private VeiculoService veiculoService;
    @MockBean
    private ClienteService clienteService;
    @MockBean
    private VeiculoNaoCadastrado veiculoNaoCadastrado;

    @BeforeEach
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(this.locadoraImplController, this.veiculoNaoCadastrado);
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
    public void deveRetornarException_quandoVeiculoNaoEncontrado() throws VeiculoNaoCadastrado{
        String placaInexistente = "PIA-9655";

        when(this.veiculoService.getVeiculoByPlaca(placaInexistente))
        .thenThrow(new VeiculoNaoCadastrado());

        this.veiculoService.getVeiculoByPlaca(placaInexistente);

    }
    
}
