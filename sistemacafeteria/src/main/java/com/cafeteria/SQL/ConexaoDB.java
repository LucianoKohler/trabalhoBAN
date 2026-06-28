package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static Connection conexao;
    
    private ConexaoDB() {}

    public static Connection getInstancia() {
<<<<<<< HEAD:sistemacafeteria/src/main/java/com/cafeteria/SQL/ConexaoDB.java
        try {
            if (conexao == null || conexao.isClosed()) {
=======
        if (conexao == null) {
            /* 
            try {
>>>>>>> 8ec63ae7255112a7488d74ec46c0f5e0a91a8395:sistemacafeteria/src/main/java/com/cafeteria/banco/ConexaoDB.java
                conexao = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5433/cafeteria",
                    "postgres", "1234"
                );
<<<<<<< HEAD:sistemacafeteria/src/main/java/com/cafeteria/SQL/ConexaoDB.java
=======
            } catch (SQLException e) {
                System.out.println("Erro ao conectar: " + e.getMessage());
            }*/
            try {
                conexao = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/cafeteria",
                    "luiza", "1234"
                );
            } catch (SQLException e) {
                System.out.println("Erro ao conectar: " + e.getMessage());
>>>>>>> 8ec63ae7255112a7488d74ec46c0f5e0a91a8395:sistemacafeteria/src/main/java/com/cafeteria/banco/ConexaoDB.java
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ou restabelecer conexão: " + e.getMessage());
        }
        return conexao;
    }
}