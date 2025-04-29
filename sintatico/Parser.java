import java.util.List;

public class Parser {
	
	List<Token> tokens;
	Token token;
	
	public Parser(List<Token> tokens){
		this.tokens = tokens;
	}
	
	public Token getNextToken() {
		if(tokens.size() > 0)
			return tokens.remove(0);
		
		return null;
	}
	
	private void erro(String regra) {
		System.out.println("Regra: " + regra);
		System.out.println("token inválido: " + token.lexema);
		System.exit(0);
	}
	
	// adicionar métodos aqui -----------
	
	void main() {
		token = getNextToken();
		if (
                        funcao() ||
                        alarif()){
			if(token.tipo.equals("EOF")){
				System.out.println("sintaticamente correto");
			}
		} else{		
			erro("main");
		}
	}
        
        public boolean funcao() {
            if(
                    matchL("function", "nomear") &&
                    id() &&
                    param()) {
                return true;
            }
            return false;
        
        }
        
        public boolean param(){
            if(
                    matchL("param", "com") &&
                    id() ||
                    num()
                    ) {
                return true;
            }
            erro("param");
            return false;
        }
	
	public boolean alarif() {
		if(
                        condicao() 
                        && matchL("if", "alar")
                        && bloco()
                        && elseif()){
				return true;
			}
                erro("alarif");
		return false;
	}
        
        public boolean elseif() {
            if(
                    token.tipo.equals("EOF")
                    ) {return true;}
            if(
                    matchL("else", "desdobramento") && 
                    bloco() ||
                    condicao() && 
                    matchL("elseif", "segunda_voz") && 
                    bloco() && 
                    matchL("else", "desdobramento") && 
                    bloco() ||
                    token.tipo.equals("EOF")
                    ) {
                return true;
            }
            erro("elseif");
            return false;
        
        }
        
        public boolean print() {
            if(
                    matchL("print", "cantar") &&
                    matchL("(", "(") &&
                    id() &&
                    matchL(")", ")") ||
                    matchL("(", "(") &&
                    num() &&
                    matchL(")", ")")
                    ) {
                return true;
            }
            erro("print");
            return false;
        
        }
        
        public boolean condicao() {
            if(
                    matchL("(","(") && 
                    id() && 
                    operador() && 
                    num() &&
                    matchL(")",")")) {
			return true;
		}
		erro("condicao");
		return false;
	}
        
        public boolean bloco(){
            if(matchL("{", "{")) {
                while( 
                        id() && operador() && num() ||
                        id() && operador() && fator() ||
                        fator() ||
                        print()){
                    if(matchL("}","}")){
                    return true;
                    }
                }
            }
            erro("bloco");
            return false;
        }
        
        public boolean fator(){
            if(
                    id() ||
                    num() ||
                    operador() ||
                    bloco()) {
                return true;
            }
            erro("fator");
            return false;
        }
	
	public boolean id(){
		return matchT("id", token.lexema);
	}
	
	public boolean num(){
		return matchT("num", token.lexema);
	}
	
	public boolean operador(){
		if(
                        matchL(">", ">") || 
                        matchL("<", "<") ||
                        matchL("+", "+") ||
                        matchL("-", "-") ||
                        matchL("*", "*") ||
                        matchL("/", "/") ||
                        matchL("=", "=")) {
			return true;
		}
		erro("operador");
		return false;
	}
	
	public boolean expressao(){
		if(
                        matchT("id", "id") && 
                        matchL("=", "=") && 
                        matchT("num", "num")) {
			return true;
		}
		erro("expressao");
		return false;
	}
	
	/*public boolean matchL(String lexema){
		if(token.lexema.equals(lexema)){
			token = getNextToken();
			return true;
		}
		return false;
	}*/
        
        public boolean matchL(String palavra, String newCode){
		if(token.lexema.equals(palavra)){
                        traduz(newCode);
			token = getNextToken();
			return true;
		}
		return false;
	}
	
	/*public boolean matchT(String tipo){
		if(token.tipo.equals(tipo)){
			token = getNextToken();
			return true;
		}
		return false;
	}*/
        
        public boolean matchT(String palavra, String newCode){
		if(token.tipo.equals(palavra)){
                    traduz(newCode);
			token = getNextToken();
			return true;
		}
		return false;
	}
        
        private void traduz(String code) {
            
            System.out.println(code);
        }
}