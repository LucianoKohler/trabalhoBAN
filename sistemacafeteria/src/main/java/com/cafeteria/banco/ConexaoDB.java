package com.cafeteria.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static Connection conexao;
    private ConexaoDB() {}

    public static Connection getInstancia() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5433/cafeteria",
                    "postgres", "1234"
                );
            } catch (SQLException e) {
                System.out.println("Erro ao conectar: " + e.getMessage());
            }
        }
        return conexao;
    }

}
