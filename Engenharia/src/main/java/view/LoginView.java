package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import controller.LoginController;
import dao.Conexao;
import dao.UsuarioDao;
import model.Usuario;


public class LoginView extends JFrame implements ActionListener{
    
    JLabel title;
    JLabel jLabelEmail;
    JLabel jLabelSenha;
    JTextField loginEmail;
    JPasswordField loginSenha;
    
    JButton cadastrar;
    JButton entrar;
    JCheckBox buttonShow;
    
	private LoginController controller;
    
  
    
    public LoginView() {
    	
    	controller = new LoginController(this);
        
		JFrame jFrame = new JFrame();
		setTitle("Login");
	
        setSize( 800,450);
        setDefaultCloseOperation(LoginView.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(235, 5, 300, 100);
                
        add(title);
        
        jLabelEmail = new JLabel("Email:");
        jLabelEmail.setBounds(115, 75, 200, 50);
        jLabelEmail.setFont(new Font("Arial", Font.PLAIN, 14));
  
                 
        add(jLabelEmail);
        
        loginEmail = new JTextField();
        loginEmail.setBounds(115, 115, 550, 40);
        loginEmail.setFont(new Font("Arial", Font.ITALIC, 15));
        
        add(loginEmail);
        
        
        jLabelSenha = new JLabel("Senha:");
        jLabelSenha.setBounds(115, 155, 250, 50);
        jLabelSenha.setFont(new Font("Arial", Font.PLAIN, 14));
  
                 
        add(jLabelSenha);
        
        loginSenha = new JPasswordField();
        loginSenha.setBounds(115, 195, 550, 40);
        loginSenha.setFont(new Font("Arial", Font.ITALIC, 15));
                
        add(loginSenha);
        
        //botao para mostrar senha 
        buttonShow = new JCheckBox("Mostrar Senha");
        buttonShow.setBounds(500, 280, 250, 40);
        buttonShow.setFont(new Font("Arial", Font.ITALIC, 15));
        
        add(buttonShow);
        
        buttonShow.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(buttonShow.isSelected())
        			loginSenha.setEchoChar((char)0);
        		else
        			loginSenha.setEchoChar('*');
        	} 
        });
        
        entrar = new JButton("Entrar");
        entrar.setBounds(325, 255, 85, 30);
        add(entrar);
        
        entrar.addActionListener(this);
     
        
        cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(325, 295, 95, 30);
        add(cadastrar);
        
        cadastrar.addActionListener(this);
        
        
        
        setVisible(true);
		
}
   
	public JTextField getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(JTextField loginEmail) {
		this.loginEmail = loginEmail;
	}

	public JTextField getLoginSenha() {
		return loginSenha;
	}

	public void setLoginSenha(JPasswordField loginSenha) {
		this.loginSenha = loginSenha;
	}

	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == entrar){
			try {
				controller.autenticar();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (event.getSource() == cadastrar){
			new CadastrarView();
			
		}
		
		
	}

}