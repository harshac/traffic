package util;

import java.util.ArrayList;

public class Utils {

    public static int[][] clone(int source[][]) {
        int length = source.length;
        int[][] cloned = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                cloned[i][j] = source[i][j];
            }
        }
        return cloned;
    }

    public static ArrayList<Integer> convertToList(int[] array) {
        ArrayList<Integer> integers = new ArrayList<Integer>();
        for (int i : array) {
            integers.add(i);
        }
        return integers;
    }

}
