package GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame {
    JLabel title;
    JLabel jLabelUser;
    JLabel jLabelPassword;
    JTextField jTextFieldUser;
    JTextField jPasswordField;
    JButton buttonEntrar;
    JButton buttonCadastrar;

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

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(115, 255, 550, 40); 
        jPasswordField.setFont(new Font("Arial", Font.ITALIC, 15));
        add(jPasswordField);

        buttonEntrar = new JButton("Entrar");
        buttonEntrar.setBounds(280, 310, 85, 30); 
        add(buttonEntrar);

        buttonCadastrar = new JButton("Cadastrar"); 
        buttonCadastrar.setBounds(380, 310, 100, 30); // Ajuste de posicionamento e tamanho
        add(buttonCadastrar);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}




