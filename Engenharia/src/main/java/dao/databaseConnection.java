package dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class databaseConnection {
    private Connection connection;
 
    public databaseConnection() {
        try {
            // Carregue o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
 
            // Estabeleça a conexão com o banco de dados
            connection = DriverManager.getConnection("jdbc:mysql://banco-metacode.mysql.database.azure.com:3306/banco_zari?useSSL=true", "admMC", "MetaCode411#");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
 
    public Connection getConnection() {
        return connection;
    }
}