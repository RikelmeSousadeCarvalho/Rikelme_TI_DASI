package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public void insert(Usuario usuario){
        String sql = "INSERT INTO Usuario (nome, email, telefone, clb, idade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Substituímos os "?" pelos valores do objeto
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getClb());
            stmt.setInt(5, usuario.getIdade());

            stmt.executeUpdate();
            System.out.println("Usuário salvo com sucesso: " + usuario.getNome());

        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public void selectAll() {
        String sql = "SELECT * FROM Usuario";

        try (Connection conn = Conexao.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Lista de Usuários ---");
            while (rs.next()) {
                int id = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                String clb = rs.getString("clb");
                int idade = rs.getInt("idade");
                System.out.println("ID: " + id + " | Nome: " + nome + " | Email: " + email + " | Telefone: " + telefone + " | CLB: " + clb + " | Idade: " + idade);
            }
            System.out.println("-------------------------\n");

        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    public void update(Usuario usuario, int idUsuario){
        String sql = "UPDATE Usuario SET nome = ?, email = ?, telefone = ?, clb = ?, idade = ? WHERE idUsuario = ?";

        try (Connection conn = Conexao.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getClb());
            stmt.setInt(5, usuario.getIdade());
            stmt.setInt(6, idUsuario);

            int rowsAffected = stmt.executeUpdate();


            if (rowsAffected > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    public void delete(int idUsuario){
        String sql = "DELETE FROM Usuario WHERE idUsuario=?";

        try (Connection conn = Conexao.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário deletado com sucesso!");
            } else {
                System.out.println("Nenhum usuário com esse ID foi encontrado para deletar.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
    }
}
