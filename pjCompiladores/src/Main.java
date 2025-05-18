/*
import Lexico.Lexer;
import Lexico.Token;
import Sintatico.Parser;
import java.io.IOException;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
            List<Token> tokens = null;
            String data = "vincular i = int compasso ( i = 10; - 1) { cantar ( i ) } ";
            Lexer lexer = new Lexer(data);
            tokens = lexer.getTokens();
            for(Token t : tokens) {
                System.out.println(t);
            }
            Parser parser = new Parser(tokens);
            parser.main();
            
	}
}*/

import Lexico.Lexer;
import Lexico.Token;
import Sintatico.Parser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // Caminho do arquivo contendo o código fonte em Kote
        String caminhoArquivo = "codigo.kote";

        // Lê o conteúdo do arquivo como uma única string
        String data = Files.readString(Path.of(caminhoArquivo));

        // Executa o analisador léxico
        Lexer lexer = new Lexer(data);
        List<Token> tokens = lexer.getTokens();

        // Exibe os tokens
        for (Token t : tokens) {
            System.out.println(t);
        }

        // Executa o analisador sintático
        Parser parser = new Parser(tokens);
        parser.main();
    }
}