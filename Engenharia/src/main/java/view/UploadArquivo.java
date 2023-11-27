package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import controller.HistoricoController;

public class UploadArquivo extends JFrame implements ActionListener{
	
	RoundButton upload;
    RoundButton uploadPdf;
    RoundButton historico;
    String arquivoPath;
    public String arquivoAcessado;

    private ChatApp chatApp = new ChatApp();

    private HistoricoController historicoController;

     public void setHistoricoController(HistoricoController historicoController) {
    	this.historicoController = historicoController;
    }
	
    public UploadArquivo(){
       ImageIcon icon = new ImageIcon(getClass().getResource("zaribot.png"));
       setIconImage(icon.getImage());
       Image resizedIcon = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
       ImageIcon finalIcon = new ImageIcon(resizedIcon);
       etIconImage(finalIcon.getImage());
	    
       chatApp.setVisible(false);
	    
       //Titulo
	    
       JLabel title = new JLabel("Upload de Arquivos");
       title.setHorizontalAlignment(JLabel.CENTER);
       title.setVerticalAlignment(JLabel.TOP);
       title.setFont(new Font("Arial", Font.BOLD, 30));
              
       //Botão para abrir selecionador de arquivos
       upload = new RoundButton("Selecione um Arquivo TXT");
       upload.setBounds((450/2)-110, 100, 200, 32);
       upload.setHorizontalTextPosition(JButton.CENTER);
       upload.setFocusable(false);
       upload.addActionListener(this);     //Propriedade que detecta se o botão esta sento clicado
	    
	//Botão para abrir selecionador de arquivos
       uploadPdf = new RoundButton("Selecione um Arquivo PDF");
       uploadPdf.setBounds((450/2)-110, 150, 200, 32);
       uploadPdf.setHorizontalTextPosition(JButton.CENTER);
       uploadPdf.setFocusable(false);
       uploadPdf.addActionListener(this);     //Propriedade que detecta se o botão esta sento clicado
	    
       historico = new RoundButton("Histórico");

       historico.setBounds((450/2)-110, 200, 200, 32);
       historico.setHorizontalTextPosition(JButton.CENTER);
       historico.setFocusable(false);
       historico.addActionListener(this); 
	    
       this.setSize(450,450);
       this.setBackground(Color.yellow);
       this.setResizable(false);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setLocationRelativeTo(null);
       this.setLayout(null);
       
       //adiciona o botão e o titulo para o frame(tela)
       this.add(upload);
       this.add(uploadPdf);
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
        		chatApp.setVisible(true);      //Cria a tela de pergunta
            	
                this.setVisible(false);
		    
            }
		
            chatApp.setHistoricoControllerChat(historicoController);         //Cria a tela de pergunta
                
        }
      if (e.getSource() == uploadPdf){
    		
        	
        	FileNameExtensionFilter filtroPdf = new FileNameExtensionFilter("Pdf Files", "pdf");   //Cria filtro para permitir selecionar apenas certos tipos de arquivos
        	
            JFileChooser explorador_pdf = new JFileChooser();     //Cria uma janela para selecionar o arquivo
            explorador_pdf.setFileFilter(filtroPdf);                 //Aplica o filtro ao selecionador de arquivos
            int respostaPdf = explorador_pdf.showOpenDialog(null);
            
            if(respostaPdf == JFileChooser.APPROVE_OPTION){

		File arquivo = new File(explorador_pdf.getSelectedFile().getAbsolutePath());  //pega o caminho do arquivo selecionad
                String fileName = explorador_pdf.getSelectedFile().getAbsolutePath(); // provide the path to pdf file
                PDDocument document = null;


                    try (PrintWriter out = new PrintWriter("src/main/java/_Engenharia/template.txt")){
                        PDFTextStripper stripper = new PDFTextStripper();
                           document = PDDocument.load( new File(fileName));
                           String pdfText;
                           pdfText = stripper.getText(document).toString();
                           out.println(pdfText);
			   document.close();
                        
                       } catch (IOException e1) {
                           // TODO Auto-generated catch block
                           e1.printStackTrace();
                       } 

                    arquivoAcessado = (arquivo.getName());
                    
                    historicoController.setDocumento(arquivoAcessado);

                    //Coletar a data e hora em que o arquivo foi inserido pelo usuÃ¡rio
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
                    chatApp.setVisible(true);      //Cria a tela de pergunta
                	
                    this.setVisible(false);
                 
                    
        }
  	chatApp.setHistoricoControllerChat(historicoController);         //Cria a tela de pergunta          
		}  
    }
    
    public String getArquivoPath() {
    	return arquivoPath;
    }
    
}
