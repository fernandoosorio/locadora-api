package com.locadora.locadoraapi.model;

public abstract class Veiculo {

    protected String marca;
	protected String modelo;
	protected int anoDeFabricacao;
	protected double valorDoBem;
	protected double valorDiaria;
	protected String placa;

    public abstract double seguro();
	
	public double aluguel(int dias) {
		return (this.valorDiaria + this.seguro()) * dias;
	}

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoDeFabricacao() {
        return this.anoDeFabricacao;
    }

    public void setAnoDeFabricacao(int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public double getValorDoBem() {
        return this.valorDoBem;
    }

    public void setValorDoBem(double valorDoBem) {
        this.valorDoBem = valorDoBem;
    }

    public double getValorDiaria() {
        return this.valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
   
    
}
