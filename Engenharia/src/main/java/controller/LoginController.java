package controller;


import java.sql.Connection;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.UsuarioDao;
import dao.databaseConnection;
import model.Usuario;
import view.LoginView;
import view.UploadArquivo;
import view.TabelaHistorico;
import view.UploadArquivo;


public class LoginController {

	private UploadArquivo uploadArquivo;
	
	private LoginView view;

	private HistoricoController historicoController = new HistoricoController();
    
        private TabelaHistorico tabelaHistorico;
    
    	public void setTabelaHistorico(TabelaHistorico tabelaHistorico) {
    	        this.tabelaHistorico = tabelaHistorico;
   	}
	
	public LoginController(LoginView view) {
		this.view = view;
	}
	

	public boolean autenticar() throws SQLException {
		
		//Buscar um usuario da view
		
		String email = view.getLoginEmail().getText();
		String senha = view.getLoginSenha().getText();
		
		Usuario usuarioAutenticar = new Usuario(email, senha);
		
		//Verificar se existe no banco de dados
		
        databaseConnection dbConnection = new databaseConnection();
        Connection conexao = dbConnection.getConnection();

        // Create an instance of UsuarioDao using the connection
        UsuarioDao usuarioDao = new UsuarioDao(conexao);

		
		boolean existe = usuarioDao.existeNoBancoPorUsuarioESenha(usuarioAutenticar);
		
		//Se existir direciona para o assistente
		
		if(existe) {
			
			//File template = new File(System.getProperty("user.dir")+"/src/main/java/_Engenharia/template.txt");
	    	
			
			uploadArquivo = new UploadArquivo();
          		uploadArquivo.setHistoricoController(historicoController);
          		historicoController.setEmail(email);
         		historicoController.setTabelaHistorico(tabelaHistorico);
            
           		historicoController.tabelaHistorico.setUploadArquivo(uploadArquivo);
		}else {
			JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
		}
		return existe;	
	}
	  
	
	
	

	
	
}
