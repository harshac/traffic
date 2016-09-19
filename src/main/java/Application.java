import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import presentation.ConsolePrinter;
import presentation.JsonStringBuilder;
import presentation.Run;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.join;
import static presentation.JsonStringBuilder.buildJsonForArray;

public class Application {

    public static void main(String args[]) throws IOException {
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

        List<Run> runs = new ArrayList<>();

        Run run = run(capacity, currentFlow, costWithinCapacity, defaultSignalTimings, defaultSignalTimings, costWithinCapacity);
        runs.add(run);

        for (int i = 1; i < 9; i++) {
            run = run(capacity, run.getPredictedFlow(), run.getPredictedCost(), run.getNewTimings(), defaultSignalTimings, costWithinCapacity);
            runs.add(run);
        }

        ConsolePrinter.printRuns(runs);

        FileUtils.writeStringToFile(new File("out/result.json"), buildJsonForArray(runs));
    }

    private static Run run(int[][] capacity, int[][] currentFlow, int[][] currentCost, int[][] currentTimings, int[][] defaultSignalTimings, int[][] costWithinCapacity) {
        SignalController signalController1 = new SignalController();
        Run run = new Run(capacity, currentFlow, currentCost, currentTimings);
        currentTimings = signalController1.control(currentFlow, capacity, currentCost, currentTimings);
        run.setNewTimings(currentTimings);
        Predictor simulate = Predictor.predict(currentFlow, defaultSignalTimings, currentTimings, costWithinCapacity, capacity);
        run.setPredictedCost(simulate.getPredictedCost());
        run.setPredictedFlow(simulate.getPredictedFlow());
        return run;
    }
}
