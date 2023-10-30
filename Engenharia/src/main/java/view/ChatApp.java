package view;
 
import javax.swing.*;
import javax.swing.border.EmptyBorder;
 
import assistente.AssistenteMemoryDocument;
import controller.HistoricoController;
import dao.*;
 
import javax.swing.text.*;

import assistente.AssistenteMemoryDocument;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
 
 
public class ChatApp extends JFrame implements ActionListener{
	private int conversationId = 0;
    private AssistenteMemoryDocument assistente;  //Seta varial para objeto
    private JTextPane chatArea;
    private JTextField inputField;
    JButton buttonHistorico;
    
    private List<String> conversation;
    
	private HistoricoController historicoController;
	
	public void setHistoricoControllerChat(HistoricoController historicoController) {
		this.historicoController = historicoController;
	}
 
    public ChatApp() {
    	assistente = new AssistenteMemoryDocument(); //Cria novo objeto assistnte (que possui o metodo fazerPergunta(String pergunta))
        conversation = new ArrayList<>();
        setTitle("Assistente Virtual");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
 
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        JPanel chatPanel = new JPanel(new BorderLayout());
        mainPanel.add(chatPanel);

	JPanel titlePanel = new JPanel(new BorderLayout());
        mainPanel.add(titlePanel, BorderLayout.NORTH);
 
        JLabel titleLabel = new JLabel("CHAT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
 
        chatArea = new JTextPane();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        chatPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
 
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(150, 30)); 
        inputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true)); // Borda arredondada
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        inputField.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.add(inputField, BorderLayout.CENTER);
        
        
        buttonHistorico = new JButton("Histórico");
        buttonHistorico.setPreferredSize(new Dimension(100, 10)); // Ajuste de posicionamento e tamanho
        titlePanel.add(buttonHistorico, BorderLayout.EAST);
        buttonHistorico.addActionListener(this); 

	 addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    saveMessage();
                } catch (Exception ex) {
                    ex.printStackTrace();

                    // Exibir uma mensagem de erro com JOptionPane
                    JOptionPane.showMessageDialog(ChatApp.this, "Erro ao salvar as mensagens.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
	    
        setVisible(true);
    }
 
    private void sendMessage() {
        String message = inputField.getText();
        appendToChat("Você: " + message);
        inputField.setText("");
        try {
        	String answer = assistente.fazerPergunta(message);    //envia pergunta para o bot e devolve resposta String
            appendToChat("Assistente Zari: " + answer);
        } catch (Exception e) {
            appendToChat("Assistente Zari: Erro ao obter resposta do assistente.");
        }
    }
 
    private void appendToChat(String text) {
        StyledDocument doc = chatArea.getStyledDocument();
 
        SimpleAttributeSet style = new SimpleAttributeSet();
        StyleConstants.setBold(style, false);
        StyleConstants.setForeground(style, Color.BLACK);
 
        String[] words = text.split(" ");
 
        for (String word : words) {
            if (word.equals("Você:") || word.equals("Assistente") || word.equals("Zari:")) {
                StyleConstants.setBold(style, true);
            } else {
                StyleConstants.setBold(style, false);
            }
            try {
                doc.insertString(doc.getLength(), word + " ", style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
 
        try {
            doc.insertString(doc.getLength(), "\n\n", style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
 
        // Adicione a mensagem à lista de conversa
        conversation.add(text);
    }
    
    public void saveMessage() {
    	databaseConnection dbConnection = new databaseConnection();
        Connection connection = dbConnection.getConnection();
 
        try {
            // Use um StringBuilder para concatenar todas as mensagens
            StringBuilder conversationBuilder = new StringBuilder();
            for (String message : conversation) {
                conversationBuilder.append(message).append(" ");
            }

            String sql = "INSERT INTO conversa (id, mensagem) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, conversationId);
            statement.setString(2, conversationBuilder.toString().trim());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent event) {		
		if (event.getSource() == buttonHistorico){
			this.dispose();
			
			historicoController.salvarNaTabela();
			
		}
		
	}
	
}
