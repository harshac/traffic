import util.Utils;

public class Predictor {

    private int[][] predictedFlow;
    private int[][] predictedCost;

    public Predictor(int[][] predictedFlow, int[][] predictedCost) {
        this.predictedCost = predictedCost;
        this.predictedFlow = predictedFlow;
    }

    public int[][] getPredictedFlow() {
        return predictedFlow;
    }

    public int[][] getPredictedCost() {
        return predictedCost;
    }

    public static Predictor predict(int currentFlow[][], int oldSignal[][], int newSignal[][], int cost[][], int capacity[][]) {
        int newFlow[][] = Utils.clone(currentFlow);
        int newCost[][] = Utils.clone(cost);
        for (int i = 0; i < currentFlow.length; i++) {
            for (int j = 0; j < currentFlow.length; j++) {
                if (oldSignal[i][j] > 0) {
                    double signalDifference = (((double) newSignal[i][j] - oldSignal[i][j]) / oldSignal[i][j]);
                    newFlow[i][j] += (int) (currentFlow[i][j] * signalDifference);
                    if (capacity[i][j] < newFlow[i][j]) {
                        newCost[i][j] += (int) (cost[i][j] * ((double) currentFlow[i][j] - capacity[i][j]) / capacity[i][j]);
                    }
                }
            }
        }


        return new Predictor(newFlow, newCost);
    }

}
