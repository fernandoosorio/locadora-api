package com.locadora.locadoraapi.model;

public class Carro  extends Veiculo{

    public final int PASSEIO = 1;
	public final int SUV = 2;
    public final int PICKUP = 3;

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
