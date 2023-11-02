
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import _Engenharia.Assistente;
import controller.HistoricoController;

public class PerguntaResposta extends JFrame implements ActionListener{
    
    JLabel title;
    JLabel jLabel;
    JLabel jLabel2;
    JTextField caixa1;
    JTextField caixa2;
    
    RoundButton enviar;
    RoundButton buttonHistorico;
    
    private Assistente assistente = new Assistente();
    private HistoricoController historicoController;

    public void setHistoricoControllerPergunta(HistoricoController historicoController) {
    	this.historicoController = historicoController;
    }
	
    public PerguntaResposta() {
        
	JFrame jFrame = new JFrame();
	setTitle("Aba de perguntas e respostas");
        setBackground(Color.yellow);
        setSize( 800,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
	
        title = new JLabel("Pergunta / Resposta");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(235, 20, 300, 100);
                
        add(title);
        
        jLabel = new JLabel("Insira sua pergunta:");
        jLabel.setBounds(115, 105, 200, 50);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 14));
       
        add(jLabel);
        
        caixa1 = new JTextField();
        caixa1.setBounds(115, 145, 550, 40);
        caixa1.setFont(new Font("Arial", Font.ITALIC, 15));
        
        enviar = new RoundButton("Enviar");
        enviar.setBounds(325, 190, 85, 30);
        add(enviar);
        
        enviar.addActionListener(this);
                
        add(caixa1);
        
        jLabel2 = new JLabel("Sua resposta será carregada abaixo:");
        jLabel2.setBounds(115, 230, 250, 50);
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
  
                 
        add(jLabel2);
        
        caixa2 = new JTextField();
        caixa2.setBounds(115, 270, 550, 40);
        caixa2.setFont(new Font("Arial", Font.ITALIC, 15));
                
        add(caixa2);

	//botao historico
	buttonHistorico = new RoundButton("Histórico");
        buttonHistorico.setBounds(325, 350, 100, 30); // Ajuste de posicionamento e tamanho
        add(buttonHistorico);
        buttonHistorico.addActionListener(this); 
        
        setVisible(true);
    }
        
   

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == enviar){
			assistente.setPergunta(caixa1.getText());
			
			try {
				String resposta = assistente.fazerPergunta();
				caixa2.setText(resposta);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			}
			
	}	
		
		if (event.getSource() == buttonHistorico){
			limparCaixa();
			this.dispose();
			
			historicoController.salvarNaTabela();
			
		}
		
	}
	public void limparCaixa(){
		caixa1.setText("");
		caixa2.setText("");
		}
	
}
   
