package controller;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.Conexao;
import dao.UsuarioDao;
import model.Usuario;
import view.LoginView;
import view.UploadArquivo;



public class LoginController {

	private LoginView view;

	public LoginController(LoginView view) {
		this.view = view;
	}
	

	public boolean autenticar() throws SQLException {
		
		//Buscar um usuario da view
		
		String email = view.getLoginEmail().getText();
		String senha = view.getLoginSenha().getText();
		
		Usuario usuarioAutenticar = new Usuario(email, senha);
		
		//Verificar se existe no banco de dados
		
		Connection conexao = new Conexao().getConnection();
		UsuarioDao usuarioDao = new UsuarioDao(conexao);
		
		boolean existe = usuarioDao.existeNoBancoPorUsuarioESenha(usuarioAutenticar);
		
		//Se existir direciona para o assistente
		
		if(existe) {
			
			File template = new File(System.getProperty("user.dir")+"/src/main/java/_Engenharia/template.txt");
	    	try {
				template.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Não Foi Possível Criar o Arquivo base", "Error", 2);
				System.exit(-1);
			}
			
			new UploadArquivo();
		}else {
			JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
		}
		return existe;
			
		
	}
	  
	
	
	

	
	
}
