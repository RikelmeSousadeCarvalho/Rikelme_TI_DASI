package org.example;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection createConnection() throws SQLException {
        Dotenv dotenv = Dotenv.load();

        String url = "jdbc:mysql://localhost:3306/dblib";
        String user = "root";
        String password = dotenv.get("DB_PASSWORD");

        Connection con = null;
        con = DriverManager.getConnection(url, user, password);

        return con;
    }
}