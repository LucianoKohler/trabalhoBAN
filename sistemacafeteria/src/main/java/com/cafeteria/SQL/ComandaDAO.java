package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafeteria.dados.Comanda;

public class ComandaDAO {

    // Só precisa da mesa pois o resto é gerado na hora (data abertura, status)
    public static Boolean criaComanda(int numero_mesa){
        String sql = "INSERT INTO Comanda (numero_mesa, status_pgto) VALUES (?, ?)";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, numero_mesa);
            st.setString(2, "PENDENTE");
            st.executeUpdate();
            st.close();
            System.out.println("Comanda criada com sucesso!");
            return true;
        }catch(SQLException e){
            System.out.println("Erro ao criar comanda: " + e.getMessage());
            return false;
        }
    }

    public static Comanda procuraComandaPorID(int cID){
        String sql = "SELECT * FROM Comanda WHERE ID_comanda = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cID);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new Comanda(
                    res.getInt("ID_comanda"),
                    res.getInt("numero_mesa"), 
                    res.getTimestamp("data_hora_abertura").toLocalDateTime(),
                    res.getString("status_pgto"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Comanda> selectAll(){
        List<Comanda> comandas = new ArrayList<>();
        String sql = "SELECT * FROM Comanda";

        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet res = st.executeQuery();
            while(res.next()){
                Comanda c = new Comanda(
                    res.getInt("ID_comanda"),
                    res.getInt("numero_mesa"), 
                    res.getTimestamp("data_hora_abertura").toLocalDateTime(),
                    res.getString("status_pgto"));
                comandas.add(c);
            }
            return comandas;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Boolean deletaComandaPorID(int cID){
        String sql = "DELETE FROM Comanda WHERE ID_comanda = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cID);
            st.executeUpdate();
            st.close();
            System.out.println("Comanda deletada com sucesso!");

            return true;
        }catch(SQLException e){
            System.out.println("Erro ao deletar Comanda: " + e.getMessage());
            
            return false;
        }
    }

    public static Boolean alteraComanda(String campoAlterado, int comandaID, String novoAtributo){
        String sql = "UPDATE Produto SET " + campoAlterado + " = ? WHERE ID_produto = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            switch (campoAlterado) {
                case "status_pgto":
                    st.setString(1, novoAtributo);
                    break;
            
                case "numero_mesa":
                    st.setInt(1, Integer.parseInt(novoAtributo));
                    break;
                    
                default:
                    System.out.println("Escolha de campo inválida");
                    return false;
            }
            st.setInt(2, comandaID);
            st.executeUpdate();
            st.close();
            System.out.println("Comanda alterada com sucesso!");

            return true;
        }catch(SQLException e){
            System.out.println("Erro ao alterar Comanda: " + e.getMessage());

            return false;
        }
    }
}
