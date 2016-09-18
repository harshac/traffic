package presentation;

import java.util.Arrays;

import static presentation.JsonStringBuilder.buildJsonForMatrix;

public class Run {
    private int capacity[][];
    private int currentFlow[][];
    private int currentCost[][];
    private int currentTimings[][];
    private int predictedFlow[][];
    private int newCost[][];
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

    public void setNewCost(int[][] newCost) {
        this.newCost = newCost;
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

    public int[][] getNewCost() {
        return newCost;
    }

    @Override
    public String toString() {
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
        stringBuilder.append(buildJsonForMatrix(currentTimings));
        stringBuilder.append(",");

        stringBuilder.append("\"predictedFlow\":");
        stringBuilder.append(buildJsonForMatrix(predictedFlow));
        stringBuilder.append(",");

        stringBuilder.append("\"newCost\":");
        stringBuilder.append(buildJsonForMatrix(newCost));
        stringBuilder.append(",");

        stringBuilder.append("\"newTimings\":");
        stringBuilder.append(buildJsonForMatrix(newTimings));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
