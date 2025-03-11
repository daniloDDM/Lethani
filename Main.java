import java.io.IOException;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
		List<Token> tokens = null;
		String data = "2.45 + 40";
		Lexer lexer = new Lexer(data);
		tokens = lexer.getTokens();
		for(Token t : tokens) {
			System.out.println(t);
		}
	}
}