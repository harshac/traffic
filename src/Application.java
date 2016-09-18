import org.apache.commons.lang.StringUtils;
import presentation.Run;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.join;

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

        int defaultSignalTimings[][] = {
                {0, 60, 0, 0, 60, 0},
                {0, 0, 60, 60, 0, 0},
                {0, 0, 0, 0, 0, 60},
                {0, 0, 0, 0, 0, 60},
                {0, 0, 0, 0, 0, 60},
                {0, 0, 0, 0, 0, 0}};

        List<String> runJsons = new ArrayList<String>();

        Run run = run(capacity, currentFlow, costWithinCapacity, defaultSignalTimings, defaultSignalTimings, costWithinCapacity);
        runJsons.add(run.toString());

        for (int i = 1; i < 6; i++) {
            run = run(capacity, run.getPredictedFlow(), run.getNewCost(), run.getNewTimings(), defaultSignalTimings, costWithinCapacity);
            runJsons.add(run.toString());
        }

        StringBuilder json = new StringBuilder("[");
        json.append(StringUtils.join(runJsons, ","));
        json.append("]");
        System.out.println(json.toString());
    }

    private static Run run(int[][] capacity, int[][] currentFlow, int[][] currentCost, int[][] currentTimings, int[][] defaultSignalTimings, int[][] costWithinCapacity){
        SignalController signalController1 = new SignalController();
        Run run = new Run(capacity, currentFlow, currentCost, currentTimings);
        currentTimings = signalController1.control(currentFlow, capacity, currentCost, currentTimings);
        run.setNewTimings(currentTimings);
        Simulator simulate = Simulator.simulate(currentFlow, defaultSignalTimings, currentTimings, costWithinCapacity, capacity);
        run.setNewCost(simulate.getNewCost());
        run.setPredictedFlow(simulate.getPredictedFlow());
        return run;
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
