import java.text.CharacterIterator;

public class Identificador extends AFD{
    
        @Override
        public Token evaluate(CharacterIterator code) {
		
		if(Character.isLetter(code.current())) {
			String string = readString(code);
                        
                        if(isTokenSeparator(code)){
                            return new Token("ID", string);
                        }
		}
		return null;
	}
	
	private String readString(CharacterIterator code) {
		String string = "";
		while (Character.isLetter(code.current())) {
			string += code.current();
			code.next();
		}
		return string;
	}
    }
