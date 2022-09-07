package net.mrgeotech.tokens;

import net.mrgeotech.tokens.Token;
import net.mrgeotech.tokens.TokenType;
import net.mrgeotech.tokens.specials.Function;

import java.util.ArrayList;
import java.util.List;

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
        } else if (c == '~') {
            index++;
            return new Token(TokenType.VAR, null, false);
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
            // Getting a string
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

            // Checking for keywords
            if (sb.toString().equalsIgnoreCase("func")) {
                return new Token(TokenType.FUNCTION, null, false);
            } else {
                return new Token(TokenType.NAME, sb.toString(), false);
            }
        }
    }

    public List<Token> getAllTokens() {
        List<Token> tokens = new ArrayList<>();
        Token next = getNextToken();
        while (!next.getType().equals(TokenType.EOF)) {
            tokens.add(next);
            next = getNextToken();
        }
        return tokens;
    }

    public List<Token> formatTokens(List<Token> tokens) {
        if (tokens.size() == 0) {
            return tokens;
        }

        // Creating main function if there is no function
        if (!tokens.get(0).getType().equals(TokenType.FUNCTION)) {
            tokens.add(0, new Token(TokenType.FUNCTION, null, false));
            tokens.add(1, new Token(TokenType.NAME, "void", false));
            tokens.add(2, new Token(TokenType.NAME, "main", false));
            tokens.add(3, new Token(TokenType.LEFT_PAREN, null, false));
            tokens.add(4, new Token(TokenType.NAME, "String", false));
            tokens.add(5, new Token(TokenType.LEFT_BRACE, null, false));
            tokens.add(6, new Token(TokenType.RIGHT_BRACE, null, false));
            tokens.add(7, new Token(TokenType.NAME, "args", false));
            tokens.add(8, new Token(TokenType.RIGHT_PAREN, null, false));
            tokens.add(9, new Token(TokenType.LEFT_BRACKET, null, false));
            tokens.add(new Token(TokenType.RIGHT_BRACKET, null, false));
        }

        List<Token> result = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.getType().equals(TokenType.FUNCTION)) {
                // Getting the name and return type
                Token returnType = tokens.get(i + 1);
                if (!returnType.getType().equals(TokenType.NAME)) {
                    System.err.println("Expected return type after function keyword");
                    return null;
                }
                Token name = tokens.get(i + 2);
                if (!name.getType().equals(TokenType.NAME)) {
                    System.err.println("Expected function name after return type");
                    return null;
                }
                if (!tokens.get(i + 3).getType().equals(TokenType.LEFT_PAREN)) {
                    System.err.println("Expected left parenthesis after function name");
                    return null;
                }

                // Getting function parameters
                List<Token> params = new ArrayList<>();
                int j = i + 4;
                while (!tokens.get(j).getType().equals(TokenType.RIGHT_PAREN)) {
                    // Parameter type
                    if (!tokens.get(j).getType().equals(TokenType.NAME)) {
                        System.err.println("Expected parameter type");
                        return null;
                    }
                    // Check for array
                    int offset = 0;
                    if (tokens.get(j + 1).getType().equals(TokenType.LEFT_BRACE)) {
                        if (!tokens.get(j + 2).getType().equals(TokenType.RIGHT_BRACE)) {
                            System.err.println("Expected right bracket after left bracket");
                            return null;
                        }
                        tokens.get(j).setValue(tokens.get(j).getValue() + "[]");
                        offset = 2;
                    }
                    // Parameter name
                    if (!tokens.get(j + 1 + offset).getType().equals(TokenType.NAME)) {
                        System.err.println("Expected parameter name");
                        return null;
                    }
                    params.add(tokens.get(j));
                    params.add(tokens.get(j + 1 + offset));
                    j += 2 + offset;
                    if (tokens.get(j).getType().equals(TokenType.COMMA)) {
                        j++;
                    }
                }

                // Getting the function body
                if (!tokens.get(j + 1).getType().equals(TokenType.LEFT_BRACKET)) {
                    System.err.println("Expected left bracket after right parenthesis");
                    return null;
                }
                List<Token> body = new ArrayList<>();
                int k = j + 2;
                int bracketCount = 1;
                while (bracketCount > 0) {
                    if (tokens.get(k).getType().equals(TokenType.LEFT_BRACKET)) {
                        bracketCount++;
                    } else if (tokens.get(k).getType().equals(TokenType.RIGHT_BRACKET)) {
                        bracketCount--;
                    }
                    body.add(tokens.get(k));
                    k++;
                }

                // Setting function token
                token.setValue(new Function(returnType, name, params, body));
                result.add(token);

                // Skipping to the end of the function
                i = k - 1;
            }
        }

        return result;
    }

}
