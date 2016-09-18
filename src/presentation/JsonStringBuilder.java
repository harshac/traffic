package presentation;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.join;
import static util.Utils.convertToList;

public class JsonStringBuilder {
    private static final String SEPARATOR = ",";

    public static String buildJsonForMatrix(int array[][]) {
        StringBuilder stringBuilder = new StringBuilder("[");
        List<String> rows = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            rows.add(buildJsonForRow(array[i]));
        }
        stringBuilder.append(join(rows, SEPARATOR));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String buildJsonForArray(ArrayList<Run> runs) {
        List<String> jsonStrings = runs.stream().map(run -> run.toString()).collect(Collectors.toList());
        StringBuilder json = new StringBuilder("[");
        json.append(StringUtils.join(jsonStrings, ","));
        json.append("]");
        return json.toString();
    }

    private static String buildJsonForRow(int[] array) {
        StringBuilder stringBuilder = new StringBuilder("[");
        stringBuilder.append(join(convertToList(array), SEPARATOR));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
