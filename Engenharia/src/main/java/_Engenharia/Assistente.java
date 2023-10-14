package _Engenharia;


import dev.langchain4j.chain.ConversationalRetrievalChain;

import dev.langchain4j.data.document.Document;
//import dev.langchain4j.data.document.splitter.ParagraphSplitter;    !!!!!!!!!!!!!!!DANDO ERRO, substitui temporariamente!!!!!!!!!!!!!!!!!!!!!
import dev.langchain4j.data.document.splitter.DocumentSplitters;   //Substituição
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.model.huggingface.HuggingFaceEmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static dev.langchain4j.data.document.FileSystemDocumentLoader.loadDocument;
import static java.time.Duration.ofSeconds;

import java.io.File;


public class Assistente {
	
	 // You can get your own HuggingFace API key here: https://huggingface.co/settings/tokens
    public static final String hfApiKey = "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";
	
    
    private static String pergunta;
    
	public String fazerPergunta() throws Exception {
		
		

        Document document = loadDocument(toPath("template.txt"));  //Usa documento criado com todos os dados do documento selecionado (Esse documento e criado dentro do pacote _Engenharia)
        
        //escolhendo um modelo para vetorizar meu texto
        EmbeddingModel embeddingModel = HuggingFaceEmbeddingModel.builder()
                .accessToken(hfApiKey)
                .modelId("sentence-transformers/all-MiniLM-L6-v2")
                .waitForModel(true)
                .timeout(ofSeconds(60))
                .build();
        
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        //estou aplicando o modelo de vetorização escolhido ao meu texto
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                // .splitter(new ParagraphSplitter())  !!!!!!!!!!!!!!!DANDO ERRO, substitui temporariamente!!!!!!!!!!!!!!!!!!!!!
        		.documentSplitter(DocumentSplitters.recursive(500))  //Substituição
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();
        ingestor.ingest(document);

  
 
        //aqui eu escolho o modelo da inferência (a pergunta)
        ConversationalRetrievalChain chain = ConversationalRetrievalChain.builder()
                .chatLanguageModel(HuggingFaceChatModel.withAccessToken(hfApiKey))
                .retriever(EmbeddingStoreRetriever.from(embeddingStore, embeddingModel))
                // .chatMemory() // you can override default chat memory
                // .promptTemplate() // you can override default prompt template
                .build();
        
        
        //aqui eu faço a inferência
        String answer = chain.execute(pergunta);
        
        File delete_file = new File("src/main/java/_Engenharia/template.txt");     //Apaga o documento depois da resposta
        delete_file.delete();                                                     //Caso erro na resposta o arquivo NAO e deletado
        
        return answer; // Charlie is a cheerful carrot living in VeggieVille...
        //exemplo para continuar a pesquisa
        //https://github.com/langchain4j/langchain4j/blob/7307f43d9823af619f1e3196252d212f3df04ddc/langchain4j/src/main/java/dev/langchain4j/model/huggingface/HuggingFaceChatModel.java
	}
	
	private static Path toPath(String fileName) {
        try {
            URL fileUrl = Assistente.class.getResource(fileName);
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
	}

	
	public void setPergunta(String p) {
		pergunta = p;
	}
	
}
