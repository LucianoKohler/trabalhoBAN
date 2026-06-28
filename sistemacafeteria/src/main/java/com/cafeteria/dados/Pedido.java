package com.cafeteria.dados;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private LocalDateTime data_hora_pedido;
    private ArrayList<ItemPedido> produtos;
    private Funcionario atendente;
    private Comanda comanda;

    /* Construtor */
    public Pedido(int id, LocalDateTime data_hora_pedido, ArrayList<ItemPedido> produtos,
            Funcionario atendente, Comanda comanda) {
        this.id = id;
        this.data_hora_pedido = data_hora_pedido;
        this.produtos = produtos;
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

    public ArrayList<ItemPedido> getProdutos() {
        return produtos;
    }
    public void setProdutos(ArrayList<ItemPedido> produtos) {
        this.produtos = produtos;
    }

    public Funcionario getAtendente() {
        return atendente;
    }
    public void setAtendente(Funcionario atendente) {
        this.atendente = atendente;
    }

    public Comanda getComanda() {
        return comanda;
    }
    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("ID: " + id + "\nFeito em: " + data_hora_pedido.toString() +
        "\nProdutos: " + produtos + "\nServido por: " + atendente.getNome() + 
        "\nNúmero da comanda: " + comanda);
        
        return s.toString();
    }
}
