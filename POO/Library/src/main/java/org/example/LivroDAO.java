package org.example;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LivroDAO {

    public void insert(Livro livro) {
        String sql = "INSERT INTO Livro (autor, titulo, editora, dataPublicacao) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getAutor());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getEditora());
            stmt.setObject(4, livro.getDataPublicacao());

            stmt.executeUpdate();
            System.out.println("Livro salvo com sucesso: " + livro.getTitulo());

        } catch (SQLException e) {
            System.err.println("Erro ao salvar livro: " + e.getMessage());
        }
    }

    public void selectAll() {
        String sql = "SELECT * FROM Livro";

        try (Connection conn = Conexao.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Lista de Livros ---");
            while (rs.next()) {
                int idLivro = rs.getInt("idLivro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String editora = rs.getString("editora");
                LocalDate dataPublicacao = rs.getObject("dataPublicacao", LocalDate.class);
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = dataPublicacao.format(fmt);
                System.out.println("ID: " + idLivro + " | Titulo: " + titulo + " | Autor: " + autor +
                        " | Editora: " + editora + " | Data: " + dataFormatada);
            }
            System.out.println("-------------------------\n");

        } catch (SQLException e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
        }
    }

    public void update(Livro livro, int idLivro) {
        String sql = "UPDATE Livro SET autor = ?, titulo = ?, editora = ?, dataPublicacao = ? WHERE idLivro = ?";

        try (Connection conn = Conexao.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getAutor());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getEditora());
            stmt.setObject(4, livro.getDataPublicacao());
            stmt.setInt(5, idLivro);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum livro encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    public void delete(int idLivro) {
        String sql = "DELETE FROM Livro WHERE idLivro=?";
        try (Connection conn = Conexao.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro deletado com sucesso!");
            } else {
                System.out.println("Nenhum livro com esse ID encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar livro: " + e.getMessage());
        }
    }
}