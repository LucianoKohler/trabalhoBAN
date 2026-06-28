package com.cafeteria.model;

import java.time.LocalDateTime;

public class Comanda {
    private int id;
    private int numero_mesa;
    private LocalDateTime data_abertura;
    private String status_pgto;

    /* Construtor */
    public Comanda(int id, int numero_mesa, LocalDateTime data_abertura, String status_pgto) {
        this.id = id;
        this.numero_mesa = numero_mesa;
        this.data_abertura = data_abertura;
        this.status_pgto = status_pgto;
    }

    /* Gets e Sets */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero_mesa() {
        return numero_mesa;
    }
    public void setNumero_mesa(int numero_mesa) {
        this.numero_mesa = numero_mesa;
    }

    public LocalDateTime getdata_abertura() {
        return data_abertura;
    }
    public void setdata_abertura(LocalDateTime data_abertura) {
        this.data_abertura = data_abertura;
    }

    public String getStatus_pgto() {
        return status_pgto;
    }
    public void setStatus_pgto(String status_pgto) {
        this.status_pgto = status_pgto;
    }

    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("ID: " + id + "\nN° mesa: " + numero_mesa +
        "\nData de Abertura: " + data_abertura.toString() + "\nStatus: " + status_pgto);
        
        return s.toString();
    }
}
