package view;
 
import javax.swing.*;
import javax.swing.border.EmptyBorder;
 
import _Engenharia.Assistente;
 
import dao.*;
 
import javax.swing.text.*;
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
 
 
public class ChatApp extends JFrame {
	private int conversationId = 0;
    private Assistente assistente;
    private JTextPane chatArea;
    private JTextField inputField;
    private List<String> conversation;
 
    public ChatApp() {
        assistente = new Assistente();
        conversation = new ArrayList<>();
        setTitle("Assistente Virtual");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
 
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);
 
        JLabel titleLabel = new JLabel("CHAT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
 
        chatArea = new JTextPane();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        panel.add(scrollPane, BorderLayout.CENTER);
 
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200, 30)); 
        inputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true)); // Borda arredondada
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        inputField.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(inputField, BorderLayout.SOUTH);
 
        setVisible(true);
    }
 
    private void sendMessage() {
        String message = inputField.getText();
        appendToChat("Você: " + message);
        inputField.setText("");
        assistente.setPergunta(message);
        try {
            String answer = assistente.fazerPergunta();
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
 
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChatApp chatApp = new ChatApp();
                chatApp.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                    	chatApp.saveMessage();
                    	chatApp.conversationId++;
                   }
                });
            }
        });
    }
}