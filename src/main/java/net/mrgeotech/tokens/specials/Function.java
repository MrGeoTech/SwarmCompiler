package net.mrgeotech.tokens.specials;

import net.mrgeotech.tokens.Token;

import java.util.List;

public record Function(Token returnType, Token name, List<Token> args, List<Token> body) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FUNCTION name = ")
                .append(name.getValue())
                .append(" args = [");

        for (Token arg : args) {
            sb.append(arg.toString()).append(", ");
        }

        sb.append("] body = [");

        for (Token token : body) {
            sb.append(token.toString()).append(", ");
        }

        return sb.append("]").toString();
    }

}
