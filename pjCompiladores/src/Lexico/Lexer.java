package Lexico;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Lexer{
	
	private List<Token> tokens;
	private List<AFD> afds;
	private CharacterIterator code;
	
	public Lexer(String code) {
		tokens = new ArrayList<>();
		this.code = new StringCharacterIterator(code);
		afds = new ArrayList<>();
		afds.add(new MathOperator());
		afds.add(new Number());
                afds.add(new Reservado());
                afds.add(new Identificador());
                afds.add(new StringID());
	}
	
	public void skipWhiteSpace(){
		while (code.current() == ' ' || code.current() == '\n') {
			code.next();
		}
	}
	
	public List<Token> getTokens() {
		Token t;
		do {
			skipWhiteSpace();
			t = searchNextToken();
			if(t == null) erro();
			tokens.add(t);
		}while (!"EOF".equals(t.tipo));
		return tokens;
	}
	
	public Token searchNextToken() {
		int pos = code.getIndex();
		for (AFD afd : afds) {
			Token t = afd.evaluate(code);
			if (t != null) return t;
			code.setIndex(pos);
		}
		return null;
	}
	
	public void erro(){
		new RuntimeException("Character não reconhecido: " + code.current());
	}
}