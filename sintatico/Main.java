import java.util.ArrayList;
import java.util.List;

//Função
public class Main {
    public static void main(String[] args) {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token("nomear", "function"));
        tokens.add(new Token("id", "galipole"));
        tokens.add(new Token("com", "param"));
        tokens.add(new Token("id", "int"));
        tokens.add(new Token("EOF", "$"));
        Parser parser = new Parser(tokens);
	parser.main();
        
    }
}
/*
// Apenas IF
public class Main {
	public static void main(String[] args) {
		List<Token> tokens = new ArrayList<>();
                tokens.add(new Token("ap", "("));
		tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_condicional", "="));
		tokens.add(new Token("num", "5"));
                tokens.add(new Token("fp", ")"));
                tokens.add(new Token("alar", "if"));
                tokens.add(new Token("ac", "{"));
                tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_condicional", "="));
		tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_condicional", "+"));
		tokens.add(new Token("num", "1"));
                tokens.add(new Token("fc", "}"));
		tokens.add(new Token("EOF", "$"));
		Parser parser = new Parser(tokens);
		parser.main();
	}
}

// If, ElseIf
public class Main {
	public static void main(String[] args) {
		List<Token> tokens = new ArrayList<>();
                tokens.add(new Token("ap", "("));
		tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_condicional", "="));
		tokens.add(new Token("num", "5"));
                tokens.add(new Token("fp", ")"));
                tokens.add(new Token("alar", "if"));
                tokens.add(new Token("ac", "{"));
		tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_soma", "+"));
		tokens.add(new Token("num", "1"));
                tokens.add(new Token("fc", "}"));
                tokens.add(new Token("ap", "("));
		tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_condicional", "<"));
		tokens.add(new Token("num", "5"));
                tokens.add(new Token("fp", ")"));
		tokens.add(new Token("reservada_elseif", "elseif"));
                tokens.add(new Token("ac", "{"));
		tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_atribuicao", "="));
		tokens.add(new Token("num", "soma"));
                tokens.add(new Token("operador_condicional", "/"));
                tokens.add(new Token("num", "2"));
                tokens.add(new Token("fc", "}"));
		tokens.add(new Token("EOF", "$"));
		Parser parser = new Parser(tokens);
		parser.main();
	}
}

//If completo
public class Main {
	public static void main(String[] args) {
		List<Token> tokens = new ArrayList<>();
                tokens.add(new Token("ap", "("));
		tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_condicional", "="));
		tokens.add(new Token("num", "5"));
                tokens.add(new Token("fp", ")"));
                tokens.add(new Token("alar", "if"));
                tokens.add(new Token("ac", "{"));
		tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_soma", "+"));
		tokens.add(new Token("num", "1"));
                tokens.add(new Token("fc", "}"));
                tokens.add(new Token("ap", "("));
		tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_condicional", "<"));
		tokens.add(new Token("num", "5"));
                tokens.add(new Token("fp", ")"));
		tokens.add(new Token("reservada_elseif", "elseif"));
                tokens.add(new Token("ac", "{"));
		tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_atribuicao", "="));
		tokens.add(new Token("num", "2"));
                tokens.add(new Token("fc", "}"));
                tokens.add(new Token("reservada_else", "else"));
                tokens.add(new Token("ac", "{"));
		tokens.add(new Token("id", "soma"));
		tokens.add(new Token("operador_atribuicao", "="));
		tokens.add(new Token("num", "soma"));
                tokens.add(new Token("operador_condicional", "*"));
                tokens.add(new Token("num", "2"));
                tokens.add(new Token("fc", "}"));
		tokens.add(new Token("EOF", "$"));
		Parser parser = new Parser(tokens);
		parser.main();
	}
}*/