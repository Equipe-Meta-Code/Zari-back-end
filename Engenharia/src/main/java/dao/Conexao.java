package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	//conecta com o banco
	
	public Connection getConnection() throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/banco_zari","root","fatec");
		return conexao;
	}

}
