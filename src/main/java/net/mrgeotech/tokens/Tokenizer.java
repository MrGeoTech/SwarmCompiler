package net.mrgeotech.tokens;

import net.mrgeotech.tokens.Token;
import net.mrgeotech.tokens.TokenType;

public class Tokenizer {

    private final String code;
    private int index;

    public Tokenizer(String code) {
        this.code = code;
        this.index = 0;
    }

    public Token getNextToken() {
        if (index >= code.length()) {
            return new Token(TokenType.EOF, null, false);
        }

        char c = code.charAt(index);

        if (Character.isWhitespace(c)) {
            index++;
            return getNextToken();
        } else if (c == ';') {
            index++;
            return new Token(TokenType.SEMICOLON, null, false);
        } else if(c == '(') {
            index++;
            return new Token(TokenType.LEFT_PAREN, null, false);
        } else if (c == ')') {
            index++;
            return new Token(TokenType.RIGHT_PAREN, null, false);
        } else if(c == '[') {
            index++;
            return new Token(TokenType.LEFT_BRACE, null, false);
        } else if (c == ']') {
            index++;
            return new Token(TokenType.RIGHT_BRACE, null, false);
        } else if(c == '{') {
            index++;
            return new Token(TokenType.LEFT_BRACKET, null, false);
        } else if (c == '}') {
            index++;
            return new Token(TokenType.RIGHT_BRACKET, null, false);
        } else if (c == ',') {
            index++;
            return new Token(TokenType.COMMA, null, false);
        } else if (c == '"') {
            index++;
            StringBuilder sb = new StringBuilder();
            int state = 0;
            while (index < code.length()) {
                c = code.charAt(index);
                if (state == 0) {
                    if (c == '"') {
                        break;
                    } else if (c == '\\') {
                      state = 1;
                    } else {
                        sb.append(c);
                    }
                } else {
                    if (c == '"') {
                        sb.append(c);
                    } else {
                        sb.append('\\').append(c);
                    }
                    state = 0;
                }
                index++;
            }
            index++;
            return new Token(TokenType.VAR_LITERAL, sb.toString(), true);
        } else {
            StringBuilder sb = new StringBuilder();
            while (index < code.length()) {
                c = code.charAt(index);
                if (Character.isWhitespace(c) || (!Character.isDigit(c) && !Character.isAlphabetic(c))) {
                    break;
                } else {
                    sb.append(c);
                }
                index++;
            }

            if (sb.toString().equalsIgnoreCase("func")) {
                return new Token(TokenType.FUNCTION, null, false);
            } else {
                return new Token(TokenType.NAME, sb.toString(), false);
            }
        }
    }

}
