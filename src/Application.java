public class Application {

    public static void main(String args[]) {
        int capacity[][] = {
                {0, 500, 0, 0, 1000, 0},
                {0, 0, 700, 300, 0, 0},
                {0, 0, 0, 0, 0, 1000},
                {0, 0, 0, 0, 0, 800},
                {0, 0, 0, 0, 0, 1000},
                {0, 0, 0, 0, 0, 0}};

        int costWithinCapacity[][] = {
                {0, 150, 0, 0, 350, 0},
                {0, 0, 250, 150, 0, 0},
                {0, 0, 0, 0, 0, 150},
                {0, 0, 0, 0, 0, 20},
                {0, 0, 0, 0, 0, 450},
                {0, 0, 0, 0, 0, 0}};


        int currentFlow[][] = {
                {0, 410, 0, 0, 20, 0},
                {0, 0, 110, 610, 0, 0},
                {0, 0, 0, 0, 0, 20},
                {0, 0, 0, 0, 0, 310},
                {0, 0, 0, 0, 0, 110},
                {0, 0, 0, 0, 0, 0}};

        int signalTimings[][] = {
                {0, 60, 0, 0, 60, 0},
                {0, 0, 60, 60, 0, 0},
                {0, 0, 0, 0, 0, 60},
                {0, 0, 0, 0, 0, 60},
                {0, 0, 0, 0, 0, 60},
                {0, 0, 0, 0, 0, 0}};

        int[][] newTimings = new SignalController().control(currentFlow, capacity, costWithinCapacity, signalTimings);
        Simulator simulate = Simulator.simulate(currentFlow, signalTimings, newTimings, costWithinCapacity, capacity);
        System.out.println(" ------------- Run 1 ----------------");
        print2D("Old Timings", signalTimings);
        print2D("Current cost", costWithinCapacity);
        print2D("New cost", simulate.getNewCost());
        print2D("Current Flow", currentFlow);
        print2D("Predicted Flow", simulate.getNewFlow());
        print2D("New Timings", newTimings);

        for (int i = 1; i < 11; i++) {
            System.out.println(" ------------- Run" + (i + 1) + "----------------");
            print2D("Old Timings", newTimings);

            currentFlow = simulate.getNewFlow();
            int[][] cost = simulate.getNewCost();
            newTimings = new SignalController().control(currentFlow, capacity, cost, newTimings);
            print2D("New Timings", newTimings);

            simulate = Simulator.simulate(currentFlow, signalTimings, newTimings, costWithinCapacity, capacity);
            print2D("Current cost", cost);
            print2D("New cost", simulate.getNewCost());
            print2D("Current Flow", currentFlow);
            print2D("Predicted Flow", simulate.getNewFlow());


        }
    }

    private static void print2D(String name, int[][] array) {
        System.out.println("\n" + name);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
