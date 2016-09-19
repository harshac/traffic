
package presentation;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

import static presentation.ConsolePrinter.printMatrix;
import static presentation.JsonStringBuilder.buildJsonForEdge;

public class Run {
    private int capacity[][];
    private int currentFlow[][];
    private int currentCost[][];
    private int currentTimings[][];
    private int predictedFlow[][];
    private int predictedCost[][];
    private int newTimings[][];

    public Run(int[][] capacity, int[][] currentFlow, int[][] currentCost, int[][] currentTimings) {
        this.capacity = capacity;
        this.currentFlow = currentFlow;
        this.currentCost = currentCost;
        this.currentTimings = currentTimings;
    }

    public void setPredictedFlow(int[][] predictedFlow) {
        this.predictedFlow = predictedFlow;
    }

    public void setPredictedCost(int[][] predictedCost) {
        this.predictedCost = predictedCost;
    }

    public void setNewTimings(int[][] newTimings) {
        this.newTimings = newTimings;
    }

    public int[][] getPredictedFlow() {
        return predictedFlow;
    }

    public int[][] getNewTimings() {
        return newTimings;
    }

    public int[][] getPredictedCost() {
        return predictedCost;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        ArrayList<String> edgeJsons = new ArrayList<>();
        for (int i = 0; i < capacity.length; i++) {
            for (int j = 0; j < capacity.length; j++) {
                if (capacity[i][j] > 0) {
                    edgeJsons.add(buildJsonForEdge(i, j, capacity[i][j], currentFlow[i][j], currentTimings[i][j]));
                }
            }
        }
        stringBuilder.append(StringUtils.join(edgeJsons, ","));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void print() {
        printMatrix("Current Flow", currentFlow);
        printMatrix("Predicted Flow", predictedFlow);
        printMatrix("Current Cost", currentCost);
        printMatrix("Predicted Cost", predictedCost);
        printMatrix("Current Timings", currentTimings);
        printMatrix("New Timings", newTimings);
    }
}

