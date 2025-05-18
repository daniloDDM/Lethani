package Sintatico;

import Lexico.Token;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Parser {
	
	List<Token> tokens;
	Token token;
	
	public Parser(List<Token> tokens){
		this.tokens = tokens;
	}
	
	public Token getNextToken() {
		if(!tokens.isEmpty())
			return tokens.remove(0);
		
		return null;
	}
	
	private void erro(String regra) {
		System.out.println("Regra: " + regra);
		System.out.println("token inválido: " + token.lexema);
		System.exit(0);
	}
	
	// adicionar métodos aqui -----------
	
	public void main() {
		token = getNextToken();
                if(
                    firstL("vincular")){
                    var();
                }
                
                traduz("""
                       begin
                       """);
                
                if(startProg()){
                    traduz("end.");
                    if(token.tipo.equals("EOF")){
                        System.out.println("sintaticamente correto");
                            try {
                                Files.writeString(Path.of("saida.pas"), saida.toString());
                                System.out.println("✅ Código Pascal salvo como 'saida.pas'");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                }
                erro("main");
	}
        
       public boolean startProg() {
           return lista();
       }
        
       public boolean lista() {
           return (comandos() && lista() || true);
       }
       
       public boolean comandos() {
           return (funcao() || alarif() || funCall() || print() || forCompasso() || ecoarWhile() || input() || atrVar());
       }
       
        public boolean ecoarWhile() {
            if(firstL("ecoar")){
                if(
                        matchL("ecoar", "while") &&
                        condicao() &&
                        bloco()
                        ) {
                    return true;
                }
                erro("ecoar");
            }
            return false;
        }
        
        //FOR -> compasso (expr(); fator()) {bloco}
        public boolean forCompasso() {
            if(firstL("compasso")){
                if(
                        matchL("compasso", "for") &&
                        matchL("(", " ") &&
                        expressao() &&
                        matchL(";", " to ") &&
                        fator() &&
                        matchL(")", " do \n") &&
                        bloco()) {
                    return true;
                }
                erro("for");
            }
            return false;
        }
        
        //VAR -> vincular operador_atribuicao ID 
        public boolean var() {
            if(
                    firstL("vincular")){
                if(   
                        matchL("vincular", "var \n") &&
                        id() &&
                        matchL("=", ":") &&
                        tipoVar()){
                    traduz(";\n \n");
                    return true;
                }
                erro("var");
            }
            return false;
        
        }
        
        //TIPOVAR -> verifica o tipo de variável declarada
        public boolean tipoVar() {
            if(
                    matchL("int", "integer") ||
                    matchL("string", "string") ||
                    matchL("float", "real")){
                return true;
            }
            erro("tipoVar");
            return false;
        }
        
         public boolean atrVar(){
             if(firstT(token.tipo)){
                if(
                        id() &&
                        matchL("=", ":=") &&
                        fator()){
                    traduz("; \n");
                    return true;
                }
                erro("atrVar");
             }
             return false;
        }
        
        // FUNCAO -> nomear ID FUNCAO_PARAM
        public boolean funcao() {
            if(firstL("nomear")){
                if(
                         matchL("nomear", "function") &&
                        id() &&
                        funParam()) {
                    erro("funcao");
                    return true; 
                }
                return false;
            }
            return false;
        }
        
        public boolean funParam() {
            if(
                    token.tipo.equals("EOF")
                    ) {return true;}
            if(
                    matchL("param", "com") &&
                    expressao() &&                   
                    bloco() ||
                    bloco()) {
                return true;
            }
            erro("funParam");
            return false;
        }
        
            //FUNCALL -> invocar ID FUNCALLPARAM
            public boolean funCall() {
                if(firstL("invocar")){
                    if(
                            matchL("invocar", "fct := ") &&
                            id() &&
                            funCallParam()){
                        return true;
                    }
                    erro("funCall");
                }
                return false;
            }
            
            public boolean funCallParam() {
                if(
                    token.tipo.equals("EOF")
                    ) {return true;}
                if(
                        matchL("param", "com") &&
                        fator()) {
                    return true;
                }
                erro("funCallParam");
                return false;
            }
	
            //IF -> (condicao) alar {bloco} ELSE IF
            public boolean alarif() {
                if(firstL("(")){
                    if( 
                            matchL("(", "if(") &&
                            condicao() && 
                            matchL("alar", "then" + "\n") && 
                            bloco() && 
                            elseif() ||
                            elseif()){
                        traduz("end.");
                        return true;
                            }
                    erro("alarif");
            }
                return false;
            }
        
        public boolean elseif() {
            if(
                    token.tipo.equals("EOF")
                    ) {return true;}
            if(
                    matchL("segunda", "then" + "\n")  && 
                    bloco() && 
                    matchL("desdobramento", "else" + "\n") && 
                    bloco() ||
                    matchL("desdobramento", "else" + "\n") && 
                    bloco() ||
                    token.tipo.equals("EOF")
                    ) {
                return true;
            }
            erro("elseif");
            return false;
        
        }
        
        //READLN -> inscrever ('ID | NUM')
        public boolean input() {
            if(firstL("inscrever")){
                if(
                        matchL("inscrever", "readln") &&
                        matchL("(", "(") &&
                        fator() &&
                        matchL(")", ")")) {
                    traduz("; \n");
                    return true;
                }
                erro("input");
            }
            return false;
        }
        
        //WRITELN -> cantar (’ ID | NUM ’)
        public boolean print() {
            if(firstL("cantar")){
                if(
                        matchL("cantar", "writeln") &&
                        matchL("(", "(") &&
                        fator() &&
                        matchL(")", ")")
                        ) {
                    traduz("""
                           ;
                           """);
                    return true;
                }
                erro("print");
            }
            return false;
        }
        
        public boolean condicao() {
            if(
                    matchL("(","(") && 
                    id() && 
                    operador() && 
                    num() &&
                    matchL(")",")") ||
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
            traduz("begin \n");
            if(matchL("{", "")) {
                while( 
                        id() && operador() && num() ||
                        id() && operador() && fator() ||
                        print() ||
                        fator()){
                    if(
                            matchL("}","\n")){
                        traduz("end \n");
                    return true;
                    }
                    
                }
            }
            erro("bloco");
            return false;
        }
        
        public boolean retorna() {
            if(
                    fator()){
                traduz(");");
                return true;
            }
            erro("retorna");
            return false;
        }
        
        public boolean fator(){
            if (
                        matchL("writeln", "cantar")){
                    print();
                }
            if(
                    id() ||
                    num() ||
                    operador() ||
                    expressao()) {
                return true;
            }
            
            erro("fator");
            return false;
        }
	
	public boolean id(){
		return matchT("ID", token.lexema);
	}
	
	public boolean num(){
		return matchT("NUM", token.lexema);
	}
	
	public boolean operador(){
		if(
                        matchL(">", ">") || 
                        matchL("<", "<") ||
                        matchL("+", "+") ||
                        matchL("-", "-") ||
                        matchL("*", "*") ||
                        matchL("/", "/") ||
                        matchL("=", ":=") ||
                        matchL("==", "=") ||
                        matchL(":", ":") ||
                        matchL(";", ";\n")) {
			return true;
		}
		erro("operador");
		return false;
	}
	
	public boolean expressao(){
		if(
                        id() && 
                        operador() && 
                        fator()) {
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
           
        public boolean firstL(String palavra){
		return token.lexema.equals(palavra);
	}
        
        public boolean firstT(String tipo){
            return token.tipo.equals("ID");
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
        
        private final StringBuilder saida = new StringBuilder();
        
        private void traduz(String code) {
            
            System.out.print(code + " ");
            saida.append(code);
           
            
        }
        
}