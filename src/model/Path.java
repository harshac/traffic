package model;

import java.util.ArrayList;

public class Path {
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private Integer maxFlow = 0;

    public Path(int i, int j) {
        nodes.add(new Node(i));
        nodes.add(new Node(j));
    }
    public Path(){

    }


    public void print(int cost[][]) {
        for (Node node : nodes) {
            node.print();
        }
        System.out.println("\nMax flow: " + maxFlow);
        System.out.println("Cost: " + calculateCost(cost));
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void setMaxFlow(Integer maxFlow) {
        this.maxFlow = maxFlow;
    }

    public int calculateCost(int cost[][]) {
        int pathCost = 0;
        Node startNode = nodes.get(0);
        for (Node node : nodes) {
            pathCost += cost[startNode.getValue()][node.getValue()];
            startNode = node;
        }
        return pathCost;
    }

    public boolean contains(Node node) {
        return nodes.contains(node);
    }

    public Integer getMaxFlow() {
        return maxFlow;
    }

    public boolean isCongested(int capacity[][], int currentFlow[][]) {
        for (int i = 0; i < nodes.size() - 1; ++i) {
            Integer node1 = nodes.get(i).getValue();
            Integer node2 = nodes.get(i + 1).getValue();
            if (capacity[node1][node2] < currentFlow[node1][node2]) {
                return true;
            }
        }
        return false;
    }

    public Integer getSource() {
        return nodes.get(0).getValue();
    }

    public Integer getDestination() {
        return nodes.get(nodes.size() - 1).getValue();
    }

    public Integer getINode(int i){
        return nodes.get(i).getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Path path = (Path) o;

        if (maxFlow != null ? !maxFlow.equals(path.maxFlow) : path.maxFlow != null) return false;
        if (nodes != null ? !nodes.equals(path.nodes) : path.nodes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nodes != null ? nodes.hashCode() : 0;
        result = 31 * result + (maxFlow != null ? maxFlow.hashCode() : 0);
        return result;
    }
}
