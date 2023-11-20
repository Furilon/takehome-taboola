package net.medvediev;
/**
 * JSON Parser
 * JSONParser.parse(String) is the main method
 * Returns Map<String, Object> of the parsed JSON
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONParser {
    /**
     * Parses JSON string into Map<String, Object>
     * @param json JSON string
     * @return Map<String, Object> of the parsed JSON
     */
    public static Map<String, Object> parse(String json) {
        return (Map<String, Object>) parseValue(new Tokenizer(json));
    }

    private static Object parseValue(Tokenizer tokenizer) {
        tokenizer.skipWhitespaces();
        char current = tokenizer.peek();

        if (current == '{') {
            return parseObject(tokenizer);
        } else if (current == '[') {
            return parseArray(tokenizer);
        } else if (current == '\"') {
            return parseString(tokenizer);
        } else if (Character.isDigit(current) || current == '-') {
            return parseInteger(tokenizer);
        } else if (current == 't' || current == 'f') {
            return parseBoolean(tokenizer);
        }

        throw new RuntimeException("Unexpected token: " + current);
    }

    private static Map<String, Object> parseObject(Tokenizer tokenizer) {
        Map<String, Object> result = new HashMap<>();

        tokenizer.expect('{');
        tokenizer.skipWhitespaces();

        while (tokenizer.peek() != '}') {
            String key = parseString(tokenizer);
            tokenizer.skipWhitespaces();
            tokenizer.expect(':');
            Object value = parseValue(tokenizer);
            result.put(key, value);

            tokenizer.skipWhitespaces();
            if (tokenizer.peek() == ',') {
                tokenizer.next(); // consume ','
                tokenizer.skipWhitespaces();
            } else {
                break;
            }
        }

        tokenizer.expect('}');
        return result;
    }

    private static List<Object> parseArray(Tokenizer tokenizer) {
        List<Object> result = new ArrayList<>();

        tokenizer.expect('[');
        tokenizer.skipWhitespaces();

        while (tokenizer.peek() != ']') {
            result.add(parseValue(tokenizer));

            tokenizer.skipWhitespaces();
            if (tokenizer.peek() == ',') {
                tokenizer.next(); // consume ','
                tokenizer.skipWhitespaces();
            } else {
                break;
            }
        }

        tokenizer.expect(']');
        return result;
    }

    private static String parseString(Tokenizer tokenizer) {
        tokenizer.expect('\"');
        StringBuilder sb = new StringBuilder();
        while (true) {
            char c = tokenizer.next();
            if (c == '\"') {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static int parseInteger(Tokenizer tokenizer) {
        StringBuilder sb = new StringBuilder();
        char current = tokenizer.peek();

        while (Character.isDigit(current) || current == '-') {
            sb.append(current);
            tokenizer.next();
            current = tokenizer.peek();
        }

        return Integer.parseInt(sb.toString());
    }

    private static boolean parseBoolean(Tokenizer tokenizer) {
        if (tokenizer.peek() == 't') {
            tokenizer.consume("true");
            return true;
        } else {
            tokenizer.consume("false");
            return false;
        }
    }
}
