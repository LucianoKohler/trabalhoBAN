package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cafeteria.dados.Ingrediente;

public class IngredienteDAO {

    public static Boolean insereIngrediente(Ingrediente ing){
        String sql = "INSERT INTO Ingrediente (nome, descricao, quantidade, unidade_medida) VALUES (?, ?, ?, ?)";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, ing.getNome());
            st.setString(2, ing.getDescricao());
            st.setFloat(3, ing.getQuantidade());
            st.setString(4, ing.getUnidade_medida());
            st.executeUpdate();
            st.close();
            System.out.println("Ingrediente criado com sucesso!");
            return true;
        }catch(SQLException e){
            System.out.println("Erro ao criar post: " + e.getMessage());
            return false;
        }
    }

    public static Ingrediente procuraIngredientePorID(int ingID){
        String sql = "SELECT * FROM Ingrediente WHERE ID_ingrediente = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, ingID);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new Ingrediente(
                    res.getInt("ID_ingrediente"),
                    res.getString("nome"), 
                    res.getString("descricao"), 
                    res.getFloat("quantidade"),
                    res.getString("unidade_medida"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    // Só retorna o primeiro ingrediente
    public static Ingrediente procuraIngredientePorNome(String nome){
        String sql = "SELECT * FROM Ingrediente WHERE nome = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new Ingrediente(
                    res.getInt("ID_ingrediente"),
                    res.getString("nome"), 
                    res.getString("descricao"), 
                    res.getFloat("quantidade"),
                    res.getString("unidade_medida"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Boolean deletaIngredientePorID(int ingId){
        String sql = "DELETE FROM Ingredientes WHERE ID_ingrediente = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, ingId);
            st.executeUpdate();
            st.close();
            System.out.println("Ingrediente deletado com sucesso!");

            return true;
        }catch(SQLException e){
            System.out.println("Erro ao deletar ingrediente: " + e.getMessage());
            
            return false;
        }
    }
}
