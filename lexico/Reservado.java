import java.text.CharacterIterator;

public class Reservado extends AFD{
    
        @Override
        public Token evaluate(CharacterIterator code) {
		
		if(Character.isLetter(code.current())) {
			String string = readString(code);
                        
                        switch(string){
			case "alar" -> { // = IF
                            code.next();
                            return new Token("RES", string);
                            }
                        case "segunda_voz" -> { // = ELSE_IF
                            code.next();
                            return new Token("RES", string);
                            }
                        case "desdobramento" -> { // = ELSE
                            code.next();
                            return new Token("RES", string);
                            }
                        case "compasso" -> { // = FOR
                            code.next();
                            return new Token("RES", string);
                            }
                        case "pausa" -> { // = BREAK
                            code.next();
                            return new Token("RES", string);
                            }
                        case "dizer" -> {// = RETURN (responder)
                            code.next();
                            return new Token("RES", string);
                            }
                        case "ecoar" -> { // = WHILE
                            code.next();
                            return new Token("RES", string);
                            }
                        
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
