package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafeteria.dados.Ingrediente;

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

    public static List<Ingrediente> buscaIngredientesDeUmProduto(int IDproduto){
            List<Ingrediente> ingredientes = new ArrayList<>();
            String sql = "SELECT * FROM Info_produto WHERE FK_produto = ?";

            try{
                Connection con = ConexaoDB.getInstancia();
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, IDproduto);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    Ingrediente i = IngredienteDAO.procuraIngredientePorID(rs.getInt("FK_ingrediente"));
                    ingredientes.add(i);
                }
                return ingredientes;
            }catch(SQLException e){
                System.out.println(e.getMessage());
                return null;
            }
    }
}
