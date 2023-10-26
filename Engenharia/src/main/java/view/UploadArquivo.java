package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.HistoricoController;

public class UploadArquivo extends JFrame implements ActionListener{
	
    JButton upload;
    JButton historico;
    String arquivoPath;
    public String arquivoAcessado;

    private PerguntaResposta perguntaResposta =new PerguntaResposta();

    private HistoricoController historicoController;

     public void setHistoricoController(HistoricoController historicoController) {
    	this.historicoController = historicoController;
    }
	
    public UploadArquivo(){
	    
       perguntaResposta.setVisible(false);
	    
       //Titulo
	    
       JLabel title = new JLabel("Upload de Arquivos");
       title.setHorizontalAlignment(JLabel.CENTER);
       title.setVerticalAlignment(JLabel.TOP);
       title.setFont(new Font("Arial", Font.BOLD, 30));
              
       //Botão para abrir selecionador de arquivos
       upload = new JButton();
       upload.setBounds((450/2)-90, 100, 180, 32);
       upload.setHorizontalTextPosition(JButton.CENTER);
       upload.setFocusable(false);
       upload.setText("Selecione um Arquivo...");
       upload.addActionListener(this);     //Propriedade que detecta se o botão esta sento clicado
        
       historico = new JButton();

       historico.setBounds((450/2)-90, 150, 180, 32);
       historico.setHorizontalTextPosition(JButton.CENTER);
       historico.setFocusable(false);
       historico.setText("Historico");
       historico.addActionListener(this); 
	    
       this.setSize(450,450);
       this.setBackground(Color.yellow);
       this.setResizable(false);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setLocationRelativeTo(null);
       this.setLayout(null);
       
       //adiciona o botão e o titulo para o frame(tela)
       this.add(upload);
       this.add(historico);
       this.add(title);
       
       this.setVisible(true);
        
    }
    
    @Override
    //Metodo que sera chamado caso o botão seja apertado
    public void actionPerformed(ActionEvent e){
	    
        if (e.getSource() == historico){
    		historicoController.salvarNaTabela();
			this.dispose();
		}
	    
        if(e.getSource()==upload){   //verifica se o botão foi apertado
            
        	FileNameExtensionFilter filtro = new FileNameExtensionFilter("Text Files", "txt");   //Cria filtro para permitir selecionar apenas certos tipos de arquivos
        	
            JFileChooser explorador_arq = new JFileChooser();     //Cria uma janela para selecionar o arquivo
            explorador_arq.setFileFilter(filtro);                 //Aplica o filtro ao selecionador de arquivos
            int resposta = explorador_arq.showOpenDialog(null);   //verifica se foi selecionado um arquivo (leva o valor para a variavel resposta)
            
            if(resposta == JFileChooser.APPROVE_OPTION){
		    
                File arquivo = new File(explorador_arq.getSelectedFile().getAbsolutePath());  //pega o caminho do arquivo selecionado
                arquivoPath = arquivo.getAbsolutePath();     //Transforma o caminho em uma String

		 arquivoAcessado = (arquivo.getName());
                
                historicoController.setDocumento(arquivoAcessado);

			//Coletar a data e hora em que o arquivo foi inserido pelo usuário
		Date dataHoraAtual = new Date(); 
                LocalDateTime agora = LocalDateTime.now();
         
                	// formatar a data
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                String data = formatterData.format(agora);
         
                	// formatar a hora
                DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
                String hora = formatterHora.format(agora);
                
                String dataEHora = data+' '+hora;
                historicoController.setDataEHora(dataEHora);


		    
                historicoController.salvarHistorico();
                
                String texto;
                texto = arquivo.getAbsolutePath();
                char[] xChars = arquivoPath.toCharArray();
                
                String limpo;
        		limpo = "";
        		
        	try {
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                StringBuffer sb = new StringBuffer();
                String str;             
                
                while ((str = reader.readLine()) != null) {
        			sb.append(str).append("\n");
        			char[] chars = str.toCharArray();
        			for(int i = 0; i < chars.length; i++) {
            			if(chars[i] != ',' & chars[i] != '.' & chars[i] != '`' & chars[i] != ';' & chars[i] != ':'  & chars[i] != '[' 
            				& chars[i] != ']' & chars[i] != '(' & chars[i] != ')' & chars[i] != '{' & chars[i] != '}' & chars[i] != '/' 
            				& chars[i] != '!') {
            				limpo = limpo + chars[i];
            				
            			}
            		
        			
        			}	
        		limpo = limpo + "\n";
                } 
                reader.close();

        		} catch (IOException a) {
        			a.printStackTrace();
        		}
        		
        		try (PrintWriter out = new PrintWriter("src/main/java/_Engenharia/template.txt")) { //Cria um arquivo .txt no pacote _Engenharia
        		    out.println(limpo);
        		} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		perguntaResposta.setVisible(true);       //Cria a tela de pergunta
            	
                this.setVisible(false);
		    
            }
		
            perguntaResposta.setHistoricoControllerPergunta(historicoController);         //Cria a tela de pergunta
                
        }
        
    }
    
    public String getArquivoPath() {
    	return arquivoPath;
    }
    
}
