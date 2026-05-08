package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmprestimoDAO {
    ExemplarDAO exemplarDao = new ExemplarDAO();
    public void makeLoan(int idUsuario, int idLivro, int idExemplar){

        String sql = "INSERT INTO Emprestimo (dataEmprestimo, dataPrevistaDevolucao, dataDevolucao, statusDevolucao, fk_idUsuario, fk_idExemplar) VALUES (?, ?, ?, ?, ?, ?)";

        int totalEmprestimos = getLoansById(idUsuario);
        int exemplaresDisponiveis = exemplarDao.avaiableBooks(idLivro);
        if(totalEmprestimos <= 3){
            if(exemplaresDisponiveis > 0) {
                try (
                        Connection conn = Conexao.createConnection();  PreparedStatement stmt = conn.prepareStatement(sql)
                ) {
                    stmt.setObject(1, LocalDate.now());
                    stmt.setObject(2, LocalDate.now().plusDays(10)); //devolução teóricamente deve ser feita em 10 dias - regra de negócio
                    stmt.setObject(3, null);
                    stmt.setBoolean(4, false);
                    stmt.setInt(5, idUsuario);
                    stmt.setInt(6, idExemplar);

                    stmt.executeUpdate();
                    exemplarDao.subtractExample(idExemplar);
                    System.out.println("Empréstimo realizado com sucesso: ");

                } catch (SQLException e) {
                    System.err.println("Erro ao realizar empréstimo: " + e.getMessage());
                }
            }else{
                System.out.println("Não há exemplares disponíveis para esse livro!");
            }
        }else {
            System.out.println("O usuário não pode mais realizar empréstimos por ter mais de 3 livros com estado de devolução pendente!");
        }
    }

    public int getLoansById(int idUsuario) {
        //contando a quantidade de livros que foram emprestados e ainda não foram devolvidos
        String sql = "SELECT COUNT(fk_idUsuario) FROM Emprestimo WHERE statusDevolucao = 0 AND fk_idUsuario = ?";
        int total = 0;
        try (
                Connection conn = Conexao.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar empréstimos pelo ID: " + e.getMessage());
        }

        return total;
    }

    public void selectAll() {
        String sql = "SELECT * FROM Emprestimo";

        try (Connection conn = Conexao.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Lista de Emprestimos ---");
            while (rs.next()) {
                int idEmprestimo = rs.getInt("idEmprestimo");
                LocalDate dataEmprestimo = rs.getObject("dataEmprestimo", LocalDate.class);
                LocalDate dataPrevistaDevolucao = rs.getObject("dataPrevistaDevolucao", LocalDate.class);
                LocalDate dataDevolucao = rs.getObject("dataDevolucao", LocalDate.class);
                boolean statusDevolucao = rs.getBoolean("statusDevolucao");
                int idUsuario = rs.getInt("fk_idUsuario");
                int idExemplar = rs.getInt("fk_idExemplar");
                System.out.println("ID: " + idEmprestimo + " | Data Empréstimo: " + dataEmprestimo + " | Data Prevista Devolução: " + dataPrevistaDevolucao +
                        " | Data Devolução: " + dataDevolucao + " | Status Devolução: " + statusDevolucao + " | ID User: " + idUsuario + " | ID Exemplar: " + idExemplar);
            }
            System.out.println("-------------------------\n");

        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
        }
    }

    public void returnBook(int idEmprestimo){
        //mudar o status da devolução
        //registrar a dataReal de devolução
        String sql = "UPDATE Emprestimo SET dataDevolucao = ?, statusDevolucao = 1 WHERE idEmprestimo = ?";

        try(
                Connection conn = Conexao.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setObject(1, LocalDate.now());
            stmt.setInt(2, idEmprestimo);

            int rowsAffected = stmt.executeUpdate();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = LocalDate.now().format(fmt);
            if(rowsAffected > 0){
                System.out.println("O livro foi devolvido com sucesso na data " + dataFormatada);
            }else{
                System.out.println("Ocorreu um problema ao devolver o livro!");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}


