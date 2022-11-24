package net.mrgeotech;

import net.mrgeotech.tokens.Token;
import net.mrgeotech.tokens.Tokenizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Compiler {

    public static void main(String[] args) {
        String fileName = args[0];
        try {
            String code = Files.readString(Path.of(fileName));

            Tokenizer tokenizer = new Tokenizer(code);

            List<Token> tokens = tokenizer.formatTokens(tokenizer.getAllTokens());

            for (Token token : tokens) {
                System.out.println(token.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
