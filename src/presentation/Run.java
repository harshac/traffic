package presentation;

import static presentation.ConsolePrinter.printMatrix;
import static presentation.JsonStringBuilder.buildJsonForMatrix;

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
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append("\"capacity\":");
        stringBuilder.append(buildJsonForMatrix(capacity));
        stringBuilder.append(",");

        stringBuilder.append("\"currentFlow\":");
        stringBuilder.append(buildJsonForMatrix(currentFlow));
        stringBuilder.append(",");

        stringBuilder.append("\"predictedFlow\":");
        stringBuilder.append(buildJsonForMatrix(predictedFlow));
        stringBuilder.append(",");

        stringBuilder.append("\"currentCost\":");
        stringBuilder.append(buildJsonForMatrix(currentCost));
        stringBuilder.append(",");

        stringBuilder.append("\"predictedCost\":");
        stringBuilder.append(buildJsonForMatrix(predictedCost));
        stringBuilder.append(",");

        stringBuilder.append("\"currentTimings\":");
        stringBuilder.append(buildJsonForMatrix(currentTimings));
        stringBuilder.append(",");

        stringBuilder.append("\"newTimings\":");
        stringBuilder.append(buildJsonForMatrix(newTimings));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void print(){
        printMatrix("Current Flow", currentFlow);
        printMatrix("Predicted Flow", predictedFlow);
        printMatrix("Current Cost", currentCost);
        printMatrix("Predicted Cost", predictedCost);
        printMatrix("Current Timings", currentTimings);
        printMatrix("New Timings", newTimings);
    }
}
