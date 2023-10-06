package view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.FormCadastroController;

public class TelaAssistente extends JFrame {
	
	    
	    JLabel title;
	    
	    public TelaAssistente() {
	    	 
	        
			JFrame jFrame = new JFrame();
			setTitle("Assistente");
		
	        setSize( 800,450);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setResizable(false);
	        setLocationRelativeTo(null);
	        setLayout(null);
	        
	        title = new JLabel("Assistente");
	        title.setFont(new Font("Arial", Font.BOLD, 30));
	        title.setBounds(235, 5, 300, 100);
	                
	        add(title);
	        
	        setVisible(true);

	    }
}
