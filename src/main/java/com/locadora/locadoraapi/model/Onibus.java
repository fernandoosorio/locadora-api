package com.locadora.locadoraapi.model;

public class Onibus extends Veiculo{

    private int capacidadePassageiros;

    @Override
    public double seguro() {
        return (getValorDoBem()*0.2)/365;
    }

    public int getCapacidadePassageiros() {
        return this.capacidadePassageiros;
    }

    public void setCapacidadePassageiros(int capacidadePassageiros) {
        this.capacidadePassageiros = capacidadePassageiros;
    }
    
    
}
