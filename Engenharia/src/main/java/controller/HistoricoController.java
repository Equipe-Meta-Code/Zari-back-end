package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.Conexao;
import dao.UsuarioDao;
import model.Historico;
import view.CadastrarView;
import view.TabelaHistorico;

public class HistoricoController {
	private LoginController loginController;
	TabelaHistorico tabelaHistorico = new TabelaHistorico();
	
	public void setTabelaHistorico(TabelaHistorico tabelaH) {
		tabelaH = tabelaHistorico;
	}
	String emailHistorico;
	String documento;
	
	public void setEmail(String e) {
		emailHistorico = e;
	}
	 
	public void setDocumento(String d) {
		documento = d;
	}
	
	public void salvarHistorico() {
		
		Historico novoHistorico = new Historico(emailHistorico, documento);
		
		try {
			Connection conexao = new Conexao().getConnection();
			UsuarioDao usuarioDao = new UsuarioDao(conexao);
			usuarioDao.insertHistorico(novoHistorico);		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void salvarNaTabela() {
		
		try {
		Connection conexao = new Conexao().getConnection();
		PreparedStatement stmt = conexao.prepareStatement("select * from historico");
		
		ResultSet rs = stmt.executeQuery();

		tabelaHistorico.dados = new ArrayList<>();
		while (rs.next()) {
		 String email = rs.getString("emailHistorico");
		 String documento = rs.getString("documento");

			if (email.equals(emailHistorico)) {
				tabelaHistorico.dados.add(new Historico(email,documento));
			}
		   
		}
		stmt.close();
		conexao.close();
		tabelaHistorico.gerarTabela();
		tabelaHistorico.setVisible(true);
		tabelaHistorico.setTabelaHistorico(tabelaHistorico);
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
				
	}

}