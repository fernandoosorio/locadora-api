package com.locadora.locadoraapi.model;

import com.locadora.locadoraapi.model.helpers.TipoCarroEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

@Entity
public class Carro  extends Veiculo { 
    
    @Enumerated
    private TipoCarroEnum tipo;


    public Carro(String marca, String modelo, int anoDeFabricacao, double valorDoBem, double valorDiaria, String placa, TipoCarroEnum tipo) {
        super(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa);
        this.tipo = tipo;
    }

    public  Carro() {
    }

	public TipoCarroEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoCarroEnum tipo) {
		this.tipo = tipo;
	}

    @Override
    public double seguro() {
        return (getValorDoBem()*0.03)/365;
    }
    
}
