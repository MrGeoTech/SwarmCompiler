package net.mrgeotech.tokens;

public class Token {

    private final TokenType type;
    private Object value;
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

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isCheckable() {
        return checkable;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value=" + value +
                ", checkable=" + checkable +
                '}';
    }

}
