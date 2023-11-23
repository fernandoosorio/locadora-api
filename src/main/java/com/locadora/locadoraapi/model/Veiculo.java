package com.locadora.locadoraapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Veiculo implements VeiculoInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    @Column   
    protected String marca;
    @Column   
	protected String modelo;
    @Column
	protected int anoDeFabricacao;
    @Column
	protected double valorDoBem;
    @Column
	protected double valorDiaria;
    @Column
	protected String placa;

    public Veiculo(String marca, String modelo, int anoDeFabricacao, double valorDoBem, double valorDiaria, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.anoDeFabricacao = anoDeFabricacao;
        this.valorDoBem = valorDoBem;
        this.valorDiaria = valorDiaria;
        this.placa = placa;
    }

    public Veiculo() {
    }

	
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public double seguro() {
       return 0;
    }
   
    
}
