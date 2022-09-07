package net.mrgeotech.tokens.specials;

import net.mrgeotech.tokens.Token;

import java.util.List;

public record Function(Token returnType, Token name, List<Token> args, List<Token> body) {

}
