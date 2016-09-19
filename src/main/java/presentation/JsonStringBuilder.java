package presentation;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.join;
import static util.Utils.convertToList;

public class JsonStringBuilder {
    private static final String SEPARATOR = ",";

    public static String buildJsonForArray(List<Run> runs) {
        List<String> jsonStrings = runs.stream().map(run -> run.toString()).collect(Collectors.toList());
        StringBuilder json = new StringBuilder("[");
        json.append(StringUtils.join(jsonStrings, ","));
        json.append("]");
        return json.toString();
    }

    public static String buildJsonForEdge(int startNode, int endNode, int capacity, int flow, int time) {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append(jsonKeyValue("\"id\"", String.format("\"%s-%s\"", startNode, endNode)));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(jsonKeyValue("\"capacity\"", capacity));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(jsonKeyValue("\"flow\"", flow));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(jsonKeyValue("\"signalTime\"", time));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String jsonKeyValue(String key, String value) {
        StringBuilder stringBuilder = new StringBuilder(key);
        stringBuilder.append(":");
        stringBuilder.append(value);
        return stringBuilder.toString();
    }

    private static String jsonKeyValue(String key, int value) {
        StringBuilder stringBuilder = new StringBuilder(key);
        stringBuilder.append(":");
        stringBuilder.append(value);
        return stringBuilder.toString();
    }
}


