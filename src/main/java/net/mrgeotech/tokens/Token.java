package net.mrgeotech.tokens;

public class Token {

    private final TokenType type;
    private final Object value;
    private final boolean checkable;

    public Token(TokenType type, Object value, boolean checkable) {
        this.type = type;
        this.value = value;
        this.checkable = checkable;
    }

    public TokenType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public boolean isCheckable() {
        return checkable;
    }

}
