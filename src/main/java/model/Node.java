package model;

public class Node {
    private Integer value;

    public Node(int source) {
        value = source;
    }

    public void print() {
        System.out.print((char) ('A' + value));
    }

    public Integer getValue(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (value != null ? !value.equals(node.value) : node.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
