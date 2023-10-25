package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import controller.LoginController;
import model.Historico;
import model.Usuario;
import view.TelaInicial;
import view.UploadArquivo;

public class UsuarioDao {

	private final Connection connection;

	public UsuarioDao(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public void insert(Usuario usuario) throws SQLException {
		
		String sql = "insert into usuario(email, nome, senha) values(?,?,?);";
				
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getEmail());
		statement.setString(2, usuario.getNome());
		statement.setString(3, usuario.getSenha());
		statement.execute();		
	}
	
	public boolean existeNoBancoPorUsuarioESenha(Usuario usuario) throws SQLException {
		
		String sql = "select * from usuario where email = ? and senha = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, usuario.getEmail());
		statement.setString(2, usuario.getSenha());
		
		statement.execute();
		
		ResultSet resultSet = statement.getResultSet();
			
		return resultSet.next();	
	}
	
	public void insertHistorico(Historico historico) throws SQLException {

            String sql = "insert into historico(emailHistorico, documento) values(?,?);";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, historico.getEmailHistorico());
            statement.setString(2, historico.getDocumento());
            statement.execute();
        }

	
}
