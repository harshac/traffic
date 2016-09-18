package presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang.StringUtils.join;

public class JsonStringBuilder {

    public static String buildJsonForARun(int capacity[][], int currentFlow[][],
                                          int currentCost[][], int currentSignalTimings[][],
                                          int predictedFlow[][], int newCost[][],
                                          int newSignalTimings[][]) {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append("\"capacity\":");
        stringBuilder.append(buildJsonForMatrix(capacity));
        stringBuilder.append(",");

        stringBuilder.append("\"currentFlow\":");
        stringBuilder.append(buildJsonForMatrix(currentFlow));
        stringBuilder.append(",");

        stringBuilder.append("\"currentCost\":");
        stringBuilder.append(buildJsonForMatrix(currentCost));
        stringBuilder.append(",");

        stringBuilder.append("\"currentTimings\":");
        stringBuilder.append(buildJsonForMatrix(currentSignalTimings));
        stringBuilder.append(",");

        stringBuilder.append("\"predictedFlow\":");
        stringBuilder.append(buildJsonForMatrix(predictedFlow));
        stringBuilder.append(",");

        stringBuilder.append("\"newCost\":");
        stringBuilder.append(buildJsonForMatrix(newCost));
        stringBuilder.append(",");

        stringBuilder.append("\"newTimings\":");
        stringBuilder.append(buildJsonForMatrix(newSignalTimings));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static String buildJsonForMatrix(int array[][]) {
        StringBuilder stringBuilder = new StringBuilder("[");
        List<String> rows = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            rows.add(buildJsonForRow(array[i]));
        }
        stringBuilder.append(join(rows, ","));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static String buildJsonForRow(int[] array) {
        StringBuilder stringBuilder = new StringBuilder("[");
        stringBuilder.append(join(convertToList(array), ","));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static ArrayList<Integer> convertToList(int[] array) {
        ArrayList<Integer> integers = new ArrayList<Integer>();
        for (int i : array) {
            integers.add(i);
        }
        return integers;
    }
}
