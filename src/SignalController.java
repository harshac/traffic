import model.Node;
import model.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignalController {

    public int[][] control(int currentFlow[][], int capacity[][], int cost[][], int currentSignal[][]) {
        Map<Path, List<Path>> congestedPaths = getCongestedPaths(currentFlow, capacity, cost);
        int newTimings[][] = optimizeSignalTimes(currentSignal, congestedPaths, currentFlow, capacity, cost);
        if (congestedPaths.isEmpty()) {
            optimizeBasedOnFastestRoute();
        }
        return newTimings;
    }

    private int[][] optimizeSignalTimes(int[][] currentSignal, Map<Path, List<Path>> congestedPaths, int[][] currentFlow, int[][] capacity, int[][] cost) {
        int newSignal[][] = Helper.clone(currentSignal);
        for (Path path : congestedPaths.keySet()) {
            List<Path> alternatePaths = congestedPaths.get(path);
            for (Path alternatePath : alternatePaths) {
                if (!alternatePath.isCongested(capacity, currentFlow)) {
                    newSignal[path.getSource()][path.getDestination()] = (int) (currentSignal[path.getSource()][path.getDestination()] * 0.98);
                    newSignal[alternatePath.getSource()][alternatePath.getINode(1)] = (int) (currentSignal[alternatePath.getSource()][alternatePath.getINode(1)] * 1.02);
                }
            }

        }
        return newSignal;
    }

    private void optimizeBasedOnFastestRoute() {

    }

    private Map<Path, List<Path>> getCongestedPaths(int[][] currentFlow, int[][] capacity, int[][] cost) {
        Map<Path, List<Path>> congestedPaths = new HashMap<Path, List<Path>>();
        int rowsCount = currentFlow.length;
        int columnCount = currentFlow[0].length;
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                //isCongested
                if (currentFlow[i][j] > capacity[i][j]) {
                    congestedPaths.put(new Path(i, j), getAlternatePaths(capacity, cost, i, j));
                }
            }
        }
        return congestedPaths;
    }

    private ArrayList<Path> getAlternatePaths(int[][] capacity, int[][] cost, int i, int j) {
        ArrayList<Path> alternatePaths = new ArrayList<Path>();
        int[] nextNodes = nextNodes(capacity, j);
        for (int k = 0; k < capacity[0].length; k++) {
            if (nextNodes[k] != 0) {
                ArrayList<Path> paths = new MinCostMaxFlow().getPaths(capacity, cost, i, k);
                for (Path path : paths) {
                    if (!path.contains(new Node(j))) {
                        alternatePaths.add(path);
                    }
                }
            }
        }
        return alternatePaths;
    }

    private int[] nextNodes(int capacity[][], int source) {
        return capacity[source];
    }
}
