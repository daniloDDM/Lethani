package Lexico;
import java.text.CharacterIterator;

public class StringID extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        if (code.current() == '"') {
            StringBuilder string = new StringBuilder();

            code.next(); // pula a aspa inicial

            while (code.current() != CharacterIterator.DONE) {
                char c = code.current();

                if (c == '\\') {
                    code.next();
                    char next = code.current();

                    if (next == '"') {
                        string.append('"');
                        code.next();
                    } else {
                        string.append(next);
                        code.next();
                    }
                } else if (c == '"') {
                    code.next(); // pula a aspa final
                    break;
                } else {
                    string.append(c);
                    code.next();
                }
            }

            if (isTokenSeparator(code)) {
                return new Token("ID", "'" + string.toString() + "'"); // com aspas simples
            }
        }

        return null;
    }
}