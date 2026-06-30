package com.cafeteria.dados;

import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private LocalDateTime data_hora_pedido;
    private String status_pedido;
    private int atendente;
    private int comanda;

    /* Construtor */
    public Pedido(int id, LocalDateTime data_hora_pedido, String status_pedido, int atendente, int comanda) {
        this.id = id;
        this.data_hora_pedido = data_hora_pedido;
        this.status_pedido = status_pedido;
        this.atendente = atendente;
        this.comanda = comanda;
    }

    /* Gets e Sets */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData_hora_pedido() {
        return data_hora_pedido;
    }
    public void setData_hora_pedido(LocalDateTime data_hora_pedido) {
        this.data_hora_pedido = data_hora_pedido;
    }

    public String getStatus_pedido() {
        return status_pedido;
    }
    public void setStatus_pedido(String status_pedido) {
        this.status_pedido = status_pedido;
    }

    public int getAtendente() {
        return atendente;
    }
    public void setAtendente(int atendente) {
        this.atendente = atendente;
    }

    public int getComanda() {
        return comanda;
    }
    public void setComanda(int comanda) {
        this.comanda = comanda;
    }

    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("ID: " + id + "\nFeito em: " + data_hora_pedido.toString() +
        "\n" + (atendente == 0 ? "Ainda não atendido" : "Atendido pelo funcionario " + atendente) + 
        "\nNúmero da comanda: " + comanda);
        
        return s.toString();
    }
}
