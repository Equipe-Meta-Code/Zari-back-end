package _Engenharia;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
//import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.huggingface.HuggingFaceEmbeddingModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static dev.langchain4j.data.document.FileSystemDocumentLoader.loadDocument;
import static dev.langchain4j.data.message.UserMessage.userMessage;
import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;
import static java.util.stream.Collectors.joining;

import static java.time.Duration.ofSeconds;

import assistente.ApiKeys;

public class AssistenteMemoryDocument {

	
	private static final String vetmodel = "sentence-transformers/all-MiniLM-L6-v2";
	
	private static final ChatMemory chatMemory = TokenWindowChatMemory.withMaxTokens(300, new OpenAiTokenizer(GPT_3_5_TURBO));
	
	
	public String fazerPergunta(String pergunta) throws Exception {

		//ChatMemoria
		//ChatLanguageModel model = OpenAiChatModel.withApiKey(ApiKeys.OPENAI_API_KEY);

        
		
        
        //Chat Language Model Builder OpenAi
        ChatLanguageModel chatLanguageModel = OpenAiChatModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .temperature(0.0)
                .timeout(ofSeconds(900))
                .build();  
        
        
        //EmbeddingModel Builder HuggingFace
        EmbeddingModel embeddingModel = HuggingFaceEmbeddingModel.builder()
                .accessToken(ApiKeys.HF_API_KEY)
                .modelId(vetmodel)
                .waitForModel(true)
                .timeout(ofSeconds(60))
                .build();
        
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        //"Cosumo" do texto do arquivo
        
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(500, 0))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        Document document = loadDocument(toPath("template.txt"));
        ingestor.ingest(document);

        ConversationalRetrievalChain chain = ConversationalRetrievalChain.builder()
                .chatLanguageModel(chatLanguageModel)
                .retriever(EmbeddingStoreRetriever.from(embeddingStore, embeddingModel))
                .chatMemory(chatMemory) // you can override default chat memory
                // .promptTemplate() // you can override default prompt template
                .build();

        chatMemory.add(userMessage(pergunta));
        
        return chain.execute(pergunta);
        
		}
	
	
	//Procura arquivo
	private static Path toPath(String fileName) {
        try {
            URL fileUrl = AssistenteMemoryDocument.class.getResource(fileName);
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
	
	
}
