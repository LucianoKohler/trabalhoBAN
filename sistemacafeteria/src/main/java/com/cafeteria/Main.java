package com.cafeteria;

import com.cafeteria.banco.ConexaoDB;
import java.sql.Connection;

public class Main {
    public static void main(String args[]){
        Connection con = ConexaoDB.conectar();

        if(con != null){
            System.out.println("deu bom");
            ConexaoDB.desconectar(con);
        }
    }
}
