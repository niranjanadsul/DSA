package Graphs;

public class GraphNode implements Comparable{
    public String name;

    public GraphNode(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return name.compareTo(((GraphNode) o).name);
    }
}
