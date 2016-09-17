import model.Node;
import model.Path;

import java.util.ArrayList;
import java.util.Arrays;

public class MinCostMaxFlow {
    boolean found[];
    int N, cap[][], flow[][], cost[][], dad[], dist[], pi[];

    static final int INF = Integer.MAX_VALUE / 2 - 1;

    Path search(int source, int sink) {
        Arrays.fill(found, false);
        Arrays.fill(dist, INF);
        dist[source] = 0;
        Path path = new Path();

        while (source != N) {
            int best = N;
            boolean isFound = false;
            found[source] = true;
            for (int k = 0; k < N; k++) {
                if (found[k]) continue;
                if (flow[k][source] != 0) {
                    int val = dist[source] + pi[source] - pi[k] - cost[k][source];
                    if (dist[k] > val) {
                        dist[k] = val;
                        dad[k] = source;
                    }
                }
                if (flow[source][k] < cap[source][k]) {
                    int val = dist[source] + pi[source] - pi[k] + cost[source][k];
                    if (dist[k] > val) {
                        dist[k] = val;
                        dad[k] = source;
                        isFound = true;
                    }
                }

                if (dist[k] < dist[best]) {
                    best = k;
                }

            }
            if (isFound) {
                path.addNode(new Node(source));
            }
            source = best;
        }
        path.addNode(new Node(sink));
        for (int k = 0; k < N; k++)
            pi[k] = Math.min(pi[k] + dist[k], INF);
        if (found[sink]) {
            return path;
        }
        return null;
    }


    ArrayList<Path> getPaths(int cap[][], int cost[][], int source, int sink) {
        this.cap = cap;
        this.cost = cost;
        ArrayList<Path> paths = new ArrayList<Path>();


        N = cap.length;
        found = new boolean[N];
        flow = new int[N][N];
        dist = new int[N + 1];
        dad = new int[N];
        pi = new int[N];

        Path path = search(source, sink);
        while (path != null) {
            int amt = INF;
            for (int x = sink; x != source; x = dad[x])
                amt = Math.min(amt, flow[x][dad[x]] != 0 ? flow[x][dad[x]] :
                        cap[dad[x]][x] - flow[dad[x]][x]);
            for (int x = sink; x != source; x = dad[x]) {
                if (flow[x][dad[x]] != 0) {
                    flow[x][dad[x]] -= amt;
                } else {
                    flow[dad[x]][x] += amt;
                }
            }
            path.setMaxFlow(amt);
            paths.add(path);
            path = search(source, sink);
        }
        return paths;
    }
}