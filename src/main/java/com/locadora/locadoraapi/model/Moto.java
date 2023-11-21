package com.locadora.locadoraapi.model;

public class Moto extends Veiculo{

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
