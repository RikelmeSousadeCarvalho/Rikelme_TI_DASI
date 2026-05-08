package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExemplarDAO {
    public int avaiableBooks(int idLivro){
        //contando a quantidade de exemplares disponíveis de um livro
        String sql = "SELECT qntdDisponivel FROM EXEMPLAR WHERE fk_idLivro = ?";
        int total = 0;
        try (
                Connection conn = Conexao.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, idLivro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar exemplares pelo ID do livro: " + e.getMessage());
        }

        return total;
    }

    public void subtractExample(int idExemplar){
        String sql = "UPDATE Exemplar SET qntdDisponivel = qntdDisponivel - 1 WHERE idExemplar = ?";

        try(
                Connection conn = Conexao.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, idExemplar);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Foi subtraído um exemplar do livro emprestado!");
            }else{
                System.out.println("Erro ao subtrair livro");
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
