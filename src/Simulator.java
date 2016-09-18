public class Simulator {

    private int[][] newFlow;
    private int[][] newCost;

    public Simulator(int[][] newFlow, int[][] newCost) {
        this.newCost = newCost;
        this.newFlow = newFlow;
    }

    public int[][] getPredictedFlow() {
        return newFlow;
    }

    public int[][] getNewCost() {
        return newCost;
    }

    public static Simulator simulate(int currentFlow[][], int oldSignal[][], int newSignal[][], int cost[][], int capacity[][]) {
        int newFlow[][] = Helper.clone(currentFlow);
        int newCost[][] = Helper.clone(cost);
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


        return new Simulator(newFlow, newCost);
    }

}
