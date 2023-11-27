package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TelaInicial extends JFrame implements ActionListener {
	
	JLabel title;
	JLabel textoEntrar;
	JLabel textoCadastrar;
	RoundButton buttonEntrar;
	RoundButton buttonCadastrar;
      
    public TelaInicial() {
    	
    	setTitle("Início");
        setBackground(Color.yellow);
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        
        ImageIcon icon = new ImageIcon(getClass().getResource("zaribot.png"));
        setIconImage(icon.getImage());
        Image resizedIcon = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon finalIcon = new ImageIcon(resizedIcon);
        setIconImage(finalIcon.getImage());
        
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("zaribot.png"));
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(140, 11, 260, 180); // Ajuste a posição e o tamanho conforme necessário
        getContentPane().add(imageLabel);
        
        title = new JLabel("Assistente Virtual Zari");
        title.setFont(new Font("Yu Gothic", Font.BOLD, 30));
        title.setBounds(103, 161, 328, 100);
        getContentPane().add(title);
        
        textoEntrar = new JLabel("Já possui uma conta?");
        textoEntrar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
        textoEntrar.setBounds(65, 272, 200, 20);
        getContentPane().add(textoEntrar);
        
        buttonEntrar = new RoundButton("Entrar");
        buttonEntrar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
        buttonEntrar.setBounds(90, 302, 100, 30); 
        getContentPane().add(buttonEntrar);
        
        buttonEntrar.addActionListener(this);
        
        textoCadastrar = new JLabel("Ainda não tem uma Conta?");
        textoCadastrar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
        textoCadastrar.setBounds(282, 272, 190, 20);
        getContentPane().add(textoCadastrar);

        buttonCadastrar = new RoundButton("Cadastrar"); 
        buttonCadastrar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
        buttonCadastrar.setBounds(326, 302, 100, 30); // Ajuste de posicionamento e tamanho
        getContentPane().add(buttonCadastrar);
        
        buttonCadastrar.addActionListener(this);

        setVisible(true);
        
    }
    
    
    public static void main(String[] args) {
        new TelaInicial();
    }
    
    
    public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == buttonEntrar){
			this.dispose();
			new LoginView();
		}
		
		if (event.getSource() == buttonCadastrar){
			this.dispose();
			new CadastrarView();
		}
}}
