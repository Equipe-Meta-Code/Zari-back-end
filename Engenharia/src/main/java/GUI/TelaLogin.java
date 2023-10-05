package GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaLogin extends JFrame {
    JLabel title;
    JLabel jLabelUser;
    JLabel jLabelPassword;
    JTextField jTextFieldUser;
    JPasswordField senha;
    JButton buttonEntrar;
    JButton buttonCadastrar;
    JCheckBox buttonShow;


    public TelaLogin() {
        setTitle("Login");
        setBackground(Color.yellow);
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(null);

        title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(350, 20, 300, 100);
        add(title);

        jLabelUser = new JLabel("User:");
        jLabelUser.setBounds(115, 105, 200, 50);
        jLabelUser.setFont(new Font("Arial", Font.PLAIN, 14));
        add(jLabelUser);

        jTextFieldUser = new JTextField();
        jTextFieldUser.setBounds(115, 155, 550, 40); 
        jTextFieldUser.setFont(new Font("Arial", Font.ITALIC, 15));
        add(jTextFieldUser);

        jLabelPassword = new JLabel("Senha:");
        jLabelPassword.setBounds(115, 205, 250, 50);
        jLabelPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        add(jLabelPassword);

        senha = new JPasswordField();
        senha.setBounds(115, 255, 550, 40); 
        senha.setFont(new Font("Arial", Font.ITALIC, 15));
        add(senha);

        //botao para mostrar senha 
        buttonShow = new JCheckBox("Mostrar Senha");
        buttonShow.setBounds(500, 280, 250, 40);
        buttonShow.setFont(new Font("Arial", Font.ITALIC, 15));
        
        add(buttonShow);
        
        buttonShow.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(buttonShow.isSelected())
        			senha.setEchoChar((char)0);
        		else
        			senha.setEchoChar('*');
        	} 
        });

        buttonEntrar = new JButton("Entrar");
        buttonEntrar.setBounds(280, 310, 85, 30); 
        add(buttonEntrar);

        buttonCadastrar = new JButton("Cadastrar"); 
        buttonCadastrar.setBounds(380, 310, 100, 30); // Ajuste de posicionamento e tamanho
        add(buttonCadastrar);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

    public static void main(String[] args) {
        new TelaLogin();
    }
}




