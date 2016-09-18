package presentation;

import java.util.List;

public class ConsolePrinter {

    public static void printMatrix(String name, int[][] array) {
        System.out.println("\n ~~ " + name + " ~~");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    public static void printRuns(List<Run> runs) {
        runs.stream().forEach(run -> {
            int runIndex = runs.indexOf(run) + 1;
            System.out.println("----------------- Run " + runIndex + "-----------------");
            run.print();
        });
    }
}
