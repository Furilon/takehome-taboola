package net.medvediev;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String input = "{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"age\": 30,\n" +
                "  \"address\": {\n" +
                "    \"street\": \"123 Main St\",\n" +
                "    \"city\": \"Anytown\",\n" +
                "    \"zip\": \"12345\"\n" +
                "  },\n" +
                "  \"contacts\": [\n" +
                "    {\n" +
                "      \"type\": \"email\",\n" +
                "      \"value\": \"john.doe@example.com\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"phone\",\n" +
                "      \"value\": \"+1 555-1234\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

        Map<String, Object> output = JSONParser.parse(input);
        System.out.println(output);
    }
}