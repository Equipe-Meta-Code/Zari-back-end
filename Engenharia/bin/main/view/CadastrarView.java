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
import javax.swing.JPasswordField;
import controller.FormCadastroController;
import dao.Conexao;
import dao.UsuarioDao;
import model.Usuario;


public class CadastrarView extends JFrame implements ActionListener{
    
    JLabel title;
    JLabel jLabelname;
    JLabel jLabelsenha;
    JLabel jLabelId;
    JLabel jLabelEmail;
    JTextField textId;
    JTextField textNome;
    JPasswordField textSenha;
    JTextField textEmail;
    
    JCheckBox buttonShow;
    JButton voltar;
    JButton salvar;
	private final FormCadastroController controller;
    
  
    
    public CadastrarView() {
    	 
    	controller = new FormCadastroController(this);
        
		JFrame jFrame = new JFrame();
		setTitle("Cadastrar");
	
        setSize( 800,450);
        setDefaultCloseOperation(CadastrarView.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        title = new JLabel("Cadastrar");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(310, 5, 300, 100);
                
        add(title);
        
        jLabelId = new JLabel("ID:");
        jLabelId.setBounds(115, 75, 200, 50);
        jLabelId.setFont(new Font("Arial", Font.PLAIN, 14));
  
        
        textId = new JTextField();
        textId.setBounds(115, 115, 550, 40);
        textId.setFont(new Font("Arial", Font.ITALIC, 15));
                
     
        jLabelname = new JLabel("Nome completo:");
        jLabelname.setBounds(115, 75, 200, 50);
        jLabelname.setFont(new Font("Arial", Font.PLAIN, 14));
        
     
        add(jLabelname);
        
        textNome = new JTextField();
        textNome.setBounds(115, 115, 550, 40);
        textNome.setFont(new Font("Arial", Font.ITALIC, 15));
        
        add(textNome);
        
        jLabelEmail = new JLabel("Email:");
        jLabelEmail.setBounds(115, 155, 200, 50);
        jLabelEmail.setFont(new Font("Arial", Font.PLAIN, 14));  
     
        add(jLabelEmail);
        
        textEmail = new JTextField();
        textEmail.setBounds(115, 195, 550, 40);
        textEmail.setFont(new Font("Arial", Font.ITALIC, 15));
        
        add(textEmail);
      
        
        jLabelsenha = new JLabel("Senha:");
        jLabelsenha.setBounds(115, 235, 250, 50);
        jLabelsenha.setFont(new Font("Arial", Font.PLAIN, 14));
  
                 
        add(jLabelsenha);
        
        textSenha = new JPasswordField();
        textSenha.setBounds(115, 275, 550, 40);
        textSenha.setFont(new Font("Arial", Font.ITALIC, 15));
                
        add(textSenha);
        
        //botao para mostrar senha 
        buttonShow = new JCheckBox("Mostrar Senha");
        buttonShow.setBounds(540, 320, 250, 40);
        buttonShow.setFont(new Font("Arial", Font.ITALIC, 15));
        
        add(buttonShow);
        
        buttonShow.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(buttonShow.isSelected())
        			textSenha.setEchoChar((char)0);
        		else
        			textSenha.setEchoChar('*');
        	} 
        });
        voltar = new JButton("Voltar");
        voltar.setBounds(300, 335, 85, 30);
        add(voltar);
        
        voltar.addActionListener(this);
        
        salvar = new JButton("Salvar");
        salvar.setBounds(415, 335, 85, 30);
        add(salvar);
        
        salvar.addActionListener(this);
        
        
        
        setVisible(true);
		
}
   //Criei gets e sets do usuario e senha para poder acessar em outras classes 
	

	public JTextField getTextEmail() {
		return textEmail;
	}

	public void setTextEmail(JTextField textEmail) {
		this.textEmail = textEmail;
	}

	public JTextField getTextId() {
		return textId;
	}

	public void setTextId(JTextField textId) {
		this.textId = textId;
	}
	
	public JTextField getTextNome() {
		return textNome;
	}

	public void setTextUsuario(JTextField textUsuario) {
		this.textNome = textNome;
	}

	public JTextField getTextSenha() {
		return textSenha;
	}

	public void setTextSenha(JPasswordField textSenha) {
		this.textSenha = textSenha;
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == salvar){
			controller.salvaUsuario();
			
		}
		
		if (event.getSource() == voltar){
			this.dispose();
			new TelaInicial();
			
		}
		
	}

	}

	

 