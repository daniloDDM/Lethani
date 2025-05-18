package Lexico;

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
                        case "segunda" -> { // = ELSE_IF(IF ENCADEADO)
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
                        case "retorna" -> {// = RETURN
                            code.next();
                            return new Token("RES", string);
                            }
                        case "ecoar" -> { // = WHILE
                            code.next();
                            return new Token("RES", string);
                            }
                        case "nomear" -> { // = FUNCTION
                            code.next();
                            return new Token("RES", string);
                            }
                        case "vincular" -> { // = VAR 
                            code.next();
                            return new Token("RES", string);
                            }
                        case "cantar" -> { // = WRITELN
                            code.next();
                            return new Token("RES", string);
                            }
                        case "inscrever" -> { // = READLN
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
