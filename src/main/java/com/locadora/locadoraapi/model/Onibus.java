package com.locadora.locadoraapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Onibus extends Veiculo {
    @Column   
    private int capacidadePassageiros;

    public Onibus(String marca, String modelo, int anoDeFabricacao, double valorDoBem, double valorDiaria, String placa, int capacidadePassageiros) {
        super(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa);
        this.capacidadePassageiros = capacidadePassageiros;
    }

    public Onibus() {
    }

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
