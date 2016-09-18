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

    public static String buildJsonForMatrix(int array[][]) {
        ArrayList<String> edges = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            for(int j=0; j< array.length; j++){
                if(array[i][j] > 0){
                    edges.add(buildJsonForEdge(i, j, array[i][j]));
                }
            }
        }
        stringBuilder.append(join(edges, SEPARATOR));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static String buildJsonForRow(int[] array) {
        StringBuilder stringBuilder = new StringBuilder("[");
        stringBuilder.append(join(convertToList(array), SEPARATOR));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static String buildJsonForEdge(int startNode, int endNode, int value) {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append(format("\"startNode\": %s,", startNode));
        stringBuilder.append(format("\"endNode\": %s,", endNode));
        stringBuilder.append(format("\"value\": %s", value));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
