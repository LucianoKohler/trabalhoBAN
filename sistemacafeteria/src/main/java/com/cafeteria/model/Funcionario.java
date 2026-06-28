package com.cafeteria.model;

import java.sql.Date;

public class Funcionario {
    private int id;
    private String nome;
    private float salario;
    private Date data_contratacao;
    private String cargo;

    /* Construtor */
    public Funcionario(int id, String nome, float salario, Date data_contratacao, String cargo){
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.data_contratacao = data_contratacao;
        this.cargo = cargo;
    }

    /* Gets e Sets */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return salario;
    }
    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Date getData_contratacao() {
        return data_contratacao;
    }
    public void setData_contratacao(Date data_contratacao) {
        this.data_contratacao = data_contratacao;
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("ID: " + id + "\nNome: " + nome + 
        "\nSalário: R$" + salario + "\nData de Contratação: " + data_contratacao.toString() +
        "\n Cargo: " + cargo);
        
        return s.toString();
    }
}
