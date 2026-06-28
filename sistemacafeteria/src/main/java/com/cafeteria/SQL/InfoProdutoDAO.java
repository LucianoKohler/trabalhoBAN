package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InfoProdutoDAO {

    public static Boolean criaInfoProduto(float quantidade, String obs, int ingID, int prodID){
        String sql = "INSERT INTO Info_produto (quantidade, observacao, FK_ingrediente, FK_produto) VALUES (?, ?, ?, ?)";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setFloat(1, quantidade);
            st.setString(2, obs);
            st.setInt(3, ingID);
            st.setInt(4, prodID);
            st.executeUpdate();
            st.close();
            System.out.println("Relação criada com sucesso!");
            return true;
        }catch(SQLException e){
            System.out.println("Erro ao criar relação: " + e.getMessage());
            return false;
        }
    }
}
