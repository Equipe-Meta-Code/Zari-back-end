package controller;

import java.sql.Connection;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.UsuarioDao;
import dao.databaseConnection;
import model.Usuario;
import view.CadastrarView;

public class FormCadastroController {

	private CadastrarView view;

	public FormCadastroController(CadastrarView view) {
		
		this.view = view;
	}


	public void salvaUsuario() {
		
			
			String nome = view.getTextNome().getText();
			String email = view.getTextEmail().getText();
			String senha = view.getTextSenha().getText();
			
			Usuario usuarioNovo = new Usuario(email, nome, senha);
			
			try {
				Connection conexao = new databaseConnection().getConnection();
				UsuarioDao usuarioDao = new UsuarioDao(conexao);
				usuarioDao.insert(usuarioNovo);
				
				JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	} 
	
}
