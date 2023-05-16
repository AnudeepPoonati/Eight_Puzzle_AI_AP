package Puzzle_AI_CS;

import java.util.List;

// Node class containing the metrics of a state in the The eight puzzle problem
public class Node {
    public List<Integer> state;
    public Node parent;
    public String action;
    public int pathCost;
    public int depth;

    public Node(List<Integer> state, Node parent, String action, int pathCost, int depth) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.pathCost = pathCost;
        this.depth = depth;
    }
}
