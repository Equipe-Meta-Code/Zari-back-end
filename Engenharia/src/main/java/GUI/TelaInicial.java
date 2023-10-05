package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    /* JLabel imagem; */ 
  
    
    public TelaInicial() {
    	setTitle("Início");
        setBackground(Color.yellow);
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(null);
    	
        /* ImageIcon image = new ImageIcon(getClass().getResource("zari.png"));
        
        imagem = new JLabel(image);
        add(imagem); 
        
     	Código teste da Imagem
        */ 
        
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

        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent event) {
    	TelaLogin frame = new TelaLogin();
    	 
    	frame.setVisible(true);
	}
    
    public static void main(String[] args) {
        new TelaInicial();
    }
    
}