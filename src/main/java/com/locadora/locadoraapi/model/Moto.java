package com.locadora.locadoraapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Moto extends Veiculo{
    @Column   
    private int cilindrada;

    @Override
    public double seguro() {
        return (getValorDoBem()*0.11)/365;
    }

    public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}
    
}
