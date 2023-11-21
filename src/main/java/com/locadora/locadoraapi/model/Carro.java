package com.locadora.locadoraapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Carro  extends Veiculo{

    @Transient public static final int PASSEIO = 1;
	@Transient public static final int SUV = 2;
    @Transient public static final int PICKUP = 3;
    
    @Column
    private int tipo;

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

    @Override
    public double seguro() {
        return (getValorDoBem()*0.03)/365;
    }
    
}
