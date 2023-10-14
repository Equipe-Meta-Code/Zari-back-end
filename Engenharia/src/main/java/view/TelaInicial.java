package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TelaInicial extends JFrame implements ActionListener {
	
	JLabel title;
	JLabel textoEntrar;
	JLabel textoCadastrar;
	JButton buttonEntrar;
    JButton buttonCadastrar;
      
    public TelaInicial() {
    	
    	setTitle("Início");
        setBackground(Color.yellow);
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(null);

        
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("zaribot.png"));
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(270, 10, 260, 180); // Ajuste a posição e o tamanho conforme necessário
        add(imageLabel);
        
        title = new JLabel("Assistente Virtual Zari");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(240, 160, 400, 100);
        add(title);
        
        textoEntrar = new JLabel("Já possui uma conta?");
        textoEntrar.setFont(new Font("Arial", Font.BOLD, 15));
        textoEntrar.setBounds(140, 270, 400, 20);
        add(textoEntrar);
        
        buttonEntrar = new JButton("Entrar");
        buttonEntrar.setBounds(165, 300, 100, 30); 
        add(buttonEntrar);
        
        buttonEntrar.addActionListener(this);
        
        textoCadastrar = new JLabel("Ainda não tem uma Conta?");
        textoCadastrar.setFont(new Font("Arial", Font.BOLD, 15));
        textoCadastrar.setBounds(480, 270, 400, 20);
        add(textoCadastrar);

        buttonCadastrar = new JButton("Cadastrar"); 
        buttonCadastrar.setBounds(525, 300, 100, 30); // Ajuste de posicionamento e tamanho
        add(buttonCadastrar);
        
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