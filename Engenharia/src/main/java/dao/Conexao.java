package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	//conecta com o banco
	
	public Connection getConnection() throws SQLException {
		String url="jdbc:mysql://banco-metacode.mysql.database.azure.com:3306/banco_zari?useSSL=true";
		Connection myDbConn = DriverManager.getConnection(url, "admMC", "MetaCode411#");
		return myDbConn;
	}
}