package net.mrgeotech;

import net.mrgeotech.tokens.Token;
import net.mrgeotech.tokens.TokenType;
import net.mrgeotech.tokens.Tokenizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Compiler {

    public static void main(String[] args) {
        String fileName = args[0];
        try {
            String code = Files.readString(Path.of(fileName));

            Tokenizer tokenizer = new Tokenizer(code);

            Token next = tokenizer.getNextToken();
            while (!next.getType().equals(TokenType.EOF)) {
                System.out.println(next.getType().name() + " " + next.getValue());
                next = tokenizer.getNextToken();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
