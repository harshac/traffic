public class Helper {

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

}
