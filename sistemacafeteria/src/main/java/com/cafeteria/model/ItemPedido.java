package com.cafeteria.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ItemPedido {
    private int id;
    private Pedido pedido;
    private Produto produto;
    private int quantidade;
    private float preco_unitario;
    private String observacao;

    /* Construtor */
    public ItemPedido(int id, Pedido pedido, Produto produto, int quantidade, float preco_unitario, String observacao) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
        this.observacao = observacao;
    }

    /* Gets e Sets */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco_unitario() {
        return preco_unitario;
    }
    public void setPreco_unitario(float preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }


    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("ID: " + id + "\nProduto: " + produto.getNome() +
        "\nQuantidade: " + quantidade + "\n Do pedido: " + pedido + 
        "\nPreço unitário: " + preco_unitario + "\nObservações: " + observacao);
        
        return s.toString();
    }
}
