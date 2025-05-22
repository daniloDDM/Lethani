package Sintatico;
import Lexico.Token;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Parser {
	
	List<Token> tokens;
	Token token;
        Tree tree;
        Node root;
	
	public Parser(List<Token> tokens){
		this.tokens = tokens;
                this.root = new Node("--> RAIZ <--");
                this.tree = new Tree(root);
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
	
	public Tree main() {
		token = getNextToken();
                Node Root = new Node("main");
                tree.setRoot(Root);
                if(startProg(Root)){
                    traduz("end.");
                    if(matchT("EOF", Root)){
                        System.out.println("sintaticamente correto");
                            try {
                                Files.writeString(Path.of("saida.pas"), saida.toString());
                                System.out.println("Código salvo como 'saida.pas'");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    } else {
                        erro("main");
                        System.out.print("Parse error");
                    }   
                }
                return null;
	}
        
       public boolean startProg(Node root) {
           Node startProg = new Node("Start Prog");
           return lista(startProg);
       }
        
       public boolean lista(Node node) {
           Node lista = node.addNode("lista");
           return (comandos(lista) && lista(lista) || true);
       }
       
       public boolean comandos(Node node) {
           Node comandos = node.addNode("comandos");
           return (var(comandos) || funcao(comandos) || alarif(comandos) || funCall(comandos) || print(comandos) || forCompasso(comandos) || ecoarWhile(comandos) || input(comandos) || atrVar(comandos));
       }
       
        public boolean ecoarWhile(Node node) {
            if(firstL("ecoar")){
                Node ecoar = node.addNode("ecoarWhile");
                if(
                        matchL("ecoar", "while", ecoar) &&
                        matchL("(", " ", ecoar) &&
                        expressao(ecoar) &&
                        matchL(")", " do \n", ecoar) &&
                        bloco(ecoar)
                        ) {
                    return true;
                }
                erro("ecoar");
            }
            return false;
        }
        
        //FOR -> compasso (expr(); fator()) {bloco}
        public boolean forCompasso(Node node) {
            if(firstL("compasso")){
                Node compasso = node.addNode("forCompasso");
                if(
                        matchL("compasso", "for", compasso) &&
                        matchL("(", " ", compasso) &&
                        expressao(compasso) &&
                        matchL(";", " to ", compasso) &&
                        fator(compasso) &&
                        matchL(")", " do \n", compasso) &&
                        bloco(compasso)) {
                    return true;
                }
                erro("for");
            }
            return false;
        }
        
        //VAR -> vincular operador_atribuicao ID 
        public boolean var(Node node) {
            if(
                    firstL("vincular")){
                Node var = node.addNode("var");
                if(matchL("vincular", "var \n", var)){
                    while(
                            id(var) &&
                            matchL("=", ":", var) &&
                            tipoVar(var)){
                        if(   
                                matchL(";", "", var)){
                            traduz("\n \n");
                            traduz("begin \n");
                            return true;
                        }
                    }
                erro("var");
            }
        
        }
            return false;
        }
            
        
        //TIPOVAR -> verifica o tipo de variável declarada
        public boolean tipoVar(Node node) {
            Node tipoVar = node.addNode("tipoVar");
            if(
                    matchL("int", "integer", tipoVar) ||
                    matchL("string", "string", tipoVar) ||
                    matchL("float", "real", tipoVar)){
                traduz("; \n");
                return true;
            }
            erro("tipoVar");
            return false;
        }
        
         public boolean atrVar(Node node){
             if(firstT(token.tipo)){
                 Node atrVar = node.addNode("atrVar");
                if(
                        id(atrVar) &&
                        matchL("=", ":=", atrVar) &&
                        fator(atrVar)){
                    traduz("; \n");
                    return true;
                }
                erro("atrVar");
             }
             return false;
        }
        
        // FUNCAO -> nomear ID FUNCAO_PARAM
        public boolean funcao(Node node) {
            if(firstL("nomear")){
                Node funcao = node.addNode("funcao");
                if(
                         matchL("nomear", "function", funcao) &&
                        id(funcao) &&
                        funParam(funcao)) {
                    erro("funcao");
                    return true; 
                }
                return false;
            }
            return false;
        }
        
        public boolean funParam(Node node) {
            Node funParam = node.addNode("funParam");
            if(
                    token.tipo.equals("EOF")
                    ) {return true;}
            if(
                    matchL("param", "com", funParam) &&
                    expressao(funParam) &&                   
                    bloco(funParam) ||
                    bloco(funParam)) {
                return true;
            }
            erro("funParam");
            return false;
        }
        
            //FUNCALL -> invocar ID FUNCALLPARAM
            public boolean funCall(Node node) {
                if(firstL("invocar")){
                    Node funCall = node.addNode("funCall");
                    if(
                            matchL("invocar", "fct := ", funCall) &&
                            id(funCall) &&
                            funCallParam(funCall)){
                        return true;
                    }
                    erro("funCall");
                }
                return false;
            }
            
            public boolean funCallParam(Node node) {
                Node funCallParam = node.addNode("callParam");
                if(
                    token.tipo.equals("EOF")
                    ) {return true;}
                if(
                        matchL("param", "com", funCallParam) &&
                        fator(funCallParam)) {
                    return true;
                }
                erro("funCallParam");
                return false;
            }
	
            //IF -> (condicao) alar {bloco} ELSE IF
            public boolean alarif(Node node) {
                if(firstL("(")){
                    Node alarif = node.addNode("alarIf");
                    if( 
                            matchL("(", "if(", alarif) &&
                            condicao(alarif) && 
                            matchL("alar", "then" + "\n", alarif) && 
                            bloco(alarif) && 
                            elseif(alarif) ||
                            elseif(alarif)){
                        traduz("end.");
                        return true;
                            }
                    erro("alarif");
            }
                return false;
            }
        
        public boolean elseif(Node node) {
            Node elseIf = node.addNode("elseIf");
            if(
                    token.tipo.equals("EOF")
                    ) {return true;}
            if(
                    matchL("segunda", "then" + "\n", elseIf)  && 
                    bloco(elseIf) && 
                    matchL("desdobramento", "else" + "\n", elseIf) && 
                    bloco(elseIf) ||
                    matchL("desdobramento", "else" + "\n", elseIf) && 
                    bloco(elseIf) ||
                    token.tipo.equals("EOF")
                    ) {
                return true;
            }
            erro("elseif");
            return false;
        
        }
        
        //READLN -> inscrever ('ID | NUM')
        public boolean input(Node node) {
            if(firstL("inscrever")){
                Node input = node.addNode("input");
                if(
                        matchL("inscrever", "readln", input) &&
                        matchL("(", "(", input) &&
                        fator(input) &&
                        matchL(")", ")", input)) {
                    traduz("; \n");
                    return true;
                }
                erro("input");
            }
            return false;
        }
        
        //WRITELN -> cantar (’ ID | NUM ’)
        public boolean print(Node node) {
            if(firstL("cantar")){
                Node print = node.addNode("print");
                if(
                        matchL("cantar", "writeln", print) &&
                        matchL("(", "(", print) &&
                        printContent(print)) {
                    traduz("""
                           ;
                           """);
                    return true;
                }
                erro("print");
            }
            return false;
        }
        
        public boolean condicao(Node node) {
            Node condicao = node.addNode("condicao");
            if(
                    matchL("(","(", condicao) && 
                    id(condicao) && 
                    operador(condicao) && 
                    fator(condicao) &&
                    matchL(")",")", condicao) ||
                    id(condicao) && 
                    operador(condicao) && 
                    fator(condicao) &&
                    matchL(")",")", condicao)) {
			return true;
		}
		erro("condicao");
		return false;
	}
        
        public boolean bloco(Node node){
            traduz("begin \n");
            Node bloco = node.addNode("bloco");
            if(matchL("{", "", bloco)) {
                while(
                        id(bloco) && operador(bloco) && num(bloco) ||
                        id(bloco) && operador(bloco) && fator(bloco) ||
                        retorna(bloco) ||
                        print(bloco) ||
                        fator(bloco)
                        ){
                    if(
                            matchL("}","\n", bloco)){
                        traduz("end \n");
                    return true;
                    }
                    
                }
            }
            erro("bloco");
            return false;
        }
        
        public boolean retorna(Node node) {
            Node retorna = node.addNode("retorna");
            if(firstL("retorna")){
                if(
                        matchL("retorna", "\n writeLn (", retorna) &&
                        fator(retorna)){
                    traduz(");");
                    return true;
                }
                erro("retorna");
            }
            return false;
        }
        
        public boolean printContent(Node node) {
            Node printContent = node.addNode("printContent");
            if(firstT(token.tipo)){
                if(
                        id(printContent) &&
                        operador(printContent) &&
                        id(printContent) &&
                        matchL(")", ")", printContent) ||
                        id(printContent) &&
                        matchL(")", ")", printContent) ||
                        num(printContent) &&
                        matchL(")", ")", printContent) ||
                        matchL(")", ")", printContent)
                        ){
                    return true;
                }
                erro("printContent");
            }
            return false;
        }
        
        public boolean fator(Node node){
            Node fator = node.addNode("fator");
            if (
                        matchL("writeln", "cantar", fator)){
                    print(fator);
                }
            if(
                    id(fator) ||
                    num(fator) ||
                    operador(fator) ||
                    expressao(fator) ||
                    retorna(fator)) {
                return true;
            }
            
            erro("fator");
            return false;
        }
	
	public boolean id(Node node){
            Node id = node.addNode("id");
		return matchT("ID", token.lexema, id);
	}
	
	public boolean num(Node node){
            Node num = node.addNode("num");
		return matchT("NUM", token.lexema, num);
	}
	
	public boolean operador(Node node){
            Node operador = node.addNode("operador");
            if(firstL("<") || firstL("+") || firstL(">") || firstL("-") || firstL("*") || firstL("/") || firstL("=") || firstL("==") || firstL(":") || firstL(";")){
		if(
                        matchL(">", ">", operador) || 
                        matchL("<", "<", operador) ||
                        matchL("+", "+", operador) ||
                        matchL("-", "-", operador) ||
                        matchL("*", "*", operador) ||
                        matchL("/", "/", operador) ||
                        matchL("=", ":=", operador) ||
                        matchL("==", "=", operador) ||
                        matchL(":", ":", operador) ||
                        matchL(";", ";\n", operador)) {
			return true;
		}
                erro("operador");
            }
            return false;
        }
	
	public boolean expressao(Node node){
            Node expressao = node.addNode("expressao");
            if(firstT(token.tipo)){
		if(
                        id(expressao) && 
                        operador(expressao) && 
                        fator(expressao)) {
			return true;
		}
		erro("expressao");
        }
            return false;
        }
        
        public boolean matchL(String palavra, String newCode, Node node){
		if(token.lexema.equals(palavra)){
                        traduz(newCode);
                        node.addNode(token.lexema);
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
        
        public boolean matchT(String palavra, String newCode, Node node){
		if(token.tipo.equals(palavra)){
                    traduz(newCode);
                    node.addNode(token.lexema);
                    token = getNextToken();
                    return true;
		}
		return false;
	}
        
        public boolean matchT(String palavra, Node node){
		if(token.tipo.equals(palavra)){
                    traduz(palavra);
                    node.addNode(token.lexema);
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

    private boolean ifelse(Node root) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
}