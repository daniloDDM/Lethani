import java.text.CharacterIterator;

public class MathOperator extends AFD{
	
        @Override
	public Token evaluate(CharacterIterator code){
		switch(code.current()){
			case '+' -> {
                            code.next();
                            return new Token("PLUS", "+");
                }
                        case '-' -> {
                            code.next();
                            return new Token("MINUS", "-");
                }
                        case '*' -> {
                            code.next();
                            return new Token("MULT", "*");
                }
                        case '/' -> {
                            code.next();
                            return new Token("DIV", "/");
                }
                        case '(' -> {
                            code.next();
                            return new Token("AP", "(");
                }
                        case ')' -> {
                            code.next();
                            return new Token("FP", ")");
                }
			case CharacterIterator.DONE -> {
                            code.next();
                            return new Token("EOF", "$");
                }
		default -> {
                    return null;
                }
		}
	}
	
}