package net.medvediev;

public class Tokenizer {
    private final String input;
    private int pos = 0;

    Tokenizer(String input) {
        this.input = input;
    }

    char peek() {
        return pos < input.length() ? input.charAt(pos) : (char) -1;
    }

    char next() {
        return input.charAt(pos++);
    }

    void expect(char c) {
        if (peek() != c) {
            throw new RuntimeException("Expected '" + c + "' but found '" + peek() + "'");
        }
        pos++;
    }

    void consume(String str) {
        for (char c : str.toCharArray()) {
            expect(c);
        }
    }

    void skipWhitespaces() {
        while (Character.isWhitespace(peek())) {
            pos++;
        }
    }
}