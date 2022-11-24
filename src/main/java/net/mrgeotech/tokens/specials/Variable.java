package net.mrgeotech.tokens.specials;

import net.mrgeotech.tokens.Token;
import net.mrgeotech.tokens.TokenType;

public class Variable extends Token {

    private final String name;

    public Variable(TokenType type, String name, Object value, boolean checkable) {
        super(type, value, checkable);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "VARIABLE name = " + name + " value = " + getValue();
    }

}
