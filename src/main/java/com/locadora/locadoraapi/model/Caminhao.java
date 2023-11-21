package com.locadora.locadoraapi.model;

public class Caminhao extends Veiculo{

    private int carga;


    @Override
    public double seguro() {
        return (getValorDoBem()*0.08)/365;
    }

    public int getCarga() {
        return this.carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

}
