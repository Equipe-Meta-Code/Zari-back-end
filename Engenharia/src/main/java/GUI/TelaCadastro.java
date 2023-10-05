package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaCadastro extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel title;
    JLabel jLabel;
    JLabel jLabel2;
    JLabel jLabel3;
    JLabel jLabel4;
    
    JTextField caixa1;
    JTextField caixa2;
    JTextField caixa3;
    JPasswordField caixa4;
    
    JCheckBox buttonShow;
    
    JButton cadastrar;
    
    
    public TelaCadastro() {
        
		setTitle("Tela de Cadastro");
	
	
        setBackground(Color.yellow);
        setSize( 750,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        
        //TITULO CADASTRO
        title = new JLabel("Cadastro");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(310, 20, 300, 100);
                
        add(title);
        
        
        //NOME - 1
        jLabel = new JLabel("Nome:");
        jLabel.setBounds(200, 123, 200, 50);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 14));
          
        add(jLabel);
        
        caixa1 = new JTextField();
        caixa1.setBounds(250, 130, 250, 40);
        caixa1.setFont(new Font("Arial", Font.ITALIC, 15));
                
        add(caixa1); //fim nome
        
        
        //CARGO - 2
        jLabel2 = new JLabel("Cargo:");
        jLabel2.setBounds(195, 173, 250, 50);
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
                 
        add(jLabel2);
        
        caixa2 = new JTextField();
        caixa2.setBounds(250, 180, 250, 40);
        caixa2.setFont(new Font("Arial", Font.ITALIC, 15));
                
        add(caixa2); //fim cargo
        
        
        //USUARIO - 3
        jLabel3 = new JLabel("Usuário:");
        jLabel3.setBounds(185, 223, 250, 50);
        jLabel3.setFont(new Font("Arial", Font.PLAIN, 14));
                 
        add(jLabel3);
        
        caixa3 = new JTextField();
        caixa3.setBounds(250, 230, 250, 40);
        caixa3.setFont(new Font("Arial", Font.ITALIC, 15));
                
        add(caixa3); //fim usuario
        
        
        //SENHA - 4
        jLabel4 = new JLabel("Senha:");
        jLabel4.setBounds(190, 273, 250, 50);
        jLabel4.setFont(new Font("Arial", Font.PLAIN, 14));
                 
        add(jLabel4);
        
        //botao para mostrar senha 
        buttonShow = new JCheckBox("Mostrar Senha");
        buttonShow.setBounds(500, 280, 250, 40);
        buttonShow.setFont(new Font("Arial", Font.ITALIC, 15));
        
        add(buttonShow);
        
        buttonShow.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(buttonShow.isSelected())
        			caixa4.setEchoChar((char)0);
        		else
        			caixa4.setEchoChar('*');
        	} 
        });
        
        caixa4 = new JPasswordField();
        caixa4.setBounds(250, 280, 250, 40);
        caixa4.setFont(new Font("Arial", Font.ITALIC, 15));
        
                
        add(caixa4); //fim senha
        
        
        //botao cadastrar
        cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(325, 350, 100, 30);
        add(cadastrar);
        
        cadastrar.addActionListener(this); //fim botao
        
        
        setVisible(true);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



}
